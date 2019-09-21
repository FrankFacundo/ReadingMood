package user;

import java.awt.print.Book;
import java.util.ArrayList;

import ambiance.AmbianceSonore;
import ambiance.AmbianceOlfactive;
import book.LivreLibrary;
import book.LivreMyCollection;
import book.Livre;
public class User {
	
	private LivreMyCollection currentBook;
	/*
	 * This is the book that is currently open by the user
	 */
	private ArrayList<LivreMyCollection> bookSavedByUser;
	/*
	 * This is the list of books that was downloaded by the user
	 */
	
	public User()
	{
	
	}
	
	
	public boolean isConnectedRaspberryPi()
	{
		// TO BE COMPLETED
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
		}
		
		// TO BE COMPLETED		
		return false;
	}
	
	
	public boolean displaySmellRP(ArrayList<AmbianceOlfactive> currentSong )
	/*
	 * Send the datas of the current smell atmosphere
	 * Return true if it has been done correctly
	 */
	{
		// TO BE COMPLETED		
		return false;
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
