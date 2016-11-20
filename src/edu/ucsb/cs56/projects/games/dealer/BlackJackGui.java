package edu.ucsb.cs56.projects.games.dealer;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 *
 *
 *
 */

public class BlackJackGui {
	JPanel mainPanel;
	JFrame frame;
	BlackJackGame bjg;
	
	public static void main(String[] args){
		new BlackJackGui().buildGUI();
		
	}
	
	class BackgroundJFrame extends JFrame{
		public BackgroundJFrame(){
			setTitle("BlackJack");
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setLayout(new BorderLayout());
			setContentPane(new JLabel(new ImageIcon("images/tableBackground.jpg")));
			setLayout(new FlowLayout());
		}
	}
	
	
	public void buildGUI(){
		frame = new BackgroundJFrame();
		BorderLayout layout = new BorderLayout();
		JPanel background = new JPanel(layout);
		background.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));;
		JLabel welcome = new JLabel("Welcome to BlackJack!");
		welcome.setFont(new Font("Sans Serif",Font.PLAIN,20));
		welcome.setBackground(new Color(0,0,0,0));
		welcome.setOpaque(true);
		
		background.add(welcome,BorderLayout.CENTER);
//		welcome.setHorizontalAlignment(SwingConstants.CENTER);
		
		
		frame.getContentPane().add(background);
		
		frame.pack();
		frame.setSize(1136,640);
		frame.setVisible(true);
	}








}
