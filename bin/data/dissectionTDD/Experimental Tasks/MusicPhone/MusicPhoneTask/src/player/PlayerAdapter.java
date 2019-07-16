package player;

import java.util.ArrayList;
import commons.interfaces.IPlayer;


public class PlayerAdapter  implements IPlayer
{
		private Player player;

		public PlayerAdapter()
		{
			super();
			player = new Player();
		}

		public final ArrayList<Track> getPlayList()
		{
			return player.getPlayList();
		}
		public final void setPlayList(ArrayList<Track> value)
		{
			player.setPlayList(value);

			
		}

		public final Track getCurrentTrack()
		{
			return player.getCurrentTrack();
		}
		
		public final void setCurrentTrack(Track value)
		{
			player.setCurrentTrack(value);
			//notifyObservers(this);
			//setChanged();
			
		}

		public final void Next()
		{
			player.Next();
		//	notifyObservers(this);
			//setChanged();
			
		}

		@Override
		public final String getCurrentTitle()
		{
			return player.getCurrentTitle();
		}
		
		@Override
		public final void setCurrentTitle(String value)
		{
			player.setCurrentTitle(value);
		//	notifyObservers(this);
		//	setChanged();
			
		}

		@Override
		public final String getCurrentArtist()
		{
			return player.getCurrentArtist();
		}
		
		@Override
		public final void setCurrentArtist(String value)
		{
			player.setCurrentArtist(value);
			//notifyObservers(this);
			//setChanged();
		}

//		public event PropertyChangedEventHandler PropertyChanged;

		/*private void OnPropertyChanged(String p)
		{
			PropertyChangedEventHandler handler = PropertyChanged;
			if (handler != null)
			{
				handler(this, new PropertyChangedEventArgs(p));
			}
		}*/

}
