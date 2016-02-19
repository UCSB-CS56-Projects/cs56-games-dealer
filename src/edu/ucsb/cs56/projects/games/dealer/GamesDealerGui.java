package edu.ucsb.cs56.projects.games.dealer;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.lang.*;

/** SimpleGui for our Games Dealer application

     @author Kelly Bielaski and Tristan Starck
     @version CS56, Winter 2016, UCSB
*/


public class GamesDealerGui{
    
    /**
       Constructor
    */

    private GamesDealerGui(){}

    /**
       The places the BACPanel into the frame and makes
       the specifc frame visible
       @param standard command line parameters for main
     */
    public static void main (String[] args) {
	
	JFrame frame = new JFrame("Games Dealer");
	GamesDealerPanel panel = new GamesDealerPanel();
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.getContentPane().add(BorderLayout.CENTER, panel);
	frame.pack();
	frame.setSize(800,600);
	frame.setVisible(true);
    }
}
