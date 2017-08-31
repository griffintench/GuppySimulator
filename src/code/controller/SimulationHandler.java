package code.controller;

import code.model.Simulation;
import code.view.javafx.ControlPane;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

/**
 * Handles all events from the SimulationPane.
 * 
 * @author griffin
 * @version 1.0
 */
public class SimulationHandler implements EventHandler<ActionEvent> {

    private Simulation simulation;
    private ControlPane controlPane;

    @Override
    public void handle(ActionEvent event) {
        Object source = event.getSource();
        if (source instanceof Button) {
            Button button = (Button) source;
            String text = button.getText();

            // I feel like there's a better way to do this, since this would
            // mean that any change to the button labels would necessitate a
            // change to the controller
            if (text.equals("1 week")) {
                simulation.simulate(1);
            } else if (text.equals("Go")) {
                String numberText = controlPane.getNumberOfWeeks().trim();
                try {
                    int numberOfWeeks = Integer.parseInt(numberText);
                    simulation.simulate(numberOfWeeks);
                } catch (NumberFormatException e) {
                    System.out.println("No number typed in the field");
                }
            }
        }
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

    /**
     * Sets the ControlPane so this handler can see what is in the text field.
     * 
     * @param newControlPane
     *            the ControlPane that communicates with this handler
     */
    public void addControlPane(ControlPane newControlPane) {
        controlPane = newControlPane;
    }

}
