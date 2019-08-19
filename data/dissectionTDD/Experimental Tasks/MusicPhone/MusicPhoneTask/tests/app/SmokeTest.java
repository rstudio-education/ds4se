package app;

import static org.junit.Assert.*;
import gps.Gps;
import org.junit.Test;
import player.Player;
import commons.Recommender;
import commons.dataClasses.*;
import commons.interfaces.*;

import dataConnectors.LastFmXmlConnector;

public class SmokeTest {
	 @Test
	 public void HowToUsePlayerGpsAndRecommenderInUnitTests()
	 {
	     // Creating and initializing Player and Gps objects
	     IPlayer player = new Player(); // Player auto-registers itself with DeviceManager
	     IGps gps = new Gps();  // Gps auto-registers itself with DeviceManager
	     GeoPoint position = new GeoPoint("-14.2", "60.4"); 
	     gps.setCurrentPosition(position); 
	     gps.setDistanceUnits("KM");
	     player.setCurrentArtist("Cher");
	     player.setCurrentTitle("Moonstruck"); 
	     // Creating and initializing Recommender to access XML data for testing
	     Recommender recommender = new Recommender(new LastFmXmlConnector());

	     // Recommender knows how to access the new Player and Gps objects
	     assertNotNull(recommender.getGps());
	     assertNotNull(recommender.getPlayer());
	     assertEquals(position.getLatitude(), recommender.getGps().getCurrentPosition().getLatitude());
	     assertEquals(position.getLongitude(), recommender.getGps().getCurrentPosition().getLongitude());
	     assertEquals("Cher", recommender.getPlayer().getCurrentArtist());
	     assertEquals("KM", recommender.getGps().getDistanceUnits());
	 }
}

