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

/**
 * A Simulator GUI that uses JavaFX.
 * 
 * @author griffin
 * @version 1.0
 */
public class SimulatorFX extends Application {

    /**
     * The width of the window.
     */
    public static final int SCENE_WIDTH = 1000;

    /**
     * The height of the window.
     */
    public static final int SCENE_HEIGHT = 500;

    /**
     * The amount by which the population text is translated down.
     */
    public static final double POPULATION_TEXT_TRANSLATION_Y = 100.0;

    /**
     * The root node.
     */
    private StackPane root;

    /**
     * The Simulation we are running.
     */
    private Simulation simulation;

    /**
     * The number of weeks that have elapsed so far.
     */
    private int weeksElapsed;

    /**
     * The Text that displays the current population.
     */
    private Text populationText;

    @Override
    public void start(Stage primaryStage) throws Exception {
        weeksElapsed = 0;
        simulation = new Simulation();

        root = new StackPane();

        BorderPane bPane = new BorderPane();

        populationText = new Text(
                "Population: " + simulation.getEcosystemPopulation());
        populationText.setTranslateY(POPULATION_TEXT_TRANSLATION_Y);
        drawObjects();

        primaryStage.setTitle("Simulation");
        Button btn = new Button();
        btn.setText("Simulate");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                simulation.simulate(1);
                populationText
                .setText("Population: " + simulation.getEcosystemPopulation());
                drawObjects();
            }
        });

        root.getChildren().add(populationText);
        bPane.setBottom(btn);
        root.getChildren().add(bPane);

        Scene scene = new Scene(root, SCENE_WIDTH, SCENE_HEIGHT);
        // Camera camera = new PerspectiveCamera(true);
        // camera.setTranslateZ(100);
        // scene.setCamera(camera);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * Draws the Pools and the Guppies.
     */
    private void drawObjects() {
        final double poolGreen = 0.75;
        final double diffuseAlpha = 0.75;
        final double goodHealthCoefficient = 0.75;
        final double okayHealthCoefficient = 0.25;
        final double okayGuppyGreen = 0.5;

        Random generator = new Random();

        Ecosystem ecosystem = simulation.getEcosystem();
        ArrayList<Box> pools = new ArrayList<Box>();
        ArrayList<Sphere> guppies = new ArrayList<Sphere>();
        int numberOfPools = ecosystem.getPools().size();
        int boxWidth = SCENE_WIDTH / (2 * numberOfPools + 1);

        for (int i = 1; i <= numberOfPools; i++) {
            Box pool = new Box(boxWidth, boxWidth, boxWidth);
            double translation = 0.0
                    + (-numberOfPools + (2 * i) - 1) * boxWidth;
            pool.setTranslateX(translation);

            PhongMaterial poolMaterial = new PhongMaterial();
            poolMaterial.setSpecularColor(new Color(0.0, poolGreen, 1.0, 1.0));
            poolMaterial.setDiffuseColor(
                    new Color(0.0, poolGreen, 1.0, diffuseAlpha));
            pool.setMaterial(poolMaterial);

            for (Fish guppy : ecosystem.getPools().get(i - 1).getGuppiesInPool()
                    .getFish()) {
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
                if (guppy.getHealth()
                        .getHealthCoefficient() > goodHealthCoefficient) {
                    guppyMaterial
                            .setSpecularColor(new Color(0.0, 1.0, 0.0, 1.0));
                    guppyMaterial.setDiffuseColor(
                            new Color(0.0, 1.0, 0.0, diffuseAlpha));
                } else if (guppy.getHealth()
                        .getHealthCoefficient() > okayHealthCoefficient) {
                    guppyMaterial.setSpecularColor(
                            new Color(1.0, okayGuppyGreen, 0.0, 1.0));
                    guppyMaterial.setDiffuseColor(
                            new Color(1.0, okayGuppyGreen, 0.0, diffuseAlpha));
                } else {
                    guppyMaterial
                            .setSpecularColor(new Color(1.0, 0.0, 0.0, 1.0));
                    guppyMaterial.setDiffuseColor(
                            new Color(1.0, 0.0, 0.0, diffuseAlpha));
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
