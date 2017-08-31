package code.view.javafx;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

/**
 * Defines a Pane which provides options for the users to load the default
 * simulation, load a simulation from a file or load a custom simulation.
 * 
 * @author griffin
 * @version 1.0
 */
public class LoadSimulationPane extends VBox {
    private Button loadDefaultSimulation;

    private Button loadSimulationFromFile;

    private Button loadCustomSimulation;

    /**
     * Instantiates the three Buttons and adds them to the Pane.
     */
    public LoadSimulationPane() {
        loadDefaultSimulation = new Button("Load Default Simulation");
        loadSimulationFromFile = new Button("Load Simulation From File");
        loadCustomSimulation = new Button("Load Custom Simulation");

        loadSimulationFromFile.setDisable(true);
        loadCustomSimulation.setDisable(true);

        getChildren().addAll(loadDefaultSimulation, loadSimulationFromFile,
                loadCustomSimulation);
    }

    /**
     * Tells the Load Default Simulation button to use a LoadDefaultHandler when
     * clicked.
     * 
     * @param handler
     *            a LoadDefaultHandler object.
     */
    public void addLoadDefaultHandler(EventHandler<ActionEvent> handler) {
        loadDefaultSimulation.setOnAction(handler);
    }

}
