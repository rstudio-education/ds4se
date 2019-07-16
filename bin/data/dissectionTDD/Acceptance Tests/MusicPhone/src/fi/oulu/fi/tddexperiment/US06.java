package fi.oulu.fi.tddexperiment;

import static org.junit.Assert.*;
import gps.Gps;

import java.io.File;
import java.io.FilenameFilter;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.junit.Before;
import org.junit.Test;

import player.Player;
import commons.DeviceManager;
import commons.Recommender;
import commons.dataClasses.Destination;
import commons.dataClasses.GeoPoint;
import commons.dataClasses.Recommendation;
import commons.interfaces.IGps;
import commons.interfaces.IPlayer;
import dataConnectors.LastFmXmlConnector;
import static fi.oulu.fi.tddexperiment.Commons.*;
public class US06 {
	private Method computeDistanceImplementation = null;
	private Method getDestinationsForArtistsImplementation = null;
	private Method getRecommendationsImplementation = null;
	private Method buildItineraryForArtistsImplementation = null;
	private Recommender recommender = null;
	private IPlayer player = null;
	private IGps gps = null;
	private String computeDistanceExceptions;
	private String getDestinationsForArtistsExceptions;
	private String getRecommendationsExceptions;
	private String buildItineraryForArtistsExceptions;
	private double delta = 0.01;
	private Object reflection_parameter;
	private String whichPackageName = whichPackage;
	@Before
	public void setUp() {
		this.recommender = new Recommender(new LastFmXmlConnector());
		this.player = new Player();
		this.gps = new Gps();
		this.gps.setDistanceUnits("KM");
		this.gps.setCurrentPosition(new GeoPoint("0", "0"));

		//IF computeDistance is not in "commons", the first parameter for getUniqueImplementation below, needs to be changed manually...
		
		this.computeDistanceImplementation = getUniqueImplementation(whichPackageName,"computeDistance", new Class[]{GeoPoint.class, GeoPoint.class, String.class});
		this.computeDistanceExceptions = getExceptionListFromMethodSignature(computeDistanceImplementation);

		if(computeDistanceImplementation.toString().contains("static"))
			reflection_parameter = null;
		else
		//IF computeDistance is not static, reflection parameter should be set to the object implementing it, so that it is properly instantiated.
			reflection_parameter = recommender;
		this.getDestinationsForArtistsImplementation = getUniqueImplementation(whichPackageName, "getDestinationsForArtists", new Class[]{String.class});
		this.getDestinationsForArtistsExceptions = getExceptionListFromMethodSignature(getDestinationsForArtistsImplementation);

		this.getRecommendationsImplementation = getUniqueImplementation(whichPackageName, "getRecommendations", new Class[]{});
		this.getRecommendationsExceptions = getExceptionListFromMethodSignature(getRecommendationsImplementation);


		this.buildItineraryForArtistsImplementation = getUniqueImplementation(whichPackageName, "buildItineraryForArtists", new Class[]{List.class});
		this.buildItineraryForArtistsExceptions = getExceptionListFromMethodSignature(buildItineraryForArtistsImplementation);
	}
	
	@Test
	public void sad_GetDestinationsForExistingArtistWithNoConcerts()
	{
		try{
			List<Destination> destinations = recommender.getDestinationsForArtists("Cher");
			passIfListIsNullOrItsListSizeIsZero(destinations);
		}
		catch(Exception e) 
		{
			passIfExceptionIsDefinedInMethodSignatureFailOtherwise(getDestinationsForArtistsExceptions, e);
		}
	}
	
	
	@Test
	public void sad_GetDestinationsForNonExistingArtist()
	{
		try{
			List<Destination> destinations = recommender.getDestinationsForArtists("Somebody Somebody");
			passIfListIsNullOrItsListSizeIsZero(destinations);
		}
		catch(Exception e) 
		{
			passIfExceptionIsDefinedInMethodSignatureFailOtherwise(getDestinationsForArtistsExceptions, e);
		}	
	}
	

	@Test
	public void sad_GetDestinationsReturnsNullDistanceWhenConcertPositionNotAvailable()
	{
		try
		{
			List<Destination> destinations = recommender.getDestinationsForArtists("Bob Dylan");
			assertNotNull(destinations);
			Destination venue = findInDestinations(destinations, "The O2");
			assertNotNull(venue);
			double d = venue.getDistance();
			passIfNegativeOrNull(d);		
		} catch(Exception e){
			passIfExceptionIsDefinedInMethodSignatureFailOtherwise(getDestinationsForArtistsExceptions, e);
		}
	}
	
	@Test
	public void sad_GetDestinationsReturnsNullDistanceWhenPositionIsInvalid()
	{
		gps.setCurrentPosition(null);
		try{
			List<Destination> destinations = recommender.getDestinationsForArtists("Metallica");
			Destination venue = findInDestinations(destinations, "Hartwall Areena");
			assertNotNull(venue);
			double d = venue.getDistance();
			passIfNegativeOrNull(d);		
		} catch(Exception e){
			passIfExceptionIsDefinedInMethodSignatureFailOtherwise(getDestinationsForArtistsExceptions, e);
		}

	}
	
	@Test
	public void sad_GetDestinationsWhenDistanceUnitIsNull()
	{
		gps.setCurrentPosition(new GeoPoint("60", "60"));
		gps.setDistanceUnits(null);

		try{
			List<Destination> destinations = recommender.getDestinationsForArtists("In Flames");
			passIfListIsNullOrItsListSizeIsZero(destinations);
		} catch(Exception e){	
			passIfExceptionIsDefinedInMethodSignatureFailOtherwise(getDestinationsForArtistsExceptions, e);
		}
	}
	
	@Test
	public void sad_GetDestinationsWhenDistanceUnitIsUndefined()
	{
		gps.setCurrentPosition(new GeoPoint("60", "60"));
		gps.setDistanceUnits("");

		try{
			List<Destination> destinations = recommender.getDestinationsForArtists("In Flames");
			passIfListIsNullOrItsListSizeIsZero(destinations);
		} catch(Exception e){	
			passIfExceptionIsDefinedInMethodSignatureFailOtherwise(getDestinationsForArtistsExceptions, e);
		}
	}
	

	@Test
	public void sad_GetDestinationsWhenGpsIsUndefined()
	{
		DeviceManager.getInstance().setGps(null);
		try
		{
			List<Destination> destinations = recommender.getDestinationsForArtists("Bob Dylan");
			passIfListIsNullOrItsListSizeIsZero(destinations);
		} catch(Exception e){

			passIfExceptionIsDefinedInMethodSignatureFailOtherwise(getDestinationsForArtistsExceptions, e);
		}
	}
	
	@Test
	public void sad_GetDestinationsWhenVenuePointIsUndefined()
	{
		gps.setCurrentPosition(null);

		try
		{
			List<Destination> destinations = recommender.getDestinationsForArtists("Bob Dylan");
			Destination venue = findInDestinations(destinations, "Forum");
			assertNotNull(venue);
			double d = venue.getDistance();
			passIfNegativeOrNull(d);		
		} catch(Exception e){
			passIfExceptionIsDefinedInMethodSignatureFailOtherwise(getDestinationsForArtistsExceptions, e);
		}
	}



}
