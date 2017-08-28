package code.run;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;

import code.model.Ecosystem;
import code.model.Fish;
import code.model.Simulation;
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
 * OLD; NO REAL POINT IN USING.
 * 
 * @author griffin
 * @version 1.0
 */
public class SimulatorFX extends Application implements Observer {

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
     * The Text that displays the current population.
     */
    private Text populationText;

    /**
     * The Boxes that represent pools.
     */
    private ArrayList<Box> pools;

    /**
     * The Spheres that represent guppies.
     */
    private ArrayList<Sphere> guppies;

    /**
     * A Random object for various purposes.
     */
    private Random generator;

    @Override
    public void start(Stage primaryStage) throws Exception {
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
                populationText.setText(
                        "Population: " + simulation.getEcosystemPopulation());
                drawObjects();
            }
        });

        root.getChildren().add(populationText);
        bPane.setBottom(btn);
        root.getChildren().add(bPane);

        Scene scene = new Scene(root, SCENE_WIDTH, SCENE_HEIGHT);
        // TODO when I figure out how Camera works
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
        generator = new Random();

        Ecosystem ecosystem = simulation.getEcosystem();
        pools = new ArrayList<Box>();
        guppies = new ArrayList<Sphere>();
        int numberOfPools = ecosystem.getPools().size();
        int boxWidth = SCENE_WIDTH / (2 * numberOfPools + 1);

        for (int i = 1; i <= numberOfPools; i++) {
            drawPool(i, boxWidth, numberOfPools);
        }

        for (Box pool : pools) {
            root.getChildren().add(pool);
        }

        for (Sphere guppy : guppies) {
            root.getChildren().add(guppy);
        }
    }

    /**
     * Creates a Box object representing a pool and adds it to pools.
     * 
     * @param index
     *            the index of a pool (starting from 1)
     * @param boxWidth
     *            the width of the box representing a pool
     * @param numberOfPools
     *            the total number of pools in the simulation
     */
    private void drawPool(int index, int boxWidth, int numberOfPools) {
        final Color poolSpecular = new Color(0.0, 0.75, 1.0, 1.0);
        final Color poolDiffuse = new Color(0.0, 0.75, 1.0, 0.75);

        Box pool = new Box(boxWidth, boxWidth, boxWidth);
        double translation = 0.0
                + (-numberOfPools + (2 * index) - 1) * boxWidth;
        pool.setTranslateX(translation);

        PhongMaterial poolMaterial = new PhongMaterial();
        poolMaterial.setSpecularColor(poolSpecular);
        poolMaterial.setDiffuseColor(poolDiffuse);
        pool.setMaterial(poolMaterial);

        for (Fish guppy : simulation.getGuppies(index - 1)) {
            Sphere guppySphere = new Sphere(2);
            double guppyTranslationX = generator.nextInt(boxWidth) + translation
                    - boxWidth / 2;
            double guppyTranslationY = generator.nextInt(boxWidth)
                    - boxWidth / 2;
            double guppyTranslationZ = generator.nextInt(boxWidth)
                    - boxWidth / 2;
            guppySphere.setTranslateX(guppyTranslationX);
            guppySphere.setTranslateY(guppyTranslationY);
            guppySphere.setTranslateZ(guppyTranslationZ);

            guppySphere.setMaterial(getGuppyMaterial(guppy));

            guppies.add(guppySphere);
        }

        pools.add(pool);
    }

    /**
     * Creates and assigns colors to a PhongMaterial object corresponding to a
     * particular guppy.
     * 
     * @param guppy
     *            a Fish object (in this case a Guppy)
     * @return the PhongMaterial, properly colored
     */
    private PhongMaterial getGuppyMaterial(Fish guppy) {
        final Color healthyGuppySpecular = new Color(0.0, 1.0, 0.0, 1.0);
        final Color healthyGuppyDiffuse = new Color(0.0, 1.0, 0.0, 0.75);
        final Color okayGuppySpecular = new Color(1.0, 0.5, 0.0, 1.0);
        final Color okayGuppyDiffuse = new Color(1.0, 0.5, 0.0, 0.75);
        final Color unhealthyGuppySpecular = new Color(1.0, 0.0, 0.0, 1.0);
        final Color unhealthyGuppyDiffuse = new Color(1.0, 0.0, 0.0, 0.75);

        PhongMaterial guppyMaterial = new PhongMaterial();
        if (guppy.isHealthy()) {
            guppyMaterial.setSpecularColor(healthyGuppySpecular);
            guppyMaterial.setDiffuseColor(healthyGuppyDiffuse);
        } else if (guppy.isOkay()) {
            guppyMaterial.setSpecularColor(okayGuppySpecular);
            guppyMaterial.setDiffuseColor(okayGuppyDiffuse);
        } else {
            guppyMaterial.setSpecularColor(unhealthyGuppySpecular);
            guppyMaterial.setDiffuseColor(unhealthyGuppyDiffuse);
        }

        return guppyMaterial;
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

    @Override
    public void update(Observable o, Object arg) {
        // TODO Auto-generated method stub
        
    }

}
