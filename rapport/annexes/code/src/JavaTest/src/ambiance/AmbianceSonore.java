package ambiance;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

public class AmbianceSonore extends Ambiance{

	
	public AmbianceSonore(String path) {
		super(path);
		
	}

	public void play()
	{
		try
		{
			File file = new File(this.path);
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
			
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		
	}
	
	public String getPath()
	{
		return path;
	}
	
	
	}
	

