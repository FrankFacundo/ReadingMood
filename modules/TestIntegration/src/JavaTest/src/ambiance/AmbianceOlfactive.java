package ambiance;

public class AmbianceOlfactive extends Ambiance {

	private String title;
	
	
	public AmbianceOlfactive(String path, String title) {
		super(path);
		this.title = title;

	}

	public String getTitle()
	{
		return title;
	}
	
	@Override
	public void play() {
	
	}
	
	
	
}
