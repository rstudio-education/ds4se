package player;

import java.util.ArrayList;

import commons.interfaces.IPlayer;
import commons.DeviceManager;
import player.Track;


public class Player implements IPlayer
{
	private ArrayList<Track> playList;
	private Track currentTrack = new Track();
	private String artist;
	private String title;
	private int currentIndex = 0;

	public Player()
	{
		DeviceManager.getInstance().setPlayer(this);
	}

	public ArrayList<Track> getPlayList()
	{
		return playList;
	}
	public  void setPlayList(ArrayList<Track> value)
	{
		playList = value;
		currentIndex = 0;
	}

	public  Track getCurrentTrack()
	{
		return currentTrack;
	}
	public  void setCurrentTrack(Track value)
	{
		currentTrack = value.clone();
		Play(this.currentTrack.getArtist(),this.currentTrack.getTitle());
		currentIndex = playList.indexOf(value); 
		
	}

	public  void Next()
	{
		currentIndex = (currentIndex + 1) % getPlayList().toArray().length;
		currentTrack = this.getPlayList().get(currentIndex);
		Play(this.currentTrack.getArtist(),this.currentTrack.getTitle());
	}

	public  void Play(String artist, String title)
	{
		this.setCurrentArtist(artist);
		this.setCurrentTitle(title);
	}

	public  String getCurrentTitle()
	{
		return title;
	}
	public  void setCurrentTitle(String value)
	{
		title = value;
	}

	public  String getCurrentArtist()
	{
		return artist;
	}
	public  void setCurrentArtist(String value)
	{
		artist = value;
	}

}




