package gps;

import commons.dataClasses.GeoPoint;
import commons.interfaces.IGps;

public class GpsAdapter implements IGps   {
	private  Gps gps;
	public GpsAdapter(){
		super();
		gps=new Gps();
	}
	@Override
	public GeoPoint getCurrentPosition() {
		return gps.getCurrentPosition();
	}

	@Override
	public String getDistanceUnits() {
		return gps.getDistanceUnits();
	}

	@Override
	public void setCurrentPosition(GeoPoint value) {
		gps.setCurrentPosition(value);
		//notifyObservers(this);
		//setChanged();
	}

	@Override
	public void setDistanceUnits(String value) {
		gps.setDistanceUnits(value);
	//	notifyObservers(this);
		//setChanged();
	}
	
	
}
