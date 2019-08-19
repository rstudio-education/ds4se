package commons.dataClasses;

public class GeoPoint {
	
	private String latitude;
	private String longitude;
	
	public GeoPoint(String latitude, String longitude) {
		super();
		this.latitude = latitude;
		this.longitude = longitude;
	}
	
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
}
