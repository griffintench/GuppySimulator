package ca.bcit.comp1510.guppies;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * @author griffin
 *
 */
public class SimulatorPanel extends JPanel {
    private JLabel populationLabel;
    private Simulation simulation;
    private int weeksElapsed;

    public SimulatorPanel() {
        weeksElapsed = 0;
        
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(300, 300));

        simulation = new Simulation();
        
        populationLabel = new JLabel(
                "Population: " + simulation.getEcosystem().getGuppyPopulation());
        
        JButton startSimulation = new JButton("Start Simulation");
        startSimulation.addActionListener(new ButtonListener());

        add(startSimulation, BorderLayout.SOUTH);
        add(populationLabel, BorderLayout.CENTER);
    }

    private class ButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            final int numberOfWeeks = 1;
            int weekNumber = weeksElapsed;
            
            for (int i = weekNumber + 1; i <= numberOfWeeks + weeksElapsed; i++) {
                Ecosystem ecosystem = simulation.getEcosystem();
                ecosystem.simulateOneWeek(i);
                populationLabel.setText(
                        "Population: " + ecosystem.getGuppyPopulation());
                weekNumber++;
            }
            
            weeksElapsed = weekNumber;
        }
    }
}
