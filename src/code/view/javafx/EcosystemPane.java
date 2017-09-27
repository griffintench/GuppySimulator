package code.view.javafx;

import java.util.HashMap;
import java.util.Random;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.shape.Sphere;

/**
 * The pane showing the ecosystem and its pools and guppies.
 * 
 * @author griffin
 * @version 1.0
 */
public class EcosystemPane extends VBox {

    /** The amount of spacing between the week number and the pools. */
    public static final int SPACING = 20;

    /** Represents a healthy guppy. */
    public static final int HEALTHY = 0;

    /** Represents a guppy in okay health. */
    public static final int OKAY = 1;

    /** Represents an unhealthy guppy. */
    public static final int UNHEALTHY = 2;

    private HBox weekNumber;
    private Label weekLabel;

    private StackPane poolPane;

    private int sceneWidth;
    // private int sceneHeight;

    private Random generator;

    private HashMap<String, Box> poolBoxes;
    private HashMap<String, int[]> poolInfo; // name, [healthy, okay, unhealthy]

    /**
     * Creates an EcosystemPane with a certain width and height. Initializes the
     * Random and ArrayList variables.
     * 
     * @param sceneWidth
     *            the width of the pane
     * @param sceneHeight
     *            the height of the pane
     */
    public EcosystemPane(int sceneWidth, int sceneHeight) {
        super(SPACING);

        weekNumber = new HBox();
        weekLabel = new Label("Week 0");
        weekNumber.getChildren().add(weekLabel);
        weekNumber.setAlignment(Pos.CENTER);
        poolPane = new StackPane();
        getChildren().addAll(weekNumber, poolPane);
        setAlignment(Pos.CENTER);

        this.sceneWidth = sceneWidth;
        setPrefWidth(sceneWidth);
        // this.sceneHeight = sceneHeight;
        setPrefHeight(sceneHeight);

        generator = new Random();

        poolBoxes = new HashMap<String, Box>();
        poolInfo = null; // just making this explicit for understandability
                         // purposes
    }

    /**
     * Receives information about pools and guppies and translates into a change
     * in the viewable model.
     * 
     * @param arg
     *            a HashMap object containing information about the pools; each
     *            entry represents a pool in the form of an array of three ints,
     *            representing the numbers of healthy, okay and unhealthy
     *            guppies; each key is the pool's name
     */
    public void update(HashMap<String, int[]> arg) {
        if (poolInfo != null) {
            setUpPoolInfo(arg);

            poolPane.getChildren().clear(); // TODO in the future, this should
                                            // only clear the guppies, not the
                                            // pool boxes

            drawPools();
            drawGuppies();
        } else {
            initialize(arg);
        }
    }

    private void initialize(HashMap<String, int[]> arg) {
        setUpPoolBoxes(arg);
        setUpPoolInfo(arg);

        drawPools();
        drawGuppies();
    }

    private void setUpPoolBoxes(HashMap<String, int[]> arg) {
        final Color poolSpecular = new Color(0.0, 0.75, 1.0, 1.0);
        final Color poolDiffuse = new Color(0.0, 0.75, 1.0, 0.75);

        final int numberOfPools = arg.size();
        final int boxWidth = sceneWidth / (2 * numberOfPools + 1);

        poolBoxes = new HashMap<String, Box>();

        for (HashMap.Entry<String, int[]> entry : arg.entrySet()) {
            // create and colour box - doesn't position box, or add guppies, or
            // show box
            Box pool = new Box(boxWidth, boxWidth, boxWidth);

            PhongMaterial poolMaterial = new PhongMaterial();
            poolMaterial.setSpecularColor(poolSpecular);
            poolMaterial.setDiffuseColor(poolDiffuse);
            pool.setMaterial(poolMaterial);

            // add box to poolBoxes
            poolBoxes.put(entry.getKey(), pool);
        }
    }

    private void setUpPoolInfo(HashMap<String, int[]> arg) {
        poolInfo = arg;
    }

    private void drawPools() {
        final int numberOfPools = poolBoxes.size();
        final int boxWidth = sceneWidth / (2 * numberOfPools + 1);
        int poolNumber = 1;
        for (HashMap.Entry<String, Box> entry : poolBoxes.entrySet()) {
            double translation = 0.0
                    + (-numberOfPools + (2 * poolNumber) - 1) * boxWidth;
            Box curPool = entry.getValue();
            curPool.setTranslateX(translation);

            poolPane.getChildren().add(curPool);
            poolNumber++;
        }
    }

    private void drawGuppies() {
        for (HashMap.Entry<String, int[]> entry : poolInfo.entrySet()) {
            String name = entry.getKey();
            int[] guppyNumbers = entry.getValue();
            Box correspondingBox = poolBoxes.get(name);

            double translation = correspondingBox.getTranslateX();
            double boxWidth = correspondingBox.getWidth();

            final int maxGuppies = 100; // capping number of guppies per pool
                                        // per health type
            for (int healthType = 0; healthType <= UNHEALTHY; healthType++) {
                for (int guppy = 1; guppy <= maxGuppies
                        && guppy <= guppyNumbers[healthType]; guppy++) {
                    drawOneGuppy(translation, boxWidth, healthType);
                }
            }
        }
    }

    private void drawOneGuppy(double translation, double boxWidth, int health) {
        final Color healthyGuppySpecular = new Color(0.0, 1.0, 0.0, 1.0);
        final Color healthyGuppyDiffuse = new Color(0.0, 1.0, 0.0, 0.75);
        final Color okayGuppySpecular = new Color(1.0, 0.5, 0.0, 1.0);
        final Color okayGuppyDiffuse = new Color(1.0, 0.5, 0.0, 0.75);
        final Color unhealthyGuppySpecular = new Color(1.0, 0.0, 0.0, 1.0);
        final Color unhealthyGuppyDiffuse = new Color(1.0, 0.0, 0.0, 0.75);

        final int intWidth = (int) boxWidth;

        Sphere guppySphere = new Sphere(2);
        double guppyTranslationX = generator.nextInt(intWidth) + translation
                - boxWidth / 2;
        double guppyTranslationY = generator.nextInt(intWidth) - boxWidth / 2;
        double guppyTranslationZ = generator.nextInt(intWidth) - boxWidth / 2;
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
        poolPane.getChildren().add(guppySphere);
    }
}
