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

public class AcceptanceTests {

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

	@Before
	public void setUp() {
		this.recommender = new Recommender(new LastFmXmlConnector());
		this.player = new Player();
		this.gps = new Gps();
		this.gps.setDistanceUnits("KM");
		this.gps.setCurrentPosition(new GeoPoint("0", "0"));

		//IF computeDistance is not in "commons", the first parameter for getUniqueImplementation below, needs to be changed manually...
		String whichPackageName = "commons";
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

	// Helper methods


	private Recommendation findInRecommendation(List<Recommendation> recommendations, String artistName){
		Recommendation r = null;
		Iterator<Recommendation> recsIterator = recommendations.iterator();
		while (recsIterator.hasNext()) {
			r = (Recommendation) recsIterator.next();
			if(r.getArtist().equals(artistName))
				return r;
		}	
		return null;
	}

	private Destination findInDestinations(List<Destination> destinations, String venue)
	{
		Destination d;
		Iterator<Destination> destIterator = destinations.iterator();
		while (destIterator.hasNext()) {
			d = (Destination) destIterator.next();
			if(d.getVenue().equals(venue))
				return d;
		}	
		return null;
	}

	private void passIfNegativeOrNull(double d) {
		if(d != (Double) null)
			assertTrue(d < 0.0);
		else	
			assertTrue(true);
	}

	private void passIfListIsNullOrItsListSizeIsZero(List list) {
		if (list != null)
			assertEquals(0, list.size());
		else
			assertTrue(true); 
	}

	private void passIfExceptionIsDefinedInMethodSignatureFailOtherwise(String methodExceptions, Exception e) {
		String cause = StringUtils.substringBefore(ExceptionUtils.getRootCauseMessage(e), ":");

		if(methodExceptions.contains(cause))
			assertTrue(true);
		else
			fail("Thrown an exception that is not declared in the signature: " + cause);
	}

	private String getExceptionListFromMethodSignature(Method method) {
		String exceptions = "";
		try{
			exceptions = StringUtils.substringAfter(method.toString(), "throws");			
		}catch (NullPointerException e){
			;
		}
		return exceptions;
	}

	private Method getUniqueImplementation(String whichPackageName, String methodName, Class[] parameters) {
		String packageName = whichPackageName;
		File[] files = null;
		Method m = null;

		URL root = Thread.currentThread().getContextClassLoader().getResource(packageName.replace(".", "/"));

		try {
			files = new File(root.getFile()).listFiles(new FilenameFilter() {
				public boolean accept(File dir, String name) {
					return name.endsWith(".class");
				}
			});
		} catch (NullPointerException e) {
			fail("Null pointer exception... probably looking into a package that does not exist!");
		}

		for (File file : files) {
			String className = file.getName().replaceAll(".class$", "");
			try {
				Class<?> cls = Class.forName(packageName + "." + className);
				m = cls.getDeclaredMethod(methodName, parameters);
				return m;
			} catch (ClassNotFoundException e) {
				fail("weird... should not fail... list is returned by the system???");
			} catch (NoSuchMethodException e) {				
				;
			} catch (SecurityException e) {
				fail("There is a Security Exception ");
			}
		}
		return null;
	}

	// Diagnostics to check that everything is in place

	@Test
	public void diagnostic_ThereExistsAClassImplementingComputeDistanceMethodWithTheProvidedSignature() {
		assertNotNull("SEE COMMENTS IN Test Fixture: Setup...computeDistance is not implemented!", computeDistanceImplementation);
	}
	@Ignore
	@Test
	public void diagnostic_ThereExistsAClassImplementingComputeDistanceMethodWithTheProvidedStaticSignature() {
		assertTrue("SEE COMMENTS IN Test Fixture: Setup...computeDistance is not implemented as a static method!", computeDistanceImplementation.toString().contains("static"));
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


	// Acceptance tests for Task B

	@Test
	public void happy_CanComputeDistanceWithLowerCaseKmParameterAndValidGeoPoints()
	{

		try
		{
			assertEquals(0.0, (Double) computeDistanceImplementation.invoke(reflection_parameter,new GeoPoint("0", "0"), new GeoPoint("0", "0"), "km"), delta);
			assertEquals(6671.70, (Double) computeDistanceImplementation.invoke(reflection_parameter,new GeoPoint("0", "0"), new GeoPoint("0", "-60"), "km"), delta);
			assertEquals(6671.70, (Double) computeDistanceImplementation.invoke(reflection_parameter,new GeoPoint("0", "0"), new GeoPoint("60", "0"), "km"), delta);
			assertEquals(2886.45, (Double) computeDistanceImplementation.invoke(reflection_parameter,new GeoPoint("36.12", "-86.67"), new GeoPoint("33.94", "-118.40"), "km"), delta);
		}
		catch(Exception e) 
		{
			fail("I was not expecting an exception: " + e.getClass().toString());
		}

	}

	@Test
	public void happy_CanComputeDistanceWithLowerCaseMiParameterAndValidGeoPoints()
	{

		try
		{
			assertEquals(0.0, (Double) computeDistanceImplementation.invoke(reflection_parameter,new GeoPoint("0", "0"), new GeoPoint("0", "0"), "mi"), delta);
			assertEquals(4145.60, (Double) computeDistanceImplementation.invoke(reflection_parameter,new GeoPoint("0", "0"), new GeoPoint("0", "-60"), "mi"), delta);
			assertEquals(4145.60, (Double) computeDistanceImplementation.invoke(reflection_parameter,new GeoPoint("0", "60"), new GeoPoint("0", "0"), "mi"), delta);
			assertEquals(1793.55, (Double) computeDistanceImplementation.invoke(reflection_parameter,new GeoPoint("36.12", "-86.67"), new GeoPoint("33.94", "-118.40"), "mi"), delta);

		}

		catch(Exception e) 
		{
			fail("I was not expecting an exception: " + e.getClass().toString());
		}

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
			passIfNegativeOrNull(d);
		}
		catch(Exception e) 
		{
			passIfExceptionIsDefinedInMethodSignatureFailOtherwise(computeDistanceExceptions, e);
		}

	}

	@Test
	public void sad_ComputeDistanceWithUndefinedGeoPointCoordinates()
	{

		try
		{
			double d = (Double) computeDistanceImplementation.invoke(reflection_parameter,new GeoPoint("", "0"), new GeoPoint("0", ""), "km");
			passIfNegativeOrNull(d);
		}
		catch(Exception e) 
		{
			passIfExceptionIsDefinedInMethodSignatureFailOtherwise(computeDistanceExceptions, e);
		}
	}


	@Test
	public void sad_ComputeDistanceWithNullGeoPoint()
	{

		try
		{
			double d = (Double) computeDistanceImplementation.invoke(reflection_parameter,null, new GeoPoint("0", "0"), "km");
			passIfNegativeOrNull(d);
		}
		catch(Exception e) 
		{
			passIfExceptionIsDefinedInMethodSignatureFailOtherwise(computeDistanceExceptions, e);
		}
	}


	@Test
	public void sad_ComputeDistanceWithUnspecifiedDistanceUnits()
	{

		try
		{
			double d = (Double) computeDistanceImplementation.invoke(reflection_parameter,null, new GeoPoint("0", ""), "");
			passIfNegativeOrNull(d);
		}
		catch(Exception e) 
		{
			passIfExceptionIsDefinedInMethodSignatureFailOtherwise(computeDistanceExceptions, e);
		}
	}

	@Test
	public void sad_CanComputeDistanceWithUpperCaseKmParameterAndValidGeoPoints()
	{

		try
		{
			assertEquals(0.0, (Double) computeDistanceImplementation.invoke(reflection_parameter,new GeoPoint("0", "0"), new GeoPoint("0", "0"), "KM"), delta);
			assertEquals(6671.70, (Double) computeDistanceImplementation.invoke(reflection_parameter,new GeoPoint("0", "0"), new GeoPoint("0", "-60"), "KM"), delta);
			assertEquals(6671.70, (Double) computeDistanceImplementation.invoke(reflection_parameter,new GeoPoint("0", "0"), new GeoPoint("60", "0"), "KM"), delta);
			assertEquals(2886.45, (Double) computeDistanceImplementation.invoke(reflection_parameter,new GeoPoint("36.12", "-86.67"), new GeoPoint("33.94", "-118.40"), "KM"), delta);
		}
		catch(Exception e) 
		{
			passIfExceptionIsDefinedInMethodSignatureFailOtherwise(computeDistanceExceptions, e);
		}

	}

	@Test
	public void sad_CanComputeDistanceWithMixedCaseKmParameterAndValidGeoPoints()
	{

		try
		{
			assertEquals(0.0, (Double) computeDistanceImplementation.invoke(reflection_parameter,new GeoPoint("0", "0"), new GeoPoint("0", "0"), "Km"), delta);
			assertEquals(6671.70, (Double) computeDistanceImplementation.invoke(reflection_parameter,new GeoPoint("0", "0"), new GeoPoint("0", "-60"), "kM"), delta);
			assertEquals(6671.70, (Double) computeDistanceImplementation.invoke(reflection_parameter,new GeoPoint("0", "0"), new GeoPoint("60", "0"), "kM"), delta);
			assertEquals(2886.45, (Double) computeDistanceImplementation.invoke(reflection_parameter,new GeoPoint("36.12", "-86.67"), new GeoPoint("33.94", "-118.40"), "Km"), delta);
		}
		catch(Exception e) 
		{
			passIfExceptionIsDefinedInMethodSignatureFailOtherwise(computeDistanceExceptions, e);
		}

	}

	@Test
	public void sad_CanComputeDistanceWithUpperCaseMiParameterAndValidGeoPoints()
	{

		try
		{
			assertEquals(0.0, (Double) computeDistanceImplementation.invoke(reflection_parameter,new GeoPoint("0", "0"), new GeoPoint("0", "0"), "MI"), delta);
			assertEquals(4145.60, (Double) computeDistanceImplementation.invoke(reflection_parameter,new GeoPoint("0", "0"), new GeoPoint("0", "-60"), "MI"), delta);
			assertEquals(4145.60, (Double) computeDistanceImplementation.invoke(reflection_parameter,new GeoPoint("0", "60"), new GeoPoint("0", "0"), "MI"), delta);
			assertEquals(1793.55, (Double) computeDistanceImplementation.invoke(reflection_parameter,new GeoPoint("36.12", "-86.67"), new GeoPoint("33.94", "-118.40"), "MI"), delta);

		}

		catch(Exception e) 
		{
			passIfExceptionIsDefinedInMethodSignatureFailOtherwise(computeDistanceExceptions, e);
		}

	}

	@Test
	public void sad_CanComputeDistanceWithMixedCaseMiParameterAndValidGeoPoints()
	{

		try
		{
			assertEquals(0.0, (Double) computeDistanceImplementation.invoke(reflection_parameter,new GeoPoint("0", "0"), new GeoPoint("0", "0"), "Mi"), delta);
			assertEquals(4145.60, (Double) computeDistanceImplementation.invoke(reflection_parameter,new GeoPoint("0", "0"), new GeoPoint("0", "-60"), "mI"), delta);
			assertEquals(4145.60, (Double) computeDistanceImplementation.invoke(reflection_parameter,new GeoPoint("0", "60"), new GeoPoint("0", "0"), "mI"), delta);
			assertEquals(1793.55, (Double) computeDistanceImplementation.invoke(reflection_parameter,new GeoPoint("36.12", "-86.67"), new GeoPoint("33.94", "-118.40"), "Mi"), delta);

		}

		catch(Exception e) 
		{
			passIfExceptionIsDefinedInMethodSignatureFailOtherwise(computeDistanceExceptions, e);
		}

	}


	// Acceptance tests for Task C

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
	public void happy_GetDestinationsReturnsCorrectDistanceToVenue()
	{
		gps.setCurrentPosition(new GeoPoint("56", "-80"));
		try{
			List<Destination> destinations = recommender.getDestinationsForArtists("Coldplay");
			Destination venue = findInDestinations(destinations, "Vector Arena");
			assertNotNull(venue);
			assertEquals(14224.98, venue.getDistance(), delta);
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
	public void happy_GetDestinationsReturnsCorrectStartDate()
	{
		SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd");

		try{
			List<Destination> destinations = recommender.getDestinationsForArtists("Iron Maiden");
			assertNotNull(destinations);
			assertTrue("I was expecting: 2009-03-20, but returned: " + findInDestinations(destinations, "Brasilia Camping").getStartDate().toString(), ft.parse("2009-03-20").equals(findInDestinations(destinations, "Brasilia Camping").getStartDate()));
			assertTrue("I was expecting: 2009-03-18, but returned: " + findInDestinations(destinations, "Mineirinho").getStartDate().toString(), ft.parse("2009-03-18").equals(findInDestinations(destinations, "Mineirinho").getStartDate()));

		} catch(Exception e){
			fail("I was not expecting an exception: " + e.getClass().toString());
		}
	}

	@Test
	public void happy_GetDestinationsReturnsCorrectCity()
	{
		try{
			List<Destination> destinations = recommender.getDestinationsForArtists("Iron Maiden");
			assertNotNull(findInDestinations(destinations, "Brasilia Camping"));
			assertNotNull(findInDestinations(destinations, "Mineirinho"));

			assertEquals("Brasilia", findInDestinations(destinations, "Brasilia Camping").getCity());
			assertEquals("Santiago", findInDestinations(destinations, "Club Hípico").getCity());
		} catch(Exception e){
			fail("I was not expecting an exception: " + e.getClass().toString());
		}
	}

	@Test
	public void happy_GetDestinationsReturnsCorrectVenues()
	{
		try{
			List<Destination> destinations = recommender.getDestinationsForArtists("Iron Maiden");
			assertNotNull(findInDestinations(destinations, "Mineirinho"));
			assertNotNull(findInDestinations(destinations, "Brasilia Camping"));
			assertNotNull(findInDestinations(destinations, "Club Hípico"));
			assertNotNull(findInDestinations(destinations, "Estadio Nacional"));
			assertNotNull(findInDestinations(destinations, "Verizon Wireless Amphitheater"));
		} catch(Exception e){
			fail("I was not expecting an exception: " + e.getClass().toString());
		}
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
	@Ignore //Not made clear in specifications //
	public void GetDestinationsReturnsConcertsInRightOrder() 
	{
		try{
			List<Destination> destinations = recommender.getDestinationsForArtists("Franz Ferdinand");
			assertTrue("Returned less than 5 destinations...", destinations.size() > 4);
			assertTrue("Dates are not in order...", destinations.get(0).getStartDate().compareTo(destinations.get(1).getStartDate()) < 0);
			assertTrue("Dates are not in order...", destinations.get(1).getStartDate().compareTo(destinations.get(2).getStartDate()) < 0);
			/*System.out.println(destinations.get(0).getStartDate().toString());
			System.out.println(destinations.get(1).getStartDate().toString());
			System.out.println(destinations.get(2).getStartDate().toString());
			System.out.println(destinations.get(3).getStartDate().toString());
			System.out.println(destinations.get(4).getStartDate().toString());*/
			assertTrue("Dates are not in order...", destinations.get(2).getStartDate().compareTo(destinations.get(3).getStartDate()) < 0);
			assertTrue("Dates are not in order...", destinations.get(3).getStartDate().compareTo(destinations.get(4).getStartDate()) < 0);
		} catch(Exception e){

			fail("I was not expecting an exception");
		}
	}






	// Acceptance tests for Task D

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

	@Test
	public void happy_CheckRecommendationsForArtistWithFewFansHavingFewTopArtistsAreOrdered()
	{
		player.setCurrentArtist("Coldplay");
		try{
			List<Recommendation> recommendations = recommender.getRecommendations();
			assertTrue("Recommendations are not ordered by fan count!", recommendations.get(0).getFanCount() >= recommendations.get(1).getFanCount());
			assertTrue("Recommendations are not ordered by fan count!", recommendations.get(1).getFanCount() >= recommendations.get(2).getFanCount());
		} catch(Exception e){
			fail("I was not expecting an exception: " + e.getClass().toString());
		} 
	}

	@Test
	public void happy_CheckGetRecommendationsReturnsAtMost20Entries()
	{
		player.setCurrentArtist("Metallica");
		try{
			List<Recommendation> recommendations = recommender.getRecommendations();
			assertTrue(recommendations.size() <= 20);
		} catch(Exception e){
			fail("I was not expecting an exception: " + e.getClass().toString());
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

	@Test
	public void happy_CheckRecommendationsForArtistWithLotsOfFansAreOrdered()
	{
		player.setCurrentArtist("U2");
		try{
			List<Recommendation> recommendations = recommender.getRecommendations();
			assertNotNull("I was expecting some recommendations, not null...", recommendations);
			assertTrue(recommendations.size() >= 20);
			assertTrue("Recommendations are not ordered by fan count!", recommendations.get(0).getFanCount() >= recommendations.get(1).getFanCount());
			assertTrue("Recommendations are not ordered by fan count!", recommendations.get(1).getFanCount() >= recommendations.get(2).getFanCount());
			assertTrue("Recommendations are not ordered by fan count!", recommendations.get(2).getFanCount() >= recommendations.get(3).getFanCount());
			assertTrue("Recommendations are not ordered by fan count!", recommendations.get(5).getFanCount() >= recommendations.get(7).getFanCount());
			assertTrue("Recommendations are not ordered by fan count!", recommendations.get(9).getFanCount() >= recommendations.get(11).getFanCount());
			assertTrue("Recommendations are not ordered by fan count!", recommendations.get(13).getFanCount() >= recommendations.get(15).getFanCount());
			assertTrue("Recommendations are not ordered by fan count!", recommendations.get(15).getFanCount() >= recommendations.get(19).getFanCount());

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

	// NO Acceptance tests for Task E

}
