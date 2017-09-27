package code.run;

import code.controller.LoadDefaultHandler;
import code.controller.SimulationHandler;
import code.model.Simulation;
import code.view.javafx.LoadSimulationPane;
import code.view.javafx.SimulationPane;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * A newer simulator using JavaFX; code is organized much better in this one.
 * 
 * @author griffin
 * @version 1.0
 */
public class SimulatorFXv2 extends Application {

    /**
     * The width of the window.
     */
    public static final int SCENE_WIDTH = 1000;

    /**
     * The height of the window.
     */
    public static final int SCENE_HEIGHT = 500;

    private static StackPane root;
    private static Stage stage;

    private static Simulation simulation;

    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        root = new StackPane();
        simulation = new Simulation();
        loadLoadSimulationPane();
    }

    /**
     * Loads the LoadSimulationPane; adds necessary handlers.
     */
    public static void loadLoadSimulationPane() {
        LoadSimulationPane pane = new LoadSimulationPane();
        LoadDefaultHandler loadDefaultHandler = new LoadDefaultHandler();
        loadDefaultHandler.setSimulation(simulation);

        pane.addLoadDefaultHandler(loadDefaultHandler);

        root.getChildren().add(pane);
        Scene scene = new Scene(root, SCENE_WIDTH, SCENE_HEIGHT);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Loads the SimulationPane; adds necessary handlers and observers.
     */
    public static void loadSimulationPane() {
        SimulationPane pane = new SimulationPane(SCENE_WIDTH, SCENE_HEIGHT);
        SimulationHandler handler = new SimulationHandler();
        handler.setSimulation(simulation);
        pane.addSimulationHandler(handler);
        simulation.addObserver(pane);
        simulation.constructPoolHashMap();
        Scene scene = new Scene(pane, SCENE_WIDTH, SCENE_HEIGHT);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Drives the program.
     * 
     * @param args
     *            command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
