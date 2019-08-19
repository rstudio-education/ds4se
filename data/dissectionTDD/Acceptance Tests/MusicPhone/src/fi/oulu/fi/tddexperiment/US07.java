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
public class US07 {
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
	public void happy_GetRecommendationsCanUseTheXMLConnector()
	{
		player.setCurrentArtist("Cher");

		try
		{
			List<Recommendation> recommendations = recommender.getRecommendations();
			assertNotNull(recommendations);
			assertEquals(20, recommendations.size());
		} catch(Exception e)
		{
			fail("I was not expecting an exception: " + e.getClass().toString());
		}
	}
	
	@Test
	public void happy_CheckRecommendationsForArtistDoNotContainWrongArtists()
	{

		player.setCurrentArtist("Metallica");
		try{
			List<Recommendation> recommendations = recommender.getRecommendations();
			assertNotNull("I was expecting some recommendations, not null...", recommendations);
			assertNull(findInRecommendation(recommendations, "Cher"));
			assertNull(findInRecommendation(recommendations, "ABBA"));
			assertNull(findInRecommendation(recommendations, "Céline Dion"));
			assertNull(findInRecommendation(recommendations, "Tina Turner"));
			assertNull(findInRecommendation(recommendations, "Madonna"));
			assertNull(findInRecommendation(recommendations, "Christina Aguilera"));
		} catch(Exception	 e){
			fail("I was not expecting an exception: " + e.getClass().toString());
		}	
	}
	
	@Test
	public void happy_GetRecommendationsForArtistWithStrictlyLoyalFans()
	{
		player.setCurrentArtist("ABBA");

		try{
			List<Recommendation> recommendations = recommender.getRecommendations();
			assertNotNull(recommendations);
			assertEquals(1, recommendations.size());
			assertEquals(player.getCurrentArtist(), recommendations.get(0).getArtist());			
		} catch(Exception e){
			fail("I was not expecting an exception: " + e.getClass().toString());
		}
	}
	
	@Test
	public void sad_GetRecommendationsWhenArtistIsEmpty()
	{
		player.setCurrentArtist("");

		try{
			List<Recommendation> recommendations = recommender.getRecommendations();
			passIfListIsNullOrItsListSizeIsZero(recommendations);
		} catch(Exception e) 
		{
			passIfExceptionIsDefinedInMethodSignatureFailOtherwise(getRecommendationsExceptions, e);
		}
	}
	
	@Test
	public void sad_GetRecommendationsWhenPlayerIsNotPlayingAnythingOrArtistIsNull()
	{
		player.setCurrentArtist(null);
		try{
			List<Recommendation> recommendations = recommender.getRecommendations();
			passIfListIsNullOrItsListSizeIsZero(recommendations);
		}
		catch(Exception e) 
		{
			passIfExceptionIsDefinedInMethodSignatureFailOtherwise(getRecommendationsExceptions, e);
		}

	}
	
	@Test
	public void sad_GetRecommendationsForNonExistingArtist()
	{
		player.setCurrentArtist("Nobody");
		try{
			List<Recommendation> recommendations = recommender.getRecommendations();
			passIfListIsNullOrItsListSizeIsZero(recommendations);
		} catch(Exception e) 
		{
			passIfExceptionIsDefinedInMethodSignatureFailOtherwise(getRecommendationsExceptions, e);
		}
	}
	
	@Test
	public void happy_GetRecommendationsForArtistWithFewFansHavingFewTopArtists()
	{
		player.setCurrentArtist("Coldplay");
		try{
			List<Recommendation> recommendations = recommender.getRecommendations();
			assertNotNull(recommendations);
			assertEquals(3, recommendations.size());			
		} catch(Exception e){
			fail("I was not expecting an exception: " + e.getClass().toString());
		}
	}


}
