package code.controller;

import code.model.Simulation;
import javafx.event.Event;
import javafx.event.EventHandler;

/**
 * 
 * @author griffin
 * @version 1.0
 */
public class SimulationHandler implements EventHandler {

    private Simulation simulation;
    
    @Override
    public void handle(Event event) {
        // for now just handles "1 week" button
        simulation.simulate(1);
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
