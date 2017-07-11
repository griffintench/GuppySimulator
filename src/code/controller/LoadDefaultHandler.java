package code.controller;

import code.model.Simulation;
import javafx.event.Event;
import javafx.event.EventHandler;

/**
 * @author griffin
 *
 */
public class LoadDefaultHandler implements EventHandler {

    private Simulation simulation;

    @Override
    public void handle(Event event) {
        simulation.setUpEcosystem(); // I'll probably rename this method later
    }

    /**
     * Sets the simulation that this handler communicates with.
     * 
     * @param simulation
     *            the simulation that this handler communicates with
     */
    public void setSimulation(Simulation simulation) {
        this.simulation = simulation;
    }

}
