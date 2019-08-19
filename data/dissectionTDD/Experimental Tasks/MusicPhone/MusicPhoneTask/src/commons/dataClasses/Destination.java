
/*** DO NOT CHANGE THE EXISTING BEHAVIOR OF THIS CLASS ***/

package commons.dataClasses;

import java.util.*;

public class Destination {
	
	private ConcertInfo concertInfo; // a musical event listing //
	private Double privateDistance;
	private String privateDistanceUnits;

	
	public Destination(String artist, String city, String venue, java.util.Date startDate, GeoPoint loc)
	{
		concertInfo = new ConcertInfo(artist, city, venue, startDate, loc);
	}

	public Destination(ConcertInfo concertInfo)
	{
		this.concertInfo = concertInfo;
	}

	public final String getArtist()
	{
		return concertInfo.getArtist();
	}

	public final String getCity()
	{
		return concertInfo.getCity();
	}

	public final String getVenue()
	{
		return concertInfo.getVenue();
	}

	public final Date getStartDate()
	{
		return concertInfo.getStartDate();
	}

	public final GeoPoint getPosition()
	{
		return concertInfo.getPosition();
	}

	public final Double getDistance()
	{
		return privateDistance;
	}
	
	public final void setDistance(Double value)
	{
		privateDistance = value;
	}

	public final String getDistanceUnits()
	{
		return privateDistanceUnits;
	}
	
	public final void setDistanceUnits(String value)
	{
		privateDistanceUnits = value;
	}
	
	public void setPosition(GeoPoint point){
		concertInfo.setPosition(point);
	}
}
