import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.JFileChooser;

import ambiance.AmbianceSonore;
import book.LivreLibrary;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

public class Main {

	public static void main(String[] args) {

//		JFileChooser fc = new JFileChooser();
		/*
		 * To open and search the .mp3
		 */
			
//		class PlaySound implements Runnable
//			{
//				private boolean isPlayed = false;
//				/*
//				 * Boolean to specify if the .mp3 is played or not
//				 */
//				private void play()
//				{
//				
//				while (isPlayed)
//				{
//					
//				try
//				{
//					int result = fc.showOpenDialog(null);
//					if (result== JFileChooser.APPROVE_OPTION)
//					
//					{
//					File file = new File(fc.getSelectedFile().getAbsolutePath());
//					FileInputStream fis = new FileInputStream(file);
//					BufferedInputStream bis = new BufferedInputStream(fis);
//					
//					try
//					{
//						Player player = new Player(bis);
//						player.play();
//						
//					}catch (JavaLayerException e )
//					{
//						System.out.println("can not open the file");
//					}
//					}	
//				} catch (IOException e)
//				{
//					e.printStackTrace();
//				}
//				}
//
//				
//				}
//				@Override
//				public void run() {
//					/*
//					 * Play the .mp3
//					 */
//					isPlayed = true;
//					this.play();
//					
//				}
//				public void stopThread()
//				{
//					this.isPlayed = false;
//				}
//				
//			}
//		
//		PlaySound mySong = new PlaySound();
//		Thread t = new Thread(mySong);
//		t.start();
//		mySong.stopThread();

		
//		LivreLibrary my_book = new LivreLibrary(base+listLetter[0]);
		String[] listLetter ={ "A","B","C", "D","E","F","G","H",
	            "I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
		String[] listTitle ={ "Admnistration", "Anger", "Attic", "Bathroom",
	            "Bedroom", "Calm", "Car", "Castle", "Cave", "Cemetery",
	            "Church", "Circus","Company", "Country Town","Desert", "Fear",
	            "Field", "Fight","Fog", "Forest", "Garden", "Gunshot","Gym","Happy","Hospital", "Industry", "Jail",
	            "Kitchens", "Library", "LivingRoom", "Love","Meadow", "Mine", "Mountain","Ocean",
	            "Plane","Rain", "Sad", "School", "Snow","Street","Sun", "Thunder", "TrainStation", "Wind"};
		
		ArrayList<String> listEpubUrl = getEpubUrl(listLetter[0]); 
//		ArrayList<ArrayList<String>> listAtmo = getAtmosphereFromLinks(listEpubUrl);
		System.out.println(listEpubUrl.size() + " ");
		
//		System.out.println(listAtmo);
//		LivreLibrary.displayArrayList(getAverageFromList());
//		System.out.println(getHtmlWithAtmosphere(listEpubUrl.get(1)));
//		System.out.println(getAverageFromList(new ArrayList<ArrayList<String>>()));
		
		
//		getAverageNumber(listAtmo);
//		getAverageFromList(listAtmo);
		
		
		
	}
	
	
	
	public static ArrayList<String> getStringsFromData(String listWeb)
    {
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
	
	
	public static ArrayList<String> getDataFromString(String singleData)
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
	
	public static String getDataFromUrl(String link)
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
		} catch (MalformedURLException e) {

			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return sb.toString();
		
	}
	
	
	public static ArrayList<String> getEpubUrl(String letter)
	{
		String base = "https://readingmood-239210.appspot.com/searchfletterbook/";
		
		ArrayList<String> result = new ArrayList<String>();
		String data = getDataFromUrl(base+letter);
		ArrayList<String> listData = getStringsFromData(data);
		for (String x : listData)
		{
			ArrayList<String> prov_list = getDataFromString(x);
			try
			{
				
				String toAdd = prov_list.get(4);
				if (toAdd.length() > 10)
				{
					result.add(toAdd);	
				}
				
			}catch (IndexOutOfBoundsException e)
			{	}
						
		}
				
		
		return result;
	}
	
	
	
	public static ArrayList<String> getHtmlWithoutAtmosphere(String listWeb)
    {

        ArrayList<String> listHtml = new ArrayList<>();
        ArrayList<String> oldHtml = getHtmlBeforeAtmosphere(listWeb);
        String myHtml = new String();
        try
        {
            myHtml = oldHtml.get(0);

        } catch (IndexOutOfBoundsException e)
        {
            return null;
        }
        String balise1 = "LLLBERNARD-MIDHTML-BEGIN-LLL";
        String balise2 = "LLLBERNARD-MIDHTML-END-LLL";
        int indexB = myHtml.indexOf(balise1);
        int indexE = myHtml.indexOf(balise2);

        while (  (indexB >= 0) && (indexE >= 0) )
        {
            String currentHtml = myHtml.substring(indexB , indexE);
            currentHtml =    currentHtml.replaceAll(balise1, "");
            listHtml.add(currentHtml);
            indexB = myHtml.indexOf(balise1, indexB + 1);
            indexE = myHtml.indexOf(balise2 , indexE +1);
        }

//        System.out.println("ListHtml " + listHtml);
        
        return listHtml;


    }

    public static ArrayList<String> getHtmlBeforeAtmosphere(String listWeb)
    {
//    	System.out.println("ListWeb " + listWeb);
        ArrayList<String> pageHtml = new ArrayList<>();
        String balise1 = "LLLBERNARD-ALLHTML-BEGIN-LLL";
        String balise2 = "LLLBERNARD-ALLHTML-END-LLL";
        int indexB = listWeb.indexOf(balise1);
        int indexE = listWeb.indexOf(balise2);

        while (  (indexB >= 0) && (indexE >= 0) )
        {
            String currentHtml = listWeb.substring(indexB , indexE);
            currentHtml =    currentHtml.replaceAll(balise1, "");
            pageHtml.add(currentHtml);
            indexB = listWeb.indexOf(balise1, indexB + 1);
            indexE = listWeb.indexOf(balise2 , indexE +1);
        }

//        System.out.println("GetHtml " + pageHtml);
        return pageHtml;
    }



	public static ArrayList<String> getHtmlWithAtmosphere(String url)
    {
		String base = "https://readingmood-239210.appspot.com/treatepub/https://";
		ArrayList<String> listAtmosphere = new ArrayList<>();
        ArrayList<String> listHtml = new ArrayList<>();
        System.out.println(base+url);
        ArrayList<String> listBefore = getHtmlWithoutAtmosphere(getDataFromUrl(base+url));
        String balise1 = "LLLBERNARD-BEGINAMBIANCE-";
        String baliseI = "-LLL";
        String balise2 = "LLLBERNARD-ENDOFAMBIANCE-LLL";

        try
        {
            for (String x : listBefore)
            {

                try
                {
                    int indexB = x.indexOf(balise1);
                    int indexInter = x.indexOf(baliseI);
                    int indexE = x.indexOf(balise2);
                    String currentAtmosphere = x.substring(indexB,indexInter).replace(balise1,"");
                    String currentHtml = x.substring(indexInter,indexE).replace(baliseI, "");
                    listAtmosphere.add(currentAtmosphere);
                    listHtml.add(currentHtml);
//                listRealAtmosphere.add(atmosphereByName.getAtmosphereFromTitle(currentAtmosphere));

                } catch (RuntimeException e)
                {
                    
                }

            }

        } catch (RuntimeException e)
        {
        }

        return listAtmosphere;

    }
	
	
	public static ArrayList<ArrayList<String>> getAtmosphereFromLinks(ArrayList<String> links)
	{
		ArrayList<ArrayList<String>> listResult = new ArrayList<ArrayList<String>>();
		for (String x : links)
		{
			ArrayList<String> prov = getHtmlWithAtmosphere(x);
			listResult.add(prov);
			
		}
		
		return listResult;
	}
	
	public static float getAverageNumber(ArrayList<ArrayList<String>> listAtmo)
	{
		int average = 0;
		int n = listAtmo.size();
		for (ArrayList<String> x : listAtmo)
		{
			average += x.size();
		}
		System.out.println("Get Average " + average);
//		"43201"
		return (float) 1.0 * average / n;
	}
	
	
	public static ArrayList<String> convertToArray(String[] liste)
	{
		ArrayList<String> new_list = new ArrayList<String>();
		for (int i = 0 ; i < liste.length ; i ++)
		{
			new_list.add(liste[i]);
		}
		return new_list;
		
	}
	
	
	
	public static Integer[] getAverageFromList(ArrayList<ArrayList<String>> listAtmo)
	{
		String[] listTitle ={ "Admnistration", "Anger", "Attic", "Bathroom",
	            "Bedroom", "Calm", "Car", "Castle", "Cave", "Cemetery",
	            "Church", "Circus","Company", "Country Town","Desert", "Fear",
	            "Field", "Fight","Fog", "Forest", "Garden", "Gunshot","Gym","Happy","Hospital", "Industry", "Jail",
	            "Kitchens", "Library", "LivingRoom", "Love","Meadow", "Mine", "Mountain","Ocean",
	            "Plane","Rain", "Sad", "School", "Snow","Street","Sun", "Thunder", "TrainStation", "Wind"};
		ArrayList<String> newListTitle = convertToArray(listTitle);
		Integer[] indexAtmosphere = new Integer[listTitle.length];

		try {

			for (ArrayList<String> x : listAtmo)
			{
				for (String y : x)
				{
					int index = newListTitle.indexOf(y);
					if ( index != -1)
					{
						indexAtmosphere[index] += 1;
					}
				}
			}

		} catch (NullPointerException e)
		{
			
		}
		
	
	for (int i = 0; i < indexAtmosphere.length ; i ++ )
	{
		System.out.println("For atmosphere : " + listTitle[i] + " we have : " + indexAtmosphere[i]);
	}
		
		return indexAtmosphere;
		
	}
	
	
}
