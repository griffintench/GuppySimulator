package code.controller;

import code.model.Simulation;
import code.run.SimulatorFXv2;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

/**
 * A handler for the Load Default Simulation button.
 * 
 * @author griffin
 * @version 1.0
 */
public class LoadDefaultHandler implements EventHandler<ActionEvent> {

    private Simulation simulation;

    @Override
    public void handle(ActionEvent event) {
        simulation.setUpEcosystem(); // I'll probably rename this method later
        SimulatorFXv2.loadSimulationPane(); // don't like this for portability
                                            // reasons
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
