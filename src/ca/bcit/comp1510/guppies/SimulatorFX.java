package ca.bcit.comp1510.guppies;

import java.util.ArrayList;
import java.util.Random;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Box;
import javafx.scene.shape.Sphere;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class SimulatorFX extends Application {
    public static final int sceneWidth = 500;
    public static final int sceneHeight = 500;

    private StackPane root;
    private Simulation simulation;
    private int weeksElapsed;
    private Text populationText;

    @Override
    public void start(Stage primaryStage) throws Exception {
        weeksElapsed = 0;
        simulation = new Simulation();

        root = new StackPane();

        BorderPane bPane = new BorderPane();

        populationText = new Text("Population: "
                + simulation.getEcosystem().getGuppyPopulation());
        populationText.setTranslateY(50.0);
        drawObjects();

        primaryStage.setTitle("Simulation");
        Button btn = new Button();
        btn.setText("Simulate");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                runSimulation();
                drawObjects();
            }
        });

        root.getChildren().add(populationText);
        bPane.setBottom(btn);
        root.getChildren().add(bPane);
        primaryStage.setScene(new Scene(root, sceneWidth, sceneHeight));
        primaryStage.show();
    }

    private void runSimulation() {
        final int numberOfWeeks = 1;
        int weekNumber = weeksElapsed + 1;

        for (int i = weekNumber; i <= numberOfWeeks + weeksElapsed; i++) {
            Ecosystem ecosystem = simulation.getEcosystem();
            ecosystem.simulateOneWeek(i);
            populationText
                    .setText("Population: " + ecosystem.getGuppyPopulation());
            weekNumber++;
        }

        weeksElapsed = weekNumber - 1;
    }

    private void drawObjects() {
        Ecosystem ecosystem = simulation.getEcosystem();
        ArrayList<Box> pools = new ArrayList<Box>();
        ArrayList<Sphere> guppies = new ArrayList<Sphere>();
        int numberOfPools = ecosystem.getPools().size();
        int boxWidth = sceneWidth / (2 * numberOfPools + 1);

        for (int i = 1; i <= numberOfPools; i++) {
            Box pool = new Box(boxWidth, boxWidth, boxWidth);
            double translation = 0.0
                    + (-numberOfPools + (2 * i) - 1) * boxWidth;
            pool.setTranslateX(translation);

            Random generator = new Random();
            for (Guppy guppy : ecosystem.getPools().get(i - 1)
                    .getGuppiesInPool()) {
                Sphere guppySphere = new Sphere(1);
                double guppyTranslationX = generator.nextInt(boxWidth)
                        + translation - boxWidth / 2;
                double guppyTranslationY = generator.nextInt(boxWidth)
                        - boxWidth / 2;
                double guppyTranslationZ = generator.nextInt(boxWidth)
                        - boxWidth / 2;
                guppySphere.setTranslateX(guppyTranslationX);
                guppySphere.setTranslateY(guppyTranslationY);
                guppySphere.setTranslateZ(guppyTranslationZ);
                guppies.add(guppySphere);
            }

            pools.add(pool);
        }

        for (Box pool : pools) {
            root.getChildren().add(pool);
        }

        for (Sphere guppy : guppies) {
            root.getChildren().add(guppy);
        }
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
