import java.io.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.*;
import org.jsoup.select.*;
import java.net.*;
public class OpeningOnline {
	public OpeningOnline() {
		
	}
	public static void main(String args[]) {
		String myText= openUrlAndReturnTextAndSaveIt("https://www.gutenberg.org/files/58535/58535-0.txt","src/book.txt");
		//System.out.println(myText.length());
		//System.out.println(myText);
		String link= returnTextAndSaveItTo("http://www.gutenberg.org/ebooks/40634",null);
		System.out.println(link);
	}
	public static String openUrlAndReturnTextAndSaveIt(String link,String folder){
		String result="";
		if(folder!=null) {
		try {
			PrintWriter out= new PrintWriter(new File(folder));
			URL url= new URL(link);
			URLConnection connection=url.openConnection();
			BufferedReader br= new BufferedReader( new InputStreamReader(connection.getInputStream()));
		String inputLine;
		while( (inputLine=br.readLine())!=null) {
			out.println(inputLine);
			result+=" \n "+inputLine;
		}
		br.close();
		}
		catch (MalformedURLException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}	
		return result;}
		else {
		try {
			URL url= new URL(link);
			URLConnection connection=url.openConnection();
			BufferedReader br= new BufferedReader( new InputStreamReader(connection.getInputStream()));
		String inputLine;
		while( (inputLine=br.readLine())!=null) {
			result+=" \n "+inputLine;
		}
		br.close();
		}
		catch (MalformedURLException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}	
		return result;
			
		}
	}
	public static String returnTextAndSaveItTo(String link,String folder) {
		String result="";
		try {
		Document doc= Jsoup.connect(link).get();
		Element thing= doc.select("a[type=text/plain]").first();
		System.out.println(thing.text());
		return (thing.text());
		
		}
		catch (IOException e) {
			e.printStackTrace();
			return result;
		}
	}
		

	
}
