package ca.bcit.comp1510.guppies;

import java.util.ArrayList;
import java.util.Random;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Camera;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.shape.Sphere;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class SimulatorFX extends Application {
    public static final int sceneWidth = 1000;
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

        Scene scene = new Scene(root, sceneWidth, sceneHeight);
        // Camera camera = new PerspectiveCamera(true);
        // camera.setTranslateZ(100);
        // scene.setCamera(camera);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * Runs the simulation after the button has been pressed. Currently the
     * number of weeks that are simulated at once is controlled within this
     * method.
     */
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
        Random generator = new Random();

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

            PhongMaterial poolMaterial = new PhongMaterial();
            poolMaterial.setSpecularColor(new Color(0.0, 0.75, 1.0, 1.0));
            poolMaterial.setDiffuseColor(new Color(0.0, 0.75, 1.0, 0.75));
            pool.setMaterial(poolMaterial);

            for (Fish guppy : ecosystem.getPools().get(i - 1)
                    .getGuppiesInPool().getFish()) {
                Sphere guppySphere = new Sphere(2);
                double guppyTranslationX = generator.nextInt(boxWidth)
                        + translation - boxWidth / 2;
                double guppyTranslationY = generator.nextInt(boxWidth)
                        - boxWidth / 2;
                double guppyTranslationZ = generator.nextInt(boxWidth)
                        - boxWidth / 2;
                guppySphere.setTranslateX(guppyTranslationX);
                guppySphere.setTranslateY(guppyTranslationY);
                guppySphere.setTranslateZ(guppyTranslationZ);

                PhongMaterial guppyMaterial = new PhongMaterial();
                if (guppy.getHealth().getHealthCoefficient() > 0.75) {
                    guppyMaterial
                            .setSpecularColor(new Color(0.0, 1.0, 0.0, 1.0));
                    guppyMaterial
                            .setDiffuseColor(new Color(0.0, 1.0, 0.0, 0.75));
                } else if (guppy.getHealth().getHealthCoefficient() > 0.25) {
                    guppyMaterial
                            .setSpecularColor(new Color(1.0, 0.5, 0.0, 1.0));
                    guppyMaterial
                            .setDiffuseColor(new Color(1.0, 0.5, 0.0, 0.75));
                } else {
                    guppyMaterial
                            .setSpecularColor(new Color(1.0, 0.0, 0.0, 1.0));
                    guppyMaterial
                            .setDiffuseColor(new Color(1.0, 0.0, 0.0, 0.75));
                }
                guppySphere.setMaterial(guppyMaterial);

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
