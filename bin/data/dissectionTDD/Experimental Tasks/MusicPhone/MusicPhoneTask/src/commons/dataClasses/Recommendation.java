/*** DO NOT CHANGE THE EXISTING BEHAVIOR OF THIS CLASS ***/

package commons.dataClasses;

public class Recommendation {
	
    /// a recommendation consists of an artist name weighted by the number of fans who have that artist as a top artist
	private String artist;
	private int fanCount;
	public Recommendation(String artist, int fanCount) {
		super();
		this.artist = artist;
		this.fanCount = fanCount;
	}
	public String getArtist() {
		return artist;
	}
	public void setArtist(String artist) {
		this.artist = artist;
	}
	public int getFanCount() {
		return fanCount;
	}
	public void setFanCount(int fanCount) {
		this.fanCount = fanCount;
	}
}
