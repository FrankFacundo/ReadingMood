package user;
import com.sun.media.jfxmedia.MediaPlayer;
import java.awt.print.Book;
import java.util.ArrayList;

import ambiance.AmbianceSonore;
import ambiance.AmbianceOlfactive;
import book.LivreLibrary;
import book.LivreMyCollection;
import raspberry.RaspberryPi;
import book.Livre;
import com.google.gson.*;

public class User {
	
	private LivreMyCollection currentBook;
	/*
	 * This is the book that is currently open by the user
	 */
	private ArrayList<LivreMyCollection> bookSavedByUser;
	/*
	 * This is the list of books that was downloaded by the user
	 */
	private ArrayList<AmbianceOlfactive> listOlfactive ;
	
	private RaspberryPi rp;
	
	public User()
	{
		initialiseRP();
	}
	
	
	public ArrayList<AmbianceOlfactive> getListOlfactive()
	{
		return listOlfactive;
	}
	
	public void setListOlfactive(ArrayList<AmbianceOlfactive> listOlfactive )
	{
		this.listOlfactive = listOlfactive;
	}
	
	public void initialiseRP()
	{
		rp = new RaspberryPi();
	}
	
	public void connect()
	{
		try {
			rp.connect();
		}catch (NullPointerException e)
		{
			
		}
	}
	
	public void disconnect()
	{
		try {
			rp = null;
			rp = new RaspberryPi();
			rp.connect();
		}catch (NullPointerException e)
		{
			
		}
	}
	
	public boolean isConnectedRaspberryPi()
	{
		
		try
		{
			return rp.isConnected();
			
		}catch (NullPointerException e) {}
		return false;
	}
	
	
	
	public boolean displaySongWithoutRP(ArrayList<AmbianceSonore> currentSong )
	/*
	 * Display the song on the phone
	 * Return true if it has been done correctly
	 */
	{
		// TO BE COMPLETED		
		return false;
	}
	
	public boolean displaySongRP(ArrayList<AmbianceSonore> currentSong )
	/*
	 * Display the song on the raspberry Pi
	 * Return true if it has been done correctly
	 */
	{
		
		if (!isConnectedRaspberryPi())
		{
			System.out.println("The raspberry Pi is not connected");
		}else
		{
			
		}
		
		
		return false;
	}
	
	
	private String displayAmbianceSmell(RaspberryPi rasp, AmbianceOlfactive myAtmosphere)
	{
		String response = new String();
		switch (myAtmosphere.getTitle())
        {
            case "Agricultural field" :
                response =  rasp.setAgriculturalField();
                break;
            case "Christmas" :
                response = rasp.setChristmas();
                break;
            case "Cook" :
                response = rasp.setCook();
                break;
            case "Forest" :
                response = rasp.setForest();
                break;
            case "Floral garden" :
                response = rasp.setFloralGarden();
                break;
            case "Ocean" :
                response = rasp.setOcean();
                break;
		
	}
		return response;
	}
	
	public String displayOneSmellRP(AmbianceOlfactive myAmbiance)
	{
		String response = displayAmbianceSmell(rp,myAmbiance);
		return response;
	}
	
	
	
	
	public boolean downloadBook(LivreLibrary bookToDownload)
	/*
	 * The user wants to save and download this book
	 * Return true if it has been done correctly
	 */
	{
		// TO BE COMPLETED
		bookSavedByUser.add(bookToDownload.getBook(""));
		return false;
	}
	
	public int getSizeBook(Livre book)
	/*
	 * Return the size of the book that it takes in the application
	 */
	{
		// TO BE COMPLETED
		
		return -1;
	}
	
	
	
	// Getters and Setters
	
	public LivreMyCollection getCurrentBook() {
		return currentBook;
	}

	public void setCurrentBook(LivreMyCollection currentBook) {
		this.currentBook = currentBook;
	}

	public ArrayList<LivreMyCollection> getBookSavedByUser() {
		return bookSavedByUser;
	}

	public void setBookSavedByUser(ArrayList<LivreMyCollection> bookSavedByUser) {
		this.bookSavedByUser = bookSavedByUser;
	}

	
	
	
	
	
	

}
