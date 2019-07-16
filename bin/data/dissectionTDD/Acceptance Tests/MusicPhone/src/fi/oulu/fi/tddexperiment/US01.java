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
import org.junit.Ignore;
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
public class US01 {
	
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
		this.computeDistanceExceptions =getExceptionListFromMethodSignature(computeDistanceImplementation);

		if(computeDistanceImplementation.toString().contains("static"))
			reflection_parameter = null;
		else
		//IF computeDistance is not static, reflection parameter should be set to the object implementing it, so that it is properly instantiated.
			reflection_parameter = recommender;
		this.getDestinationsForArtistsImplementation =data. getUniqueImplementation(whichPackageName, "getDestinationsForArtists", new Class[]{String.class});
		this.getDestinationsForArtistsExceptions = data.getExceptionListFromMethodSignature(getDestinationsForArtistsImplementation);

		this.getRecommendationsImplementation =data. getUniqueImplementation(whichPackageName, "getRecommendations", new Class[]{});
		this.getRecommendationsExceptions =data.getExceptionListFromMethodSignature(getRecommendationsImplementation);


		this.buildItineraryForArtistsImplementation = data.getUniqueImplementation(whichPackageName, "buildItineraryForArtists", new Class[]{List.class});
		this.buildItineraryForArtistsExceptions = data.getExceptionListFromMethodSignature(buildItineraryForArtistsImplementation);
	}
	@Test
	public void diagnostic_ThereExistsAClassImplementingComputeDistanceMethodWithTheProvidedSignature() {
		assertNotNull("SEE COMMENTS IN Test Fixture: Setup...computeDistance is not implemented!", computeDistanceImplementation);
	}
	

	@Test
	public void diagnostic_ThereExistsAClassImplementingGetDestinationsForArtistsMethodWithTheProvidedSignature() {
		assertNotNull("SEE COMMENTS IN Test Fixture: Setup...getDestinationsForArtists is not implemented with the provided signature!", getDestinationsForArtistsImplementation);
	}

	@Test
	public void diagnostic_ThereExistsAClassImplementingGetRecommendationsMethodWithTheProvidedSignature() {
		assertNotNull("SEE COMMENTS IN Test Fixture: Setup...getRecommendations is not implemented with the provided signature!", getRecommendationsImplementation);
	}

	@Test
	public void diagnostic_ThereExistsAClassImplementingBuildItineraryForArtistsMethodWithTheProvidedSignature() {
		assertNotNull("SEE COMMENTS IN Test Fixture: Setup...buildItineraryForArtists is not implemented with the provided signature!", buildItineraryForArtistsImplementation);
	}


}
