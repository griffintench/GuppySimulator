package ca.bcit.comp1510.guppies;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * 
 * 
 * @author griffin
 * @version 1.0
 */
public class SimulatorSwing {

    /**
     * Drives the program.
     * 
     * @param args
     *            command line arguments
     */
    public static void main(String[] args) {
        JFrame frame = new JFrame("Simulator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        SimulatorPanel simulatorPanel = new SimulatorPanel();

        frame.getContentPane().add(simulatorPanel);
        frame.pack();
        frame.setVisible(true);
    }

}
