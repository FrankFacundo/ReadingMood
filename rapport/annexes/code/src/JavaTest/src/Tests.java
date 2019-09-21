import java.util.ArrayList;
import ambiance.*;
import book.*;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
import user.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.BufferedInputStream;
import java.io.File;
import javax.swing.JFileChooser;
import java.lang.Thread;


public class Tests {

	public static void main(String[] args) {
		
		
		User userExample = new User();
		String path = "../../ambianceMp3/Car.mp3";		
//		JFileChooser fc = new JFileChooser();

			
		class PlaySound implements Runnable
			{
				private boolean isPlayed = false;
				private void play()
				{
				
				while (isPlayed)
				{
					
				try
				{
					
					File file = new File(path);
					FileInputStream fis = new FileInputStream(file);
					BufferedInputStream bis = new BufferedInputStream(fis);
					
					try
					{
						Player player = new Player(bis);
						player.play();
						
					}catch (JavaLayerException e )
					{
						System.out.println("can not open the file");
					}
					
				} catch (IOException e)
				{
					e.printStackTrace();
				}
				

				}
				}
				@Override
				public void run() {
					isPlayed = true;
					this.play();
					
				}
				public void stopThread()
				{
					this.isPlayed = false;
				}
				
			}

//		PlaySound mySong = new PlaySound();
//		Thread t = new Thread(mySong);
//		t.start();
//		mySong.stopThread();
//		try {
//			t.sleep(10000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
		Thread t = new Thread(new UISound());
		t.start();

	}
	
	
	// TESTS 
	
	
	public long downloadTimeTest(User user, LivreLibrary bookToDownload)
	/*
	 * Gives the time to download a book
	 */
	{
		long startTime = System.currentTimeMillis();
		user.downloadBook(bookToDownload);
		long stopTime = System.currentTimeMillis();
		return (stopTime - startTime);
	}
	
	public boolean isDisplayedWithoutBox(User user)
	/*
	 * Returns a boolean : true if the action has been well made
	 */
	{
		
		boolean bool = user.displaySongWithoutRP(user.getCurrentBook().getCurrentAmbianceSonore());
		return bool;
	}
	
	
	public boolean isSavedCurrentBook(User user)
	/*
	 * Returns if a new book is well saved
	 */
	{
		LivreMyCollection bookForTest = new LivreMyCollection("", "", "");
		user.setCurrentBook(bookForTest);
		return (user.getCurrentBook()==bookForTest);
	}
	
	public boolean isSavedManualMode(User user)
	/*
	 * Returns if the modification of the current Atmospheres is well saved
	 */
	{
		ArrayList<AmbianceSonore> ambianceToTest = new ArrayList<AmbianceSonore>();
		LivreMyCollection currentBook = user.getCurrentBook();
		currentBook.setCurrentAmbianceSonore(ambianceToTest);
		return (currentBook.getCurrentAmbianceSonore()==ambianceToTest);
	}
	
	
	
	

}
