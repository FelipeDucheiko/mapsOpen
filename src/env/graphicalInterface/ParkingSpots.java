package graphicalInterface;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class ParkingSpots extends JPanel{
	Map<String, JLabel> parkingSpots = new HashMap<String,JLabel>();
	
	public ParkingSpots() {
		setLayout(new GridLayout(4,4));
		setBackground(Color.WHITE);
				
	}
	
	public void addParkingSpots(String parkingSpot) {
		JLabel temp = new JLabel("    " + parkingSpot + ": Vazio");
		temp.setFont(new Font("Arial", Font.BOLD, 14));
		add(temp);
		parkingSpots.put(parkingSpot, temp);
	}
	
	public void park(String drive, String parkingSpot) {
		JLabel temp = parkingSpots.get(parkingSpot);
		temp.setText("    " + parkingSpot + ": " + drive);
	}
	
	public void freePark(String drive, String parkingSpot) {
		JLabel temp = parkingSpots.get(parkingSpot);
		temp.setText("    " + parkingSpot + ": Vazio");
	}

}
