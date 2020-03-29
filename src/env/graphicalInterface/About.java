package graphicalInterface;

import java.awt.event.MouseListener;
import java.io.IOException;
import java.net.URISyntaxException;

import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;


import javax.swing.JLabel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;

public class About extends JPanel implements MouseListener {
	private JLabel link = new JLabel("github.com/MAPS-UTFPR/MAPS");

	public About() {
		//setLayout(null);
		GridLayout layout = new GridLayout(3, 1);
		setLayout(layout);
		setBackground(Color.WHITE);

		JLabel MAPS = new JLabel("MultiAgent Parking System");
		MAPS.setHorizontalAlignment(SwingConstants.CENTER);
		add(MAPS);
		//MAPS.setBounds(130, 50, 400, 30);
		MAPS.setFont(new Font("Arial", Font.BOLD, 28));

		JTextArea description = new JTextArea("The MAPS (MultiAgent Parking System) initiave is a research project developed by students and professors of Computer Science at UTFPR - (Federal University of Technology - Parana). Its main goal is to develop solutions for intelligent parking systems. For more information visit:");
		description.setAlignmentX(RIGHT_ALIGNMENT);
		add(description);
		description.setLineWrap(true);
		description.setWrapStyleWord(true);
		description.setEditable(false);
		description.setFont(new Font("Arial", Font.PLAIN, 18));
		//description.setBounds(40, 100, 520, 110);
	
		link.addMouseListener(this);
		link.setHorizontalAlignment(SwingConstants.CENTER);
		add(link);
		//link.setBounds(150, 220, 320, 30);
		link.setFont(new Font("Arial", Font.BOLD, 20));
		link.setVisible(true);
		
	}

	
	public void mouseClicked(MouseEvent e) {
		try {
			java.awt.Desktop.getDesktop().browse(new java.net.URI("https://github.com/MAPS-UTFPR/MAPS"));
		} catch (IOException | URISyntaxException e1) {
			e1.printStackTrace();
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		link.setForeground(Color.blue);
	}

	@Override
	public void mouseExited(MouseEvent e) {
		link.setForeground(Color.black);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
