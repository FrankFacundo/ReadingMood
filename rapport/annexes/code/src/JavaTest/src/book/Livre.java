package book;

public abstract class Livre {

	
	protected String titleBook ;
	/*
	 * Title of the book
	 */
	protected String authorBook;
	/*
	 * Author of the book
	 */
	protected String summaryBook;
	/*
	 * Summary of the book
	 */
	protected String pathToImage;
	/*
	 * It is the path to get the picture of a book
	 */
	
	
	public Livre(String title, String author, String summary)
	{
		this.titleBook = title;
		this.authorBook = author;
		this.summaryBook = summary;
	}
	
	public Livre(String title)
	{
		this.titleBook = title;
		getDatasBook(title);
	}
	
	public abstract void getDatasBook(String title);
	/*
	 * Initialise the author, summary, image and/or the book itself 
	 */
	

	
	
	
	
	
	
	
	// Getters and Setters
	
	public String getTitleBook() {
		return titleBook;
	}
	public void setTitleBook(String titleBook) {
		this.titleBook = titleBook;
	}
	public String getAuthorBook() {
		return authorBook;
	}
	public void setAuthorBook(String authorBook) {
		this.authorBook = authorBook;
	}
	public String getSummaryBook() {
		return summaryBook;
	}
	public void setSummaryBook(String summaryBook) {
		this.summaryBook = summaryBook;
	}
	
	
	
	
}
