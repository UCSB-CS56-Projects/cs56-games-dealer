package edu.ucsb.cs56.projects.games.dealer;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

/**
 *
 *
 *
 */

public class BlackJackGui extends JPanel{
	BlackJackGame bjg;
	
	public BlackJackGui(){
		super();
		buildGUI();
	}
	
	public void buildGUI(){
		JPanel welcome = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		JLabel welcomePrompt = new JLabel("Welcome to BlackJack!");
		welcomePrompt.setFont(new Font("Sans Serif",Font.PLAIN,20));
		
		c.fill=GridBagConstraints.HORIZONTAL;
		c.anchor=GridBagConstraints.CENTER;
		c.insets=new Insets(250,0,0,0);
		c.gridx=1;
		c.gridy=1;
		welcome.add(welcomePrompt,c);
		
		class play implements ActionListener{
			@Override
			public void actionPerformed(ActionEvent e) {
				welcome.removeAll();
				repaint();
			}
			
		}
		JButton playButton = new JButton("Play");
		playButton.addActionListener(new play());
		c.insets=new Insets(80,0,0,0);
		c.gridx=1;
		c.gridy=2;
		welcome.add(playButton,c);
		add(welcome);
		

		
		
		
//		frame.getContentPane().add(background);
//		frame.pack();
//		frame.setSize(1136,640);
//		frame.setVisible(true);
	}
	
	








}
