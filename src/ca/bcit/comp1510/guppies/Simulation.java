package ca.bcit.comp1510.guppies;

import java.util.ArrayList;
import java.util.Random;

/**
 * Runs a Guppy simulation.
 * 
 * @author griffin
 * @version 1.0
 */
public class Simulation {

    /**
     * The Ecosystem we will use in our simulation.
     */
    private static Ecosystem ecosystem;

    /**
     * Drives the program.
     * 
     * @param args
     *            command line arguments
     */
    public static void main(String[] args) {
        final int numberOfWeeks = 20;
        ecosystem = new Ecosystem();
        setUpSimulation();
        simulate(numberOfWeeks);
    }

    /**
     * Sets up the Ecosystem with three Pools: Skookumchuk, Rutherford and
     * Gamelin.
     */
    private static void setUpSimulation() {
        final int skookumchukVolume = 1000;
        final int skookumchukTemperature = 42;
        final double skookumchukPH = 7.9;
        final double skookumchukNutrientCoefficient = 0.9;
        final int skookumchukNumberOfGuppies = 100;
        final int skookumchukMinAge = 10;
        final int skookumchukMaxAge = 25;
        final double skookumchukMinHealthCoefficient = 0.5;
        final double skookumchukMaxHealthCoefficient = 0.8;

        GuppySet skookumchukGuppies = new GuppySet(skookumchukNumberOfGuppies,
                skookumchukMinAge, skookumchukMaxAge,
                skookumchukMinHealthCoefficient,
                skookumchukMaxHealthCoefficient);

        final int rutherfordVolume = 5000;
        final int rutherfordTemperature = 39;
        final double rutherfordPH = 7.7;
        final double rutherfordNutrientCoefficient = 0.85;
        final int rutherfordNumberOfGuppies = 100;
        final int rutherfordMinAge = 10;
        final int rutherfordMaxAge = 15;
        final double rutherfordMinHealthCoefficient = 0.8;
        final double rutherfordMaxHealthCoefficient = 1.0;

        GuppySet rutherfordGuppies = new GuppySet(rutherfordNumberOfGuppies,
                rutherfordMinAge, rutherfordMaxAge,
                rutherfordMinHealthCoefficient, rutherfordMaxHealthCoefficient);

        final int gamelinVolume = 4300;
        final int gamelinTemperature = 37;
        final double gamelinPH = 7.5;
        final double gamelinNutrientCoefficient = 1.0;
        final int gamelinNumberOfGuppies = 30;
        final int gamelinMinAge = 15;
        final int gamelinMaxAge = 49;
        final double gamelinMinHealthCoefficient = 0.0;
        final double gamelinMaxHealthCoefficient = 1.0;

        GuppySet gamelinGuppies = new GuppySet(gamelinNumberOfGuppies,
                gamelinMinAge, gamelinMaxAge, gamelinMinHealthCoefficient,
                gamelinMaxHealthCoefficient);

        Pool skookumchuk = setUpPool("Skookumchuk", skookumchukVolume,
                skookumchukTemperature, skookumchukPH,
                skookumchukNutrientCoefficient, skookumchukGuppies);
        Pool rutherford = setUpPool("Rutherford", rutherfordVolume,
                rutherfordTemperature, rutherfordPH,
                rutherfordNutrientCoefficient, rutherfordGuppies);
        Pool gamelin = setUpPool("Gamelin", gamelinVolume, gamelinTemperature,
                gamelinPH, gamelinNutrientCoefficient, gamelinGuppies);

        ecosystem.addPool(skookumchuk);
        ecosystem.addPool(rutherford);
        ecosystem.addPool(gamelin);

        ecosystem.addStream(new Stream(skookumchuk, rutherford));
        ecosystem.addStream(new Stream(rutherford, gamelin));
    }

    /**
     * Creates a Pool with some Guppies according to the parameters.
     * 
     * @param name
     *            the name of the Pool
     * @param volume
     *            the volume of the Pool, in litres
     * @param temp
     *            the temperature of the Pool, in degrees Celsius
     * @param pH
     *            the pH level of the Pool
     * @param nutrientCoefficient
     *            the nutrient coefficient of the Pool
     * @param startingGuppies
     *            a GuppySet object with attributes outlining the rules for the
     *            initial Guppies in the Pool
     * @return the newly created Pool
     */
    private static Pool setUpPool(String name, double volume, double temp,
            double pH, double nutrientCoefficient, GuppySet startingGuppies) {
        Pool pool = new Pool(name, volume, temp, pH, nutrientCoefficient);

        pool.setGuppiesInPool(startingGuppies.generateGuppies());

        return pool;
    }
    
    private static void simulate(int numberOfWeeks) {
        for (int i = 1; i <= numberOfWeeks; i++) {
            ecosystem.simulateOneWeek(i);
        }
    }

    private static class GuppySet {
        private int numberOfGuppies;
        int minAge;
        int maxAge;
        double minHealthCoefficient;
        double maxHealthCoefficient;

        GuppySet(int numberOfGuppies, int minAge, int maxAge,
                double minHealthCoefficient, double maxHealthCoefficient) {
            this.numberOfGuppies = numberOfGuppies;
            this.minAge = minAge;
            this.maxAge = maxAge;
            this.minHealthCoefficient = minHealthCoefficient;
            this.maxHealthCoefficient = maxHealthCoefficient;
        }

        public ArrayList<Guppy> generateGuppies() {
            ArrayList<Guppy> guppies = new ArrayList<Guppy>();

            final double femaleProbability = 0.5;

            Random generator = new Random();

            int ageInWeeks;
            double healthCoefficient;
            double femaleRoll;
            for (int i = 1; i <= numberOfGuppies; i++) {
                ageInWeeks = generator.nextInt(maxAge - minAge) + minAge;
                healthCoefficient = generator.nextDouble()
                        * (maxHealthCoefficient - minHealthCoefficient)
                        + minHealthCoefficient;
                femaleRoll = generator.nextDouble();

                guppies.add(new Guppy("Poecilia", "reticulata", ageInWeeks,
                        femaleRoll < femaleProbability, 0, healthCoefficient));
            }

            return guppies;
        }
    }

}
