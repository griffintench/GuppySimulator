package code.view.javafx;

import java.util.ArrayList;
import java.util.HashMap;
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
    
    private EcosystemPane ecosystemPane;
    private ControlPane controlPane;

    /**
     * Creates the pane at a size determined by parameters; instantiates and
     * adds an EcosystemPane and a ControlPane.
     * 
     * @param sceneWidth
     *            the width of the scene
     * @param sceneHeight
     *            the height of the scene
     */
    public SimulationPane(int sceneWidth, int sceneHeight) {
        final double multiplier = 0.7;
        final double rounder = 0.5;

        setPrefSize(sceneWidth, sceneHeight);

        ecosystemPane = new EcosystemPane(sceneWidth,
                (int) (multiplier * sceneHeight + rounder));

        controlPane = new ControlPane(sceneWidth,
                (int) ((1 - multiplier) * sceneHeight + rounder));

        getChildren().addAll(ecosystemPane, controlPane);
    }

    /**
     * Adds the simulation handler to the simulation pane.
     * 
     * @param handler
     *            a SimulationHandler object
     */
    public void addSimulationHandler(SimulationHandler handler) {
        controlPane.addSimulationHandler(handler);
        handler.addControlPane(controlPane);
    }

    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof ArrayList) {
//            final ArrayList<int[]> castArg = (ArrayList<int[]>) arg;
//            Platform.runLater(new Runnable() {
//                public void run() {
//                    ecosystemPane.update(castArg);
//                }
//            });
        } else if (arg instanceof Boolean) {
            final Boolean castArg = (Boolean) arg;
            Platform.runLater(new Runnable() {
                public void run() {
                    controlPane.simulationInProgress(castArg);
                }
            });
        } else if (arg instanceof HashMap) {
            final HashMap castArg = (HashMap) arg;
            Platform.runLater(new Runnable() {
               public void run() {
                   ecosystemPane.update(castArg);
               }
            });
        }
    }

}
