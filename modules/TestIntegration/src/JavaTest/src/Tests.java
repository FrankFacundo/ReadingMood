import java.util.ArrayList;
import com.google.gson.*;
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
import java.net.InetAddress;
import java.net.UnknownHostException;

public class Tests {

	public static String[] listOfTitle = {"Agricultural field","Christmas", "Cook","Forest","Floral garden"
            ,"Ocean"};
	
	public static void main(String[] args) {
		
		
		User userExample = new User();
		averageToConnect(userExample, 10);
		getAverageDisplayRP(userExample, createOlfactive(10));
		
		
		
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
	
	
	private static long timeToConnect(User user)
	{
		long startTime = System.currentTimeMillis();
		user.disconnect();
		long stopTime = System.currentTimeMillis();
		return (stopTime-startTime);
		
	}
	
	public static long averageToConnect(User user, int n)
	{
		
		long compteur = 0;
		ArrayList<Long> listTime = new ArrayList<>(); 
		for (int i = 0; i<n; i++)
		{
			long ntime = timeToConnect(user);
			listTime.add(ntime);
			compteur = compteur + ntime;
		}
		System.out.println(listTime);
		long moy = compteur / n;
		System.out.println("Temps moyen pour se connecter:" + moy);
		return moy;
		
	}
	
	
	
	public static ArrayList<AmbianceOlfactive> createOlfactive(int n)
	{
		ArrayList<AmbianceOlfactive> listResult = new ArrayList<>();
		
		try
		{
			for (int i = 0; i<n; i++)
			{
				int j = (int)  Math.random() * n ;
				AmbianceOlfactive ambiance = new AmbianceOlfactive("", (listOfTitle[j]));
				listResult.add(	ambiance);
			}
			
		} catch (IndexOutOfBoundsException e)
		{
			
		}
		
		return listResult;
		
	}
	
	
	
	public static long getTimeToDisplay(User user, AmbianceOlfactive myAmbiance)
	{
		long startTime = System.currentTimeMillis();
		try
		{
			user.displayOneSmellRP(myAmbiance);
			
		}catch (NullPointerException e) {}
		
		long stopTime = System.currentTimeMillis();
		return (stopTime - startTime);
		
	}
	
	public static ArrayList<Long> getTimesDisplay(User user, ArrayList<AmbianceOlfactive> myAmbiances)
	{
		ArrayList<Long> result = new ArrayList<>();
		
		for (int i = 0; i<myAmbiances.size(); i++)
		{
			long time = getTimeToDisplay(user, myAmbiances.get(i));
			result.add(time);
		}
		
		return result;
	}
		
		
		
	public static long getAverageDisplayRP(User user, ArrayList<AmbianceOlfactive> myAmbiances)
	{
		
		long compteur = 0;
		ArrayList<Long> listTime = getTimesDisplay(user, myAmbiances);
		int n = listTime.size();
		for (int i = 0; i<n ; i++)
		{
			compteur += listTime.get(i);
		}
		long res = compteur / (long)n;
		System.out.println("Temps moyen pour diffuser une ambiance :" + res);
		return res;
		
		
	}
		
	
		
		
	}
	

	


