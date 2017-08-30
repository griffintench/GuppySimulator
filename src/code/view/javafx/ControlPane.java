package code.view.javafx;

import code.controller.SimulationHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * The pane with all the buttons to control the simulation.
 * 
 * @author griffin
 * @version 1.0
 */
public class ControlPane extends VBox {

    private int sceneWidth;
    private int sceneHeight;

    private TextField numberOfWeeks;
    private Button go;
    private Button oneWeek;
    private Button pause;
    private Button resume;
    private Button step;
    private Button slower;
    private Button faster;
    private Button populationGraph;
    private Button generateReport;

    /**
     * Sets the pane to the right size and creates the buttons. Right now, most
     * buttons are disabled until I implement their functionality.
     * 
     * @param sceneWidth
     *            the width of the pane
     * @param sceneHeight
     *            the height of the pane
     */
    public ControlPane(int sceneWidth, int sceneHeight) {
        this.sceneWidth = sceneWidth;
        setPrefWidth(sceneWidth);

        this.sceneHeight = sceneHeight;
        setPrefHeight(sceneHeight);

        Label simulate = new Label("Simulate");
        numberOfWeeks = new TextField();
        Label weeks = new Label("weeks");
        go = new Button("Go");
        oneWeek = new Button("1 week");
        pause = new Button("Pause");
        resume = new Button("Resume");
        step = new Button("Step");
        slower = new Button("Slower");
        faster = new Button("Faster");
        populationGraph = new Button("Population Graph");
        generateReport = new Button("Generate Report");

        disableButtons();

        HBox row1 = new HBox();
        HBox row2 = new HBox();
        HBox row3 = new HBox();

        row1.getChildren().addAll(simulate, numberOfWeeks, weeks, go, oneWeek);
        row2.getChildren().addAll(pause, resume, step, slower, faster);
        row3.getChildren().addAll(populationGraph, generateReport);

        row1.setAlignment(Pos.CENTER);
        row2.setAlignment(Pos.CENTER);
        row3.setAlignment(Pos.CENTER);

        getChildren().addAll(row1, row2, row3);
    }

    /**
     * Disables buttons until I've actually implemented their functionality.
     */
    private void disableButtons() {
        pause.setDisable(true);
        resume.setDisable(true);
        step.setDisable(true);
        slower.setDisable(true);
        faster.setDisable(true);
        populationGraph.setDisable(true);
        generateReport.setDisable(true);
    }

    /**
     * Disables or enables the numberOfWeeks, go, and oneWeek controls depending
     * on whether or not the simulation is currently running.
     * 
     * @param inProgress
     *            true if the simulation is currently running
     */
    public void simulationInProgress(boolean inProgress) {
        numberOfWeeks.setDisable(inProgress);
        go.setDisable(inProgress);
        oneWeek.setDisable(inProgress);
    }

    /**
     * Adds the simulation handler to the simulation pane.
     * 
     * @param handler
     *            a SimulationHandler object
     */
    @SuppressWarnings("unchecked")
    public void addSimulationHandler(SimulationHandler handler) {
        go.setOnAction(handler);
        oneWeek.setOnAction(handler);
    }

    /**
     * Returns whatever is currently typed in the number of weeks field.
     * 
     * @return whatever is currently typed in the number of weeks field
     */
    public String getNumberOfWeeks() {
        return numberOfWeeks.getText();
    }

}
