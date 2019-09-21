package book;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class LivreLibrary extends Livre{

	private String link;
	private String contentServor;
	private ArrayList<String> listEpubUrl = new ArrayList<String>();
	private ArrayList<String> listAtmosphere = new ArrayList<String>();
	
	
	
	public LivreLibrary(String title, String author, String summary)
	/*
	 * Here is the class that represents a book in the servor
	 */
	
	{
		super(title, author, summary);
		
	}

	public LivreLibrary(String link)
	{
		super("","","");
		this.link = link;
		getContentServor();
	
		
	}
	
	private void getContentServor()
	{
		StringBuilder sb = new StringBuilder();
		URL url;
		try {
			url = new URL(link);
			System.out.println("Before download " + link);
	        BufferedReader in;
	        in = new BufferedReader(
	                new InputStreamReader(
	                        url.openStream()));
	        String inputLine;
	        while ((inputLine = in.readLine()) != null)
	            sb.append(inputLine);
	        in.close();

	        this.contentServor = sb.toString();
		} catch (MalformedURLException e) {

			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		
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
		LivreMyCollection newBook = new LivreMyCollection("", "", "");
		return newBook;
		
	}
	
	
	public ArrayList<String> getStringsFromData()
    {
		String listWeb = this.contentServor;
		ArrayList<String> listResult = new ArrayList<String>();
        if (listWeb !=null)
        {

            int indexB = listWeb.indexOf("(");
            int indexE= listWeb.indexOf(")");
            while (indexB != -1 && indexE != -1)
            {
                listResult.add(listWeb.substring(indexB,indexE));
                indexB = listWeb.indexOf("(", indexB + 1);
                indexE = listWeb.indexOf(")", indexE + 1);
            }

      
       }
        
        return listResult;

    }
	

	protected ArrayList<String> getDataFromString(String singleData)
    {
        ArrayList<String> result = new ArrayList<String>();
        String inter = new String();
        int n = singleData.length();
        int compteur = 0;
        for (int i =0; i<n;i++)
        {

            String currentValue = String.valueOf(singleData.charAt(i));
            if (currentValue.equals("'") && compteur == 0)
            {
                inter = new String();
                compteur+=1;
            }else if (currentValue.equals("'") && compteur ==1)
            {
                result.add(inter);
                compteur = 0;

            }else if(currentValue.equals(","))
            {

            }else
            {
                inter = inter + currentValue;
            }

        }

        return result;

    }
	
	
	public ArrayList<String> getUrlEpub()
	{
		ArrayList<String> listData = getStringsFromData();
		ArrayList<ArrayList<String>> listResult = new ArrayList<ArrayList<String>>(); 
		for (String x : listData)
		{
			ArrayList<String> prov_list = getDataFromString(x);
			try
			{
				listEpubUrl.add(prov_list.get(4));	
			}catch (IndexOutOfBoundsException e)
			{
				
			}
			
//			listResult.add(prov_list);
			
		}
		
//		return listResult.get(0);
		return listEpubUrl;
		
	}
	
	
	
	private String getDataFromEpubUrl(String link)
	{
	
		StringBuilder sb = new StringBuilder();
		URL url;
		try {
			url = new URL(link);
	        BufferedReader in;
	        in = new BufferedReader(
	                new InputStreamReader(
	                        url.openStream()));
	        String inputLine;
	        while ((inputLine = in.readLine()) != null)
	            sb.append(inputLine);
	        in.close();

	        this.contentServor = sb.toString();
		} catch (MalformedURLException e) {

			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return sb.toString();
		
	}
	
	
	public final static void displayArrayList(ArrayList<String> listToDisplay)
	{
		for (String x : listToDisplay)
		{
			System.out.println(x);
		}
	}
	
	
	
	

}
