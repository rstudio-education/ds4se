package player;

import java.awt.*;
import javax.swing.*;


import javax.swing.JFrame;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;



public class PlayerUI {
	private PlayerAdapter playerAdapter;

	@SuppressWarnings("rawtypes")
	private JList playlist;
	private  JTextField distext;
	private JPanel mainpanel;
	private String currArtist;
	private String [] songs = {"Metallica-one","Cher-Believe","U2-Elevation"};
	//  private ListenerClass listener;
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Container createContentPane (){
		mainpanel = new JPanel();
		mainpanel.setLayout(null);
		mainpanel.setBounds(5, 5, 5, 5);

		JLabel playlabel = new JLabel("Now playing:");
		mainpanel.add(playlabel);
		playlabel.setBounds(20, 10, 100, 20);

		JLabel listlabel = new JLabel("Playlist");
		mainpanel.add(listlabel);
		listlabel.setBounds(20, 30, 100, 20);

		playlist = new JList(songs);
		playlist.setSelectedIndex(0);
		getArtistName();
		
		mainpanel.add(playlist);
		playerAdapter.setCurrentArtist(currArtist);

		distext = new JTextField(currArtist);

		playlist.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent lse) {
				if (!lse.getValueIsAdjusting()) {
					getArtistName();
					distext.setText(currArtist);
					playerAdapter.setCurrentArtist(currArtist);
				}

			}
		});

		distext.setEnabled(false);
		mainpanel.add(distext);
		distext.setBounds(130, 10, 100, 20);

		JScrollPane listjscroll = new JScrollPane(playlist);
		mainpanel.add(listjscroll);
		listjscroll.setBounds(20, 60, 200, 200);

		return mainpanel;
	}
	
	private void getArtistName() {
		currArtist = (String) playlist.getModel().getElementAt(playlist.getSelectedIndex());
		currArtist = currArtist.substring(0,currArtist.lastIndexOf("-"));
	}
	
	public PlayerUI(){
		this.playerAdapter = new PlayerAdapter();
	}
	
	public static void createAndShowGUI(){
		JFrame frame = new JFrame("MusicFone Player");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		PlayerUI player = new PlayerUI(); 
		frame.setContentPane(player.createContentPane());
		frame.setBounds(140, 130, 100, 20);
		frame.setSize(300,300 );   
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI();
			}
		});
	}

	@SuppressWarnings("rawtypes")
	public JList getPlaylist() {
		return playlist;
	}

	@SuppressWarnings("rawtypes")
	public void setPlaylist(JList playlist) {
		this.playlist = playlist;
	}

}
