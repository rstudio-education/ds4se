package gps;

import javax.swing.*;

import commons.dataClasses.GeoPoint;

public class GpsUI {
	private static GpsAdapter gps; 
	private GeoPoint nyc;

    public static void createAndShowGUI() {
    	
    	JPanel panel = new JPanel();
    	panel.setLayout(null);
    	 
    	JLabel latlabel = new JLabel("Latitude:");
    	panel.add(latlabel);
    	latlabel.setBounds(20, 10, 100, 20);
    	
    	JTextField Lattextfield = new JTextField();
       	Lattextfield.setText(gps.getCurrentPosition().getLatitude());
       	Lattextfield.setEnabled(false);
    	panel.add(Lattextfield);
    	Lattextfield.setBounds(105, 15, 100, 20);
    	
    	JLabel longlabel = new JLabel("Longitude:");
    	panel.add(longlabel);
    	longlabel.setBounds(20, 35, 100, 40);
    	
    	JTextField Lontextfield = new JTextField();
    	Lontextfield.setText(gps.getCurrentPosition().getLongitude());
    	Lontextfield.setEnabled(false);
    	panel.add(Lontextfield);

    	
    	Lontextfield.setBounds(105, 50, 100, 20);
    	
    	JLabel dislabel = new JLabel("Distance Units:");
    	panel.add(dislabel);
    	dislabel.setBounds(20, 70, 100, 40);
    	
    	ButtonGroup btngroup = new ButtonGroup();
    	
    	JRadioButton kmbtn = new JRadioButton("km");
    	kmbtn.setBounds(105, 80, 100, 20);
    	kmbtn.setEnabled(true);
    	kmbtn.setSelected(true);
    	btngroup.add(kmbtn);
    	
    	
    	JRadioButton mibtn = new JRadioButton("mi");
    	mibtn.setBounds(160, 80, 120, 20);
    	mibtn.setEnabled(true);
    	btngroup.add(mibtn);
        panel.add(mibtn);
        panel.add(kmbtn);
        
        JFrame frame = new JFrame("MusicFone Gps");
        frame.add(panel);
		frame.setBounds(180, 430, 100, 20);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(260,150 );
        frame.setVisible(true);
    }
    
    
    public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI();
			}
		});
	}
	public GpsUI() {
		gps = new GpsAdapter();
		nyc  = new GeoPoint("42.3482", "75.1890");
		gps.setCurrentPosition(nyc);
		gps.setDistanceUnits("km");

		

	}


}