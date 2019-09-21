import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.swing.JFileChooser;

import ambiance.AmbianceSonore;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

public class Main {

	public static void main(String[] args) {

		JFileChooser fc = new JFileChooser();
		/*
		 * To open and search the .mp3
		 */
			
		class PlaySound implements Runnable
			{
				private boolean isPlayed = false;
				/*
				 * Boolean to specify if the .mp3 is played or not
				 */
				private void play()
				{
				
				while (isPlayed)
				{
					
				try
				{
					int result = fc.showOpenDialog(null);
					if (result== JFileChooser.APPROVE_OPTION)
					
					{
					File file = new File(fc.getSelectedFile().getAbsolutePath());
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
					}	
				} catch (IOException e)
				{
					e.printStackTrace();
				}
				}

				
				}
				@Override
				public void run() {
					/*
					 * Play the .mp3
					 */
					isPlayed = true;
					this.play();
					
				}
				public void stopThread()
				{
					this.isPlayed = false;
				}
				
			}
		
		PlaySound mySong = new PlaySound();
		Thread t = new Thread(mySong);
		t.start();
		mySong.stopThread();

	}

}
