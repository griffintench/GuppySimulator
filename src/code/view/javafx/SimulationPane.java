package code.view.javafx;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import code.controller.SimulationHandler;
import javafx.application.Platform;
import javafx.scene.layout.VBox;

/**
 * The pane showing the simulation itself. This pane is comprised of a control
 * pane (with buttons) and an ecosystem pane (showing the pools).
 * 
 * @author griffin
 * @version 1.0
 */
public class SimulationPane extends VBox implements Observer {
    public static final int SCENE_WIDTH = 1000;
    public static final int SCENE_HEIGHT = 1000;

    private EcosystemPane ecosystemPane;
    private ControlPane controlPane;

    public SimulationPane() {
        final double multiplier = 0.7;
        final double rounder = 0.5;

        setPrefSize(SCENE_WIDTH, SCENE_HEIGHT);

        ecosystemPane = new EcosystemPane(SCENE_WIDTH,
                (int) (multiplier * SCENE_HEIGHT + rounder));

        controlPane = new ControlPane(SCENE_WIDTH,
                (int) ((1 - multiplier) * SCENE_HEIGHT + rounder));

        getChildren().addAll(ecosystemPane, controlPane);
    }

    /**
     * Adds the simulation handler to the simulation pane.
     * 
     * @param handler
     *            a SimulationHandler object
     */
    @SuppressWarnings("unchecked")
    public void addSimulationHandler(SimulationHandler handler) {
        controlPane.addSimulationHandler(handler);
        handler.addControlPane(controlPane);
    }

    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof ArrayList) {
            final ArrayList<int[]> castArg = (ArrayList<int[]>) arg;
            Platform.runLater(new Runnable() {
               public void run() {
                   ecosystemPane.update(castArg);
               }
            });
        } else if (arg instanceof Boolean) {
            final Boolean castArg = (Boolean) arg;
            Platform.runLater(new Runnable() {
               public void run() {
                   controlPane.simulationInProgress(castArg);
               }
            });
        }
    }

}
