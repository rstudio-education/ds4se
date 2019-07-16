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
public class US10 {
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
	public void happy_CheckRecommendationsForArtistWithLotsofFansHaveRightFanCounts()
	{

		player.setCurrentArtist("Cher");
		try{
			List<Recommendation> recommendations = recommender.getRecommendations();
			assertNotNull("I was expecting some recommendations, not null...", recommendations);
			assertEquals(12, findInRecommendation(recommendations, "Anastacia").getFanCount());
			assertEquals(13, findInRecommendation(recommendations, "Tina Turner").getFanCount());
			assertEquals(23, findInRecommendation(recommendations, "Christina Aguilera").getFanCount());
			assertEquals(24, findInRecommendation(recommendations, "Madonna").getFanCount());
			assertEquals(15, findInRecommendation(recommendations, "Whitney Houston").getFanCount());

		} catch(Exception e){
			fail("I was not expecting an exception: " + e.getClass().toString());
		}
	}
	
	@Test
	public void happy_CheckRecommendationsForArtistWithFewFansHavingFewTopArtistsHaveRightFanCounts()
	{
		player.setCurrentArtist("Coldplay");
		try{
			List<Recommendation> recommendations = recommender.getRecommendations();
			assertEquals(3, recommendations.size());
			assertNotNull(findInRecommendation(recommendations, "Cher"));
			assertEquals(1, findInRecommendation(recommendations, "Cher").getFanCount());
			assertNotNull(findInRecommendation(recommendations, "ABBA"));
			assertEquals(2, findInRecommendation(recommendations, "ABBA").getFanCount());
			assertNotNull(findInRecommendation(recommendations, "Coldplay"));
			assertEquals(1, findInRecommendation(recommendations, "Coldplay").getFanCount());

		} catch(Exception e){
			fail("I was not expecting an exception: " + e.getClass().toString());
		}
	}


}
