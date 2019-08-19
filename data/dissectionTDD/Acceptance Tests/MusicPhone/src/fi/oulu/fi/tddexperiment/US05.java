package fi.oulu.fi.tddexperiment;

import static org.junit.Assert.*;
import gps.Gps;

import java.io.File;
import java.io.FilenameFilter;
import java.lang.reflect.Method;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.junit.Before;
import org.junit.Ignore;
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

public class US05 {
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
	private Commons data = new Commons();
	String whichPackageName = data.whichPackage;
	@Before
	public void setUp() {
		this.recommender = new Recommender(new LastFmXmlConnector());
		this.player = new Player();
		this.gps = new Gps();
		this.gps.setDistanceUnits("KM");
		this.gps.setCurrentPosition(new GeoPoint("0", "0"));

		//IF computeDistance is not in "commons", the first parameter for getUniqueImplementation below, needs to be changed manually...
		
		this.computeDistanceImplementation = data.getUniqueImplementation(whichPackageName,"computeDistance", new Class[]{GeoPoint.class, GeoPoint.class, String.class});
		this.computeDistanceExceptions = data.getExceptionListFromMethodSignature(computeDistanceImplementation);

		if(computeDistanceImplementation.toString().contains("static"))
			reflection_parameter = null;
		else
		//IF computeDistance is not static, reflection parameter should be set to the object implementing it, so that it is properly instantiated.
			reflection_parameter = recommender;
		this.getDestinationsForArtistsImplementation =data. getUniqueImplementation(whichPackageName, "getDestinationsForArtists", new Class[]{String.class});
		this.getDestinationsForArtistsExceptions = data.getExceptionListFromMethodSignature(getDestinationsForArtistsImplementation);

		this.getRecommendationsImplementation =data. getUniqueImplementation(whichPackageName, "getRecommendations", new Class[]{});
		this.getRecommendationsExceptions = data.getExceptionListFromMethodSignature(getRecommendationsImplementation);


		this.buildItineraryForArtistsImplementation =data. getUniqueImplementation(whichPackageName, "buildItineraryForArtists", new Class[]{List.class});
		this.buildItineraryForArtistsExceptions =data. getExceptionListFromMethodSignature(buildItineraryForArtistsImplementation);
	}
	
	@Test
	public void happy_GetDestinationsCanUseTheXMLConnector()
	{
		try
		{
			List<Destination> destinations = recommender.getDestinationsForArtists("Britney Spears");
			assertNotNull(destinations);
		} catch(Exception e) {
			e.printStackTrace();
			fail("I was not expecting an exception: " + e.getClass().toString());
		}
	}
	
	@Test
	public void happy_GetDestinationsHasCorrectNumberOfDestinations()
	{
		try{
			List<Destination> destinations = recommender.getDestinationsForArtists("Cyndi Lauper");
			assertEquals(5, destinations.size());		
		} catch(Exception e){
			fail("I was not expecting an exception: " + e.getClass().toString());
		}
	}
	
	@Test
	public void happy_GetDestinationsReturnsConcertsRightVenuesInRightOrder() 
	{
		try{
			List<Destination> destinations = recommender.getDestinationsForArtists("Franz Ferdinand");
			assertTrue("Returned less than 5 destinations...", destinations.size() > 4);
			assertEquals("L'Olympia", destinations.get(0).getVenue());
			assertEquals("Store Vega", destinations.get(1).getVenue());
			assertEquals("Cirkus", destinations.get(2).getVenue());
			assertEquals("Sentrum Scene", destinations.get(3).getVenue());
			assertEquals("Docks", destinations.get(4).getVenue());

		} catch(Exception e){
			fail("I was not expecting an exception: " + e.getClass().toString());
		}
	}
	
	@Test
	public void happy_GetDestinationsReturnsCorrectCity()
	{
		try{
			List<Destination> destinations = recommender.getDestinationsForArtists("Iron Maiden");
			assertNotNull(data.findInDestinations(destinations, "Brasilia Camping"));
			assertNotNull(data.findInDestinations(destinations, "Mineirinho"));

			assertEquals("Brasilia", data.findInDestinations(destinations, "Brasilia Camping").getCity());
			assertEquals("Santiago", data.findInDestinations(destinations, "Club Hípico").getCity());
		} catch(Exception e){
			fail("I was not expecting an exception: " + e.getClass().toString());
		}
	}
	
	@Test
	public void happy_GetDestinationsReturnsCorrectDistanceToVenue()
	{
		gps.setCurrentPosition(new GeoPoint("56", "-80"));
		try{
			List<Destination> destinations = recommender.getDestinationsForArtists("Coldplay");
			Destination venue = data.findInDestinations(destinations, "Vector Arena");
			assertNotNull(venue);
			assertEquals(14224.98, venue.getDistance(), delta);
		} catch(Exception e){
			fail("I was not expecting an exception: " + e.getClass().toString());
		}
	}

	@Test
	public void happy_GetDestinationsReturnsCorrectStartDate()
	{
		SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd");

		try{
			List<Destination> destinations = recommender.getDestinationsForArtists("Iron Maiden");
			assertNotNull(destinations);
			assertTrue("I was expecting: 2009-03-20, but returned: " + data.findInDestinations(destinations, "Brasilia Camping").getStartDate().toString(), ft.parse("2009-03-20")
					.equals(data.findInDestinations(destinations, "Brasilia Camping").getStartDate()));
			assertTrue("I was expecting: 2009-03-18, but returned: " + data.findInDestinations(destinations, "Mineirinho")
					.getStartDate().toString(), ft.parse("2009-03-18").equals(data.findInDestinations(destinations, "Mineirinho").getStartDate()));

		} catch(Exception e){
			fail("I was not expecting an exception: " + e.getClass().toString());
		}
	}
	
	@Test
	public void happy_GetDestinationsReturnsCorrectVenues()
	{
		try{
			List<Destination> destinations = recommender.getDestinationsForArtists("Iron Maiden");
			assertNotNull(data.findInDestinations(destinations, "Mineirinho"));
			assertNotNull(data.findInDestinations(destinations, "Brasilia Camping"));
			assertNotNull(data.findInDestinations(destinations, "Club Hípico"));
			assertNotNull(data.findInDestinations(destinations, "Estadio Nacional"));
			assertNotNull(data.findInDestinations(destinations, "Verizon Wireless Amphitheater"));
		} catch(Exception e){
			fail("I was not expecting an exception: " + e.getClass().toString());
		}
	}
	
	@Test
	public void happy_GetDestinationsSetsArtistCorrectly()
	{
		gps.setCurrentPosition(new GeoPoint("60", "60"));

		try{
			List<Destination> destinations = recommender.getDestinationsForArtists("Britney Spears");
			assertEquals("Britney Spears", destinations.get(0).getArtist());
		} catch(Exception e){
			fail("I was not expecting an exception: " + e.getClass().toString());
		}
	}
	
	@Test
	public void happy_GetDestinationsSetsPositionCorrectly()
	{
		try{
			List<Destination> destinations = recommender.getDestinationsForArtists("Amy Winehouse");
			assertEquals(new Double("51.50"), new Double(destinations.get(0).getPosition().getLatitude()), delta);
			assertEquals(new Double("-0.22"), new Double(destinations.get(0).getPosition().getLongitude()), delta);
		} catch(Exception e){
			fail("I was not expecting an exception: " + e.getClass().toString());		}
	}
	
	
	@Test
	public void happy_GetDestinationsWhenDistanceUnitIsDefined()
	{
		gps.setCurrentPosition(new GeoPoint("60", "60"));
		gps.setDistanceUnits("mi");

		try{
			List<Destination> destinations = recommender.getDestinationsForArtists("In Flames");
			assertEquals("mi", destinations.get(0).getDistanceUnits());	
		} catch(Exception e){

			fail("I was not expecting an exception");
		}
	}

	


}
