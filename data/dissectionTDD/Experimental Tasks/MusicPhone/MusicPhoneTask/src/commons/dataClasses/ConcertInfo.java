
/*** DO NOT CHANGE THE EXISTING BEHAVIOR OF THIS CLASS ***/

package commons.dataClasses;
import java.util.*;

// info about a concert event listing //
public class ConcertInfo {

	private String privateArtist;
	private String privateCity;
	private String privateVenue;
	private Date privateStartDate = new Date(0);
	private GeoPoint privatePosition;
	
	
	public final String getArtist()
	{
		return privateArtist;
	}
	
	public final void setArtist(String value)
	{
		privateArtist = value;
	}
	
	public final String getCity()
	{
		return privateCity;
	}
	
	public final void setCity(String value)
	{
		privateCity = value;
	}
	
	public final String getVenue()
	{
		return privateVenue;
	}
	
	public final void setVenue(String value)
	{
		privateVenue = value;
	}
	
	public final Date getStartDate()
	{
		return privateStartDate;
	}
	
	public final void setStartDate(Date value)
	{
		privateStartDate = value;
	}
	
	public final GeoPoint getPosition()
	{
		return privatePosition;
	}
	
	public final void setPosition(GeoPoint value)
	{
		privatePosition = value;
	}

	public ConcertInfo(String artist, String city, String venue, Date startDate, GeoPoint position)
	{
		setArtist(artist);
		setCity(city);
		setVenue(venue);
		setStartDate(startDate);
		setPosition(position);
	}
	

}
