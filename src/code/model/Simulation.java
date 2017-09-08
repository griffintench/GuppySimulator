package code.model;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Random;

/**
 * Runs a Guppy simulation.
 * 
 * @author griffin
 * @version 1.0
 */
public class Simulation extends Observable {

    /**
     * The Ecosystem we will use in our simulation.
     */
    private Ecosystem ecosystem;

    /**
     * The number of weeks that have elapsed since the start of the simulation.
     */
    private int weeksElapsed;

    /**
     * Constructor; initializes the Ecosystem and then sets up the week number.
     */
    public Simulation() {
        ecosystem = new Ecosystem();
        weeksElapsed = 0;
    }

    /**
     * Returns the Simulation's Ecosystem.
     * 
     * @return the Simulation's Ecosystem
     */
    public Ecosystem getEcosystem() {
        return ecosystem;
    }

    /**
     * Returns the number of weeks since the start of the simulation.
     * 
     * @return the number of weeks since the start of the simulation
     */
    public int getWeeksElapsed() {
        return weeksElapsed;
    }

    /**
     * Sets up the Ecosystem with three Pools: Skookumchuk, Rutherford and
     * Gamelin.
     * 
     * TODO come up with a better name for this method when necessary
     */
    public void setUpEcosystem() {
        Pool skookumchuk = setUpSkookumchuk();
        Pool rutherford = setUpRutherford();
        Pool gamelin = setUpGamelin();

        ecosystem.addPool(skookumchuk);
        ecosystem.addPool(rutherford);
        ecosystem.addPool(gamelin);

        ecosystem.addStream(new Stream(skookumchuk, rutherford));
        ecosystem.addStream(new Stream(rutherford, gamelin));
    }

    /**
     * Sets up the Skookumchuk Pool.
     * 
     * @return the Skookumchuk Pool
     */
    private Pool setUpSkookumchuk() {
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

        Pool skookumchuk = setUpPool("Skookumchuk", skookumchukVolume,
                skookumchukTemperature, skookumchukPH,
                skookumchukNutrientCoefficient, skookumchukGuppies);

        return skookumchuk;
    }

    /**
     * Sets up the Rutherford Pool.
     * 
     * @return the Rutherford Pool
     */
    private Pool setUpRutherford() {
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

        Pool rutherford = setUpPool("Rutherford", rutherfordVolume,
                rutherfordTemperature, rutherfordPH,
                rutherfordNutrientCoefficient, rutherfordGuppies);

        return rutherford;
    }

    /**
     * Sets up the Gamelin Pool.
     * 
     * @return the Gamelin Pool
     */
    private Pool setUpGamelin() {
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

        Pool gamelin = setUpPool("Gamelin", gamelinVolume, gamelinTemperature,
                gamelinPH, gamelinNutrientCoefficient, gamelinGuppies);

        return gamelin;
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

        pool.setFishInPool(startingGuppies.generateGuppies());

        return pool;
    }

    /**
     * Runs the simulation for the specified number of weeks.
     * 
     * @param numberOfWeeks
     *            the number of weeks to simulate
     */
    public void simulate(int numberOfWeeks) {
        setChanged();
        notifyObservers(true);

        int finalWeek = weeksElapsed + numberOfWeeks;

        for (int i = weeksElapsed + 1; i <= finalWeek; i++) {
            ecosystem.simulateOneWeek(i);
            weeksElapsed++;
            constructPoolList();
        }

        setChanged();
        notifyObservers(false);
    }

    private void constructPoolList() {
        ArrayList<int[]> list = ecosystem.constructPoolList();
        setChanged();
        notifyObservers(list);
    }

    /**
     * Returns the population of this simulation's Ecosystem.
     * 
     * @return the population of this simulation's Ecosystem
     */
    public int getEcosystemPopulation() {
        return ecosystem.getGuppyPopulation();
    }

    /**
     * A Set of Guppies to use to initialize a Pool in the Simulation.
     * 
     * @author griffin
     * @version 1.0
     */
    private class GuppySet {

        /**
         * The number of Guppies to initialize.
         */
        private int numberOfGuppies;

        /**
         * The minimum starting age of all the initial Guppies.
         */
        private int minAge;

        /**
         * The maximum starting age of all the initial Guppies.
         */
        private int maxAge;

        /**
         * The minimum starting health coefficient of all the initial Guppies.
         */
        private double minHealthCoefficient;

        /**
         * The maximum starting health coefficient of all the initial Guppies.
         */
        private double maxHealthCoefficient;

        /**
         * Constructor; sets all the instance variables accordingly.
         * 
         * @param numberOfGuppies
         *            the initial number of Guppies
         * @param minAge
         *            the minimum starting age of all the initial Guppies
         * @param maxAge
         *            the maximum starting age of all the initial Guppies
         * @param minHealthCoefficient
         *            the minimum starting health coefficient of all the initial
         *            Guppies
         * @param maxHealthCoefficient
         *            the maximum starting health coefficient of all the initial
         *            Guppies
         */
        GuppySet(int numberOfGuppies, int minAge, int maxAge,
                double minHealthCoefficient, double maxHealthCoefficient) {
            this.numberOfGuppies = numberOfGuppies;
            this.minAge = minAge;
            this.maxAge = maxAge;
            this.minHealthCoefficient = minHealthCoefficient;
            this.maxHealthCoefficient = maxHealthCoefficient;
        }

        /**
         * Generates an ArrayList object with our initial Guppies.
         * 
         * @return an ArrayList object with our initial Guppies
         */
        public ArrayList<Fish> generateGuppies() {
            ArrayList<Fish> generatedFish = new ArrayList<Fish>();

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

                generatedFish.add(new Guppy("Poecilia", "reticulata",
                        ageInWeeks, femaleRoll < femaleProbability, 0,
                        healthCoefficient));
            }

            return generatedFish;
        }
    }
}
