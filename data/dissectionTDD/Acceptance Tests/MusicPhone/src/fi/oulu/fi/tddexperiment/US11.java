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
import commons.Recommender;
import commons.dataClasses.Destination;
import commons.dataClasses.GeoPoint;
import commons.dataClasses.Recommendation;
import commons.interfaces.IGps;
import commons.interfaces.IPlayer;
import dataConnectors.LastFmXmlConnector;
import static fi.oulu.fi.tddexperiment.Commons.*;
public class US11 {
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
	public void happy_CheckRecommendationsForArtistWithLotsOfFansHaveRightArtists()
	{
		player.setCurrentArtist("U2");
		try{
			List<Recommendation> recommendations = recommender.getRecommendations();
			assertNotNull("I was expecting some recommendations, not null...", recommendations);
			assertNotNull(findInRecommendation(recommendations, "U2"));
			assertNotNull(findInRecommendation(recommendations, "Radiohead"));
			assertNotNull(findInRecommendation(recommendations, "Moby"));
			assertNotNull(findInRecommendation(recommendations, "Pink Floyd"));
			assertNotNull(findInRecommendation(recommendations, "The Beatles"));
			assertNotNull(findInRecommendation(recommendations, "The Who"));
			assertNotNull(findInRecommendation(recommendations, "Muse"));
		} catch(Exception e){
			fail("I was not expecting an exception: " + e.getClass().toString());
		}
	}
	@Test
	public void happy_CheckRecommendationsForArtistWithLotsOfFansHaveRightArtistsWithRightFanCounts()
	{
		player.setCurrentArtist("Metallica");
		try{
			List<Recommendation> recommendations = recommender.getRecommendations();
			assertNotNull("I was expecting some recommendations, not null...", recommendations);
			assertNotNull(findInRecommendation(recommendations, "AC/DC"));
			assertEquals(19, findInRecommendation(recommendations, "Iron Maiden").getFanCount());
			assertNotNull(findInRecommendation(recommendations, "Slayer"));
			assertNotNull(findInRecommendation(recommendations, "Metallica"));
			assertEquals(15, findInRecommendation(recommendations, "Nightwish").getFanCount());

		} catch(Exception e){

		}	

	}
	
	@Test
	public void happy_CheckRecommendationsForArtistWithFewFansHavingFewTopArtistsHaveRightArtists()
	{	
		player.setCurrentArtist("Coldplay");

		try{
			List<Recommendation> recommendations = recommender.getRecommendations();
			assertNotNull("I was expecting some recommendations, not null...", recommendations);
			assertNotNull("Cher was not found in recommendations...", findInRecommendation(recommendations, "Cher"));
			assertNotNull("ABBA was not found in recommendations...", findInRecommendation(recommendations, "ABBA"));
			assertNotNull(player.getCurrentArtist() + " was not found in recommendations...", findInRecommendation(recommendations, player.getCurrentArtist()));

		} catch(Exception e){
			fail("I was not expecting an exception: " + e.getClass().toString());
		}
	}
	
	@Test
	public void happy_CheckRecommendationsForArtistWithLotsOfFansHaveRighNumberOfArtists()
	{
		player.setCurrentArtist("U2");
		try{
			List<Recommendation> recommendations = recommender.getRecommendations();
			assertNotNull("I was expecting some recommendations, not null...", recommendations);
			assertEquals(20, recommendations.size());
		} catch(Exception e){
			fail("I was not expecting an exception: " + e.getClass().toString());
		}
	}

}
