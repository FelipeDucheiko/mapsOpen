package graphicalInterface;

import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import cartago.*;

public class GraphicalInterface extends Artifact {
	InterfaceJFrame view;
	private Home home = new Home();
	private ParkingSpots parkingSpots = new ParkingSpots();
	private About about = new About();
	
	void init() {
        view = new InterfaceJFrame();  
        view.setVisible(true);
	}
	
	@OPERATION
	public void startDriver(String driver) {
		home.addDriversWaiting(driver);
	}
	
	@OPERATION
	public void initializeParkingSpot(String parkingSpot) {
		parkingSpots.addParkingSpots(parkingSpot);
	}
	
	@OPERATION
	public void park(String driver, String parkingSpot) {
		parkingSpots.park(driver, parkingSpot);
		home.addDriversIn(driver);
	}
	
	@OPERATION
	public void freePark(String driver, String parkingSpot) {
		parkingSpots.freePark(driver, parkingSpot);
	}
	
	@OPERATION
	public void leaveParkedSpot(String driver) {		
		home.addDriversOut(driver);
	}
	
	class  InterfaceJFrame extends JFrame {
		
		public InterfaceJFrame() {
			BorderLayout layout = new BorderLayout();
			setLayout(new BorderLayout());
			
			setTitle("MAPS");
			setSize(600, 400);
			setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
			setVisible(true);
			setLocationRelativeTo(null);
			//setResizable(false);
			//setLayout(null);
			setBackground(Color.WHITE);
			
			JLabel logo = new JLabel(new ImageIcon("src//env//images//logo.png"));
			//add(logo, layout.NORTH);
            //logo.setBounds(350, 1, 250, 60);
			
			
			JPanel panelLogo = new JPanel();
			panelLogo.setLayout(new BorderLayout());
			panelLogo.add(logo, layout.LINE_END);
			
			add(panelLogo, layout.NORTH);
			

			JTabbedPane tabs = new JTabbedPane(JTabbedPane.TOP, JTabbedPane.SCROLL_TAB_LAYOUT);
			tabs.add(home, "   Home   ");
			tabs.add(parkingSpots, "  Parking Spots ");
			tabs.add(about, "  About   ");

			add(tabs, layout.CENTER);
		
			
		
		}
	}
}

