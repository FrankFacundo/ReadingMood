package ambiance;

public abstract class Ambiance {
	
	protected String path;
	
	public Ambiance(String path)
	{
		this.path = path;
	}
	
	public abstract void play();
	/*
	 * It will display the atmosphere
	 */
	
	
}
