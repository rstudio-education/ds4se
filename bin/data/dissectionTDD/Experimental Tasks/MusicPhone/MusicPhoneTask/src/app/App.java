package app;

import player.PlayerUI;
import recommender.RecommenderUI;
import gps.GpsAdapter;
import gps.GpsUI;

public class App {
	static GpsAdapter gps;

	
	
	public static void main(String[] args) {
		
    new PlayerUI();	
	PlayerUI.createAndShowGUI();
	
		
	new RecommenderUI();
	RecommenderUI.createAndShowGUI();
	
//	gps =new GpsAdapter();
//	gpsOberver = new GpsAdapterObserver();
//	gps.addObserver(gpsOberver);
	
	new GpsUI();
	GpsUI.createAndShowGUI();


	}

}
