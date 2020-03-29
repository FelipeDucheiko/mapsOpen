package graphicalInterface;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


public class Home extends JPanel {
	JLabel driversWaiting;
	JLabel driversIn;
	JLabel driversOut;
	ArrayList<String> listDriversWaiting = new ArrayList<String>();
	ArrayList<String> listDriversIn = new ArrayList<String>();
	ArrayList<String> listDriversOut = new ArrayList<String>();
	
	
	public Home() {
		setBackground(Color.WHITE);
		setLayout(new GridLayout(3, 1, 3, 0));
		setBorder(new EmptyBorder(30, 10, 10, 10));
	
		
		JPanel panelAbove = new JPanel();
		panelAbove.setLayout(new GridLayout(4,1));
		panelAbove.setBackground(Color.WHITE);
		
		JPanel panelCenter = new JPanel();
		panelCenter.setLayout(new GridLayout(4,1));
		panelCenter.setBackground(Color.WHITE);
		
		JPanel panelBelow = new JPanel();
		panelBelow.setLayout(new GridLayout(4,1));
		panelBelow.setBackground(Color.WHITE);
		
		add(panelAbove);
		add(panelCenter);
		add(panelBelow);
		

		JLabel driversWaitingLabel = new JLabel("    Drivers Waiting");
		panelAbove.add(driversWaitingLabel);
		driversWaitingLabel.setFont(new Font("Arial", Font.BOLD, 26));

		driversWaiting = new JLabel("          ");
		panelAbove.add(driversWaiting);
		driversWaiting.setFont(new Font("Times New Roman", Font.BOLD, 18));
		
		JLabel driversInLabel = new JLabel("    Drivers In");
		panelCenter.add(driversInLabel);
		driversInLabel.setFont(new Font("Arial", Font.BOLD, 26));

		driversIn = new JLabel("          ");
		panelCenter.add(driversIn);
		driversIn.setFont(new Font("Times New Roman", Font.BOLD, 18));
		

		JLabel driversOutLabel = new JLabel("    Drivers Out");
		panelBelow.add(driversOutLabel);
		driversOutLabel.setFont(new Font("Arial", Font.BOLD, 26));
		
		driversOut = new JLabel("          ");
		panelBelow.add(driversOut);
		driversOut.setFont(new Font("Times New Roman", Font.BOLD, 18));

	}
	
	public void addDriversWaiting(String drive) {
		listDriversWaiting.add(drive);
		refreshDriversWaiting();
	}
	
	public void addDriversIn(String drive) {
		listDriversWaiting.remove(drive);
		refreshDriversWaiting();
		
		listDriversIn.add(drive);
		refreshDriversIn();
	}
	
	public void addDriversOut(String drive) {
		listDriversIn.remove(drive);
		refreshDriversIn();
		
		listDriversOut.add(drive);
		refreshDriversOut();
	}
	
	public void refreshDriversWaiting() {
		String temp = "          ";
		for(String driver: listDriversWaiting){
		    temp+= driver + "; ";
		}
		driversWaiting.setText(temp);
	}
	
	public void refreshDriversIn() {
		String temp = "          ";
		for(String driver: listDriversIn){
		    temp+= driver + "; ";
		}
		driversIn.setText(temp);
	}
	
	public void refreshDriversOut() {
		String temp = "          ";
		for(String driver: listDriversOut){
		    temp+= driver + "; ";
		}
		driversOut.setText(temp);
	}



}