package book;

public class LivreLibrary extends Livre{

	public LivreLibrary(String title, String author, String summary)
	/*
	 * Here is the class that represents a book in the servor
	 */
	
	{
		super(title, author, summary);
		
	}

	@Override
	public void getDatasBook(String title)
	/*
	 * Save the author, summary and image of the book by its title
	 */
	{
		// TO BE COMPLETED
		
	}
	
	public LivreMyCollection getBook(String link)
	/*
	 * The link to download the book
	 */
	{
		// TO BE COMPLETED
		
		
		LivreMyCollection newBook = new LivreMyCollection("", "", "");
		return newBook;
		
	}
	
	
	

}
