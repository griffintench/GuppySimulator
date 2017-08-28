package code.controller;

import code.model.Simulation;
import code.run.SimulatorFXv2;
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
        SimulatorFXv2.loadSimulationPane(); // don't like this for portability reasons
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