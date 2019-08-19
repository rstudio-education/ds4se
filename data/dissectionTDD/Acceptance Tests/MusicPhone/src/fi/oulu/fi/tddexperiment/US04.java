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

public class US04 {
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
		this.getRecommendationsExceptions =data. getExceptionListFromMethodSignature(getRecommendationsImplementation);


		this.buildItineraryForArtistsImplementation =data. getUniqueImplementation(whichPackageName, "buildItineraryForArtists", new Class[]{List.class});
		this.buildItineraryForArtistsExceptions = data.getExceptionListFromMethodSignature(buildItineraryForArtistsImplementation);
	}
	
	@Test
	public void sad_CanComputeDistanceWithBadGeoPoints()
	{

		try
		{
			double d = (Double) computeDistanceImplementation.invoke(reflection_parameter,new GeoPoint("360", "-360"), new GeoPoint("-360", "360"), "km");
			if(d == 0) {
				assertTrue(true);
				return;
			}
			data.passIfNegativeOrNull(d);
		}
		catch(Exception e) 
		{
			data.passIfExceptionIsDefinedInMethodSignatureFailOtherwise(computeDistanceExceptions, e);
		}

	}
	
	@Test
	public void sad_ComputeDistanceWithNullGeoPoint()
	{

		try
		{
			double d = (Double) computeDistanceImplementation.invoke(reflection_parameter,null, new GeoPoint("0", "0"), "km");
			data.passIfNegativeOrNull(d);
		}
		catch(Exception e) 
		{
			data.passIfExceptionIsDefinedInMethodSignatureFailOtherwise(computeDistanceExceptions, e);
		}
	}
	
	
	@Test
	public void sad_ComputeDistanceWithUndefinedGeoPointCoordinates()
	{

		try
		{
			double d = (Double) computeDistanceImplementation.invoke(reflection_parameter,new GeoPoint("", "0"), new GeoPoint("0", ""), "km");
			data.passIfNegativeOrNull(d);
		}
		catch(Exception e) 
		{
			data.passIfExceptionIsDefinedInMethodSignatureFailOtherwise(computeDistanceExceptions, e);
		}
	}

	@Test
	public void sad_ComputeDistanceWithUnspecifiedDistanceUnits()
	{

		try
		{
			double d = (Double) computeDistanceImplementation.invoke(reflection_parameter,null, new GeoPoint("0", ""), "");
			data.passIfNegativeOrNull(d);
		}
		catch(Exception e) 
		{
			data.passIfExceptionIsDefinedInMethodSignatureFailOtherwise(computeDistanceExceptions, e);
		}
	}

}
