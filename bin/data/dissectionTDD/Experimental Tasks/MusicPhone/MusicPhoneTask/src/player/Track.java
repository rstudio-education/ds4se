package player;

public final class Track
{
	private String artist;
	private String title;

	public Track()
	{
	}

	public Track(String artist, String title)
	{
		this.artist = artist;
		this.title = title;
	}

	public Track clone()
	{
		Track varCopy = new Track();

		varCopy.artist = this.artist;
		varCopy.title = this.title;

		return varCopy;
	}

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
}