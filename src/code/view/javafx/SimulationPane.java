package code.view.javafx;

import java.util.Observable;
import java.util.Observer;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 * @author griffin
 *
 */
public class SimulationPane extends VBox implements Observer {
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
    
    public SimulationPane() {
        Pane topPane = new Pane();
        VBox bottomPane = new VBox();
        
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
        
        bottomPane.getChildren().addAll(row1, row2, row3);
        getChildren().addAll(topPane, bottomPane);
    }
    
    /**
     * Disables buttons until I've actually implemented their functionality.
     */
    private void disableButtons() {
        numberOfWeeks.setDisable(true);
        go.setDisable(true);
        pause.setDisable(true);
        resume.setDisable(true);
        step.setDisable(true);
        slower.setDisable(true);
        faster.setDisable(true);
        populationGraph.setDisable(true);
        generateReport.setDisable(true);
    }

    @Override
    public void update(Observable o, Object arg) {
        // TODO Auto-generated method stub
        
    }
    
    
}
