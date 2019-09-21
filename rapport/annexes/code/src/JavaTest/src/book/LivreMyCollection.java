package book;

import java.util.ArrayList;
import ambiance.Ambiance;
import ambiance.AmbianceOlfactive;
import ambiance.AmbianceSonore;

public class LivreMyCollection extends Livre
/*
 * It represents the book that will be saved by the user
 */
{

	private String contentBook;
	/*
	 * The path to get the .epub or .txt of the book
	 */
	
	private ArrayList<String> currentText ;
	/*
	 * It represents the current page of the user
	 */
	
	private int numberOfPage ;
	/*
	 * It represents the number of page of the book
	 * It depends of how is splited the book by the 
	 * application
	 */
	
	private int currentNumberPage;
	/*
	 * It represents the counter of the page of the user
	 */
	
	private ArrayList<AmbianceOlfactive> ambianceOlfactiveBook;
	/*
	 * This is the big list where will be all the smell atmosphere of the full book
	 */
	private ArrayList<AmbianceSonore> ambianceSonoreBook;
	/*
	 * This is the big list where will be all the song atmosphere of the full book
	 */
	
	private ArrayList<AmbianceOlfactive> currentAmbianceOlfactive;
	/*
	 * The current list of olfactive atmosphere 
	 */
	private ArrayList<AmbianceSonore> currentAmbianceSonore;
	/*
	 * The current list of song atmosphere
	 */
	
	
	
	

	public LivreMyCollection(String title, String author, String summary) {
		super(title, author, summary);	
		currentNumberPage = 0;
	}



	@Override
	public void getDatasBook(String title) 
	/*
	 * Save the author, summary, image and .epub/.txt of the book by its title
	 */
	{
		// TO BE COMPLETED
		
	}
	
	public void updateAtmosphere()
	/*
	 * Update the current atmosphere with the good context of the book
	 */
	{
		// TO BE COMPLETED
	}
	
	
	// Getters and Setters
	
	public ArrayList<AmbianceOlfactive> getCurrentAmbianceOlfactive() {
		return currentAmbianceOlfactive;
	}



	public void setCurrentAmbianceOlfactive(ArrayList<AmbianceOlfactive> currentAmbianceOlfactive) {
		this.currentAmbianceOlfactive = currentAmbianceOlfactive;
	}



	public ArrayList<AmbianceSonore> getCurrentAmbianceSonore() {
		return currentAmbianceSonore;
	}



	public void setCurrentAmbianceSonore(ArrayList<AmbianceSonore> currentAmbianceSonore) {
		this.currentAmbianceSonore = currentAmbianceSonore;
	}



	public ArrayList<String> getCurrentText() {
		return currentText;
	}

	public void setCurrentText(ArrayList<String> currentText) {
		this.currentText = currentText;
	}

	public int getNumberOfPage() {
		return numberOfPage;
	}

	public void setNumberOfPage(int numberOfPage) {
		this.numberOfPage = numberOfPage;
	}
	
	
	
	
	
	
}
