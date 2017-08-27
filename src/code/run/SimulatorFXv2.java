/**
 * 
 */
package code.run;

import java.util.Observable;
import java.util.Observer;

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
 * @author griffin
 *
 */
public class SimulatorFXv2 extends Application implements Observer {

    /**
     * The width of the window.
     */
    public static final int SCENE_WIDTH = 1000;

    /**
     * The height of the window.
     */
    public static final int SCENE_HEIGHT = 500;

    private static StackPane root;
    private static Stage primaryStage;

    private static Simulation simulation;

    @Override
    public void start(Stage primaryStage) throws Exception {
        SimulatorFXv2.primaryStage = primaryStage;
        root = new StackPane();
        simulation = new Simulation(this);
        loadLoadSimulationPane();
    }

    public static void loadLoadSimulationPane() {
        LoadSimulationPane pane = new LoadSimulationPane();
        LoadDefaultHandler loadDefaultHandler = new LoadDefaultHandler();
        loadDefaultHandler.setSimulation(simulation);

        pane.addLoadDefaultHandler(loadDefaultHandler);

        root.getChildren().add(pane);
        Scene scene = new Scene(root, SCENE_WIDTH, SCENE_HEIGHT);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void loadSimulationPane() {
        SimulationPane pane = new SimulationPane();
        SimulationHandler handler = new SimulationHandler();
        handler.setSimulation(simulation);
        pane.addSimulationHandler(handler);
        simulation.addObserver(pane);

        Scene scene = new Scene(pane, SCENE_WIDTH, SCENE_HEIGHT);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @Override
    public void update(Observable o, Object arg) {
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
    }

}
