package code.view.javafx;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.shape.Sphere;

/**
 * @author griffin
 *
 */
public class EcosystemPane extends StackPane {
    private int sceneWidth;
    private int sceneHeight;
    
    public static final int HEALTHY = 0;
    public static final int OKAY = 1;
    public static final int UNHEALTHY = 2;

    private Random generator;

    private ArrayList<Box> pools;
    private ArrayList<Sphere> guppies;
    
    public EcosystemPane(int sceneWidth, int sceneHeight) {
        this.sceneWidth = sceneWidth;
        setPrefWidth(sceneWidth);
        this.sceneHeight = sceneHeight;
        setPrefHeight(sceneHeight);
        
        generator = new Random();
        
        pools = new ArrayList<Box>();
        guppies = new ArrayList<Sphere>();
    }
    
    public void update(ArrayList<int[]> arg) {
        pools = new ArrayList<Box>();
        guppies = new ArrayList<Sphere>();

        int numberOfPools = arg.size();
        int boxWidth = sceneWidth / (2 * numberOfPools + 1);

        for (int i = 1; i <= arg.size(); i++) {
            drawPool(i, boxWidth, numberOfPools, arg.get(i - 1));
        }

        for (Box pool : pools) {
            getChildren().add(pool);
        }

        for (Sphere guppy : guppies) {
            getChildren().add(guppy);
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
     * @param guppyNumbers
     *            an int array showing how many guppies there are of each health
     *            type
     */
    private void drawPool(int index, int boxWidth, int numberOfPools,
            int[] guppyNumbers) {
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

        for (int guppy = 1; guppy <= guppyNumbers[HEALTHY]; guppy++) {
            drawGuppy(translation, boxWidth, HEALTHY);
        }
        for (int guppy = 1; guppy <= guppyNumbers[OKAY]; guppy++) {
            drawGuppy(translation, boxWidth, OKAY);
        }
        for (int guppy = 1; guppy <= guppyNumbers[UNHEALTHY]; guppy++) {
            drawGuppy(translation, boxWidth, UNHEALTHY);
        }

        pools.add(pool);
    }

    /**
     * Draws a particular Guppy inside its pool.
     * 
     * @param translation
     *            A horizontal translation to ensure the Guppy is put in the
     *            right pool
     * @param boxWidth
     *            the width of the box representing the pool
     * @param health
     *            an int representing whether a Guppy is healthy, okay, or
     *            unhealthy
     */
    private void drawGuppy(double translation, int boxWidth, int health) {
        final Color healthyGuppySpecular = new Color(0.0, 1.0, 0.0, 1.0);
        final Color healthyGuppyDiffuse = new Color(0.0, 1.0, 0.0, 0.75);
        final Color okayGuppySpecular = new Color(1.0, 0.5, 0.0, 1.0);
        final Color okayGuppyDiffuse = new Color(1.0, 0.5, 0.0, 0.75);
        final Color unhealthyGuppySpecular = new Color(1.0, 0.0, 0.0, 1.0);
        final Color unhealthyGuppyDiffuse = new Color(1.0, 0.0, 0.0, 0.75);

        Sphere guppySphere = new Sphere(2);
        double guppyTranslationX = generator.nextInt(boxWidth) + translation
                - boxWidth / 2;
        double guppyTranslationY = generator.nextInt(boxWidth) - boxWidth / 2;
        double guppyTranslationZ = generator.nextInt(boxWidth) - boxWidth / 2;
        guppySphere.setTranslateX(guppyTranslationX);
        guppySphere.setTranslateY(guppyTranslationY);
        guppySphere.setTranslateZ(guppyTranslationZ);

        PhongMaterial guppyMaterial = new PhongMaterial();

        if (health == HEALTHY) {
            guppyMaterial.setSpecularColor(healthyGuppySpecular);
            guppyMaterial.setDiffuseColor(healthyGuppyDiffuse);
        } else if (health == OKAY) {
            guppyMaterial.setSpecularColor(okayGuppySpecular);
            guppyMaterial.setDiffuseColor(okayGuppyDiffuse);
        } else {
            guppyMaterial.setSpecularColor(unhealthyGuppySpecular);
            guppyMaterial.setDiffuseColor(unhealthyGuppyDiffuse);
        }
        guppySphere.setMaterial(guppyMaterial);
        guppies.add(guppySphere);
    }
}