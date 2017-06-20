package ca.bcit.comp1510.guppies;

import java.util.ArrayList;
import java.util.Random;

/**
 * An Ecosystem, which contains Pools, which contain Guppies.
 * 
 * @author griffin
 * @version 1.0
 */
public class Ecosystem {

    /**
     * The Pools in the Ecosystem.
     */
    private ArrayList<Pool> pools;

    /**
     * The Streams in the Ecosystem.
     */
    private ArrayList<Stream> streams;

    /**
     * Creates an Ecosystem without any Pools.
     */
    public Ecosystem() {
        pools = new ArrayList<Pool>();
        streams = new ArrayList<Stream>();
    }

    /**
     * Drives the program.
     * 
     * @param args
     *            command line arguments
     */
    public static void main(String[] args) {
        final int numberOfWeeks = 20;
        Ecosystem ecosystem = new Ecosystem();
        ecosystem.setUpSimulation();
        ecosystem.simulate(numberOfWeeks);
    }

    /**
     * Returns the Ecosystem's Pools.
     * 
     * @return the Ecosystem's Pools
     */
    public ArrayList<Pool> getPools() {
        return pools;
    }

    /**
     * Sets the Ecosystem's Pools; ignores null values.
     * 
     * @param pools
     *            the Ecosystem's Pools
     */
    public void setPools(ArrayList<Pool> pools) {
        if (pools != null) {
            this.pools = pools;
        }
    }

    /**
     * Returns the Ecosystem's Streams.
     * 
     * @return the Ecosystem's Streams
     */
    public ArrayList<Stream> getStreams() {
        return streams;
    }

    /**
     * Sets the Ecosystem's Streams; ignores null values.
     * 
     * @param streams
     *            the Ecosystem's Streams
     */
    public void setStreams(ArrayList<Stream> streams) {
        if (streams != null) {
            this.streams = streams;
        }
    }

    /**
     * Adds a single Stream to the Ecosystem. No duplicates allowed
     * 
     * @param stream
     *            the Stream to add
     */
    public void addStream(Stream stream) {
        if (!streams.contains(stream)) {
            streams.add(stream);
        }
    }

    /**
     * Adds a new Pool to the Ecosytem, as long as the Pool is not null.
     * 
     * @param newPool
     *            the Pool to add
     */
    public void addPool(Pool newPool) {
        if (newPool != null) {
            pools.add(newPool);
        }
    }

    /**
     * Clears all the Pools from the Ecosystem.
     */
    public void reset() {
        pools.clear();
    }

    /**
     * Returns the total Guppy population of all the Pools in the Ecosystem.
     * 
     * @return the total Guppy population of all the Pools in the Ecosystem
     */
    public int getGuppyPopulation() {
        int population = 0;

        for (Pool pool : pools) {
            population += pool.getPopulation();
        }

        return population;
    }

    /**
     * Sets up an Ecosystem with three Pools: Skookumchuk, Rutherford and
     * Gamelin.
     */
    public void setUpSimulation() {
        Pool skookumchuk = skookumchuk();
        Pool rutherford = rutherford();
        Pool gamelin = gamelin();
        
        addPool(skookumchuk);
        addPool(rutherford);
        addPool(gamelin);
        
        addStream(new Stream(skookumchuk, rutherford));
        addStream(new Stream(rutherford, gamelin));
    }

    /**
     * Creates a Pool called Skookumchuk with some Guppies.
     * 
     * @return the newly created and populated Pool object
     */
    private Pool skookumchuk() {
        Pool skookumchuk;

        final double volume = 1000.0;
        final double temperature = 42.0;
        final double pH = 7.9;
        final double nutrientCoefficient = 0.9;
        final double femaleProbability = 0.5;

        skookumchuk = new Pool("Skookumchuk", volume, temperature, pH,
                nutrientCoefficient);

        Random generator = new Random();

        final int guppies = 100;
        final int ageRange = 16;
        final int minAge = 10;
        final double healthCoefficientRange = 0.3;
        final double minHealthCoefficient = 0.5;

        int ageInWeeks;
        double healthCoefficient;
        double femaleRoll;
        for (int i = 1; i <= guppies; i++) {
            ageInWeeks = generator.nextInt(ageRange) + minAge;
            healthCoefficient = generator.nextDouble() * healthCoefficientRange
                    + minHealthCoefficient;
            femaleRoll = generator.nextDouble();

            skookumchuk.addGuppy(new Guppy("Poecilia", "reticulata", ageInWeeks,
                    femaleRoll < femaleProbability, 0, healthCoefficient));
        }

        return skookumchuk;
    }

    /**
     * Creates a Pool called Rutherford with some Guppies.
     * 
     * @return the newly created and populated Pool object
     */
    private Pool rutherford() {
        Pool rutherford;

        final double volume = 5000.0;
        final double temperature = 39.0;
        final double pH = 7.7;
        final double nutrientCoefficient = 0.85;
        final double femaleProbability = 0.5;

        rutherford = new Pool("Rutherford", volume, temperature, pH,
                nutrientCoefficient);

        Random generator = new Random();

        final int guppies = 100;
        final int ageRange = 6;
        final int minAge = 10;
        final double healthCoefficientRange = 0.2;
        final double minHealthCoefficient = 0.8;

        int ageInWeeks;
        double healthCoefficient;
        double femaleRoll;
        for (int i = 1; i <= guppies; i++) {
            ageInWeeks = generator.nextInt(ageRange) + minAge;
            healthCoefficient = generator.nextDouble() * healthCoefficientRange
                    + minHealthCoefficient;
            femaleRoll = generator.nextDouble();

            rutherford.addGuppy(new Guppy("Poecilia", "reticulata", ageInWeeks,
                    femaleRoll < femaleProbability, 0, healthCoefficient));
        }

        return rutherford;
    }

    /**
     * Creates a Pool called Gamelin with some Guppies.
     * 
     * @return the newly created and populated Pool object
     */
    private Pool gamelin() {
        Pool gamelin;

        final double volume = 4300.0;
        final double temperature = 37.0;
        final double pH = 7.5;
        final double nutrientCoefficient = 1.0;
        final double femaleProbability = 0.5;

        gamelin = new Pool("Gamelin", volume, temperature, pH,
                nutrientCoefficient);

        Random generator = new Random();

        final int guppies = 30;
        final int ageRange = 35;
        final int minAge = 15;

        int ageInWeeks;
        double healthCoefficient;
        double femaleRoll;
        for (int i = 1; i <= guppies; i++) {
            ageInWeeks = generator.nextInt(ageRange) + minAge;
            healthCoefficient = generator.nextDouble();
            femaleRoll = generator.nextDouble();

            gamelin.addGuppy(new Guppy("Poecilia", "reticulata", ageInWeeks,
                    femaleRoll < femaleProbability, 0, healthCoefficient));
        }

        return gamelin;
    }

    /**
     * Calls simulateOneWeek() a number of times.
     * 
     * @param numberOfWeeks
     *            the number of times to call simulateOneWeek()
     */
    public void simulate(int numberOfWeeks) {
        for (int i = 1; i <= numberOfWeeks; i++) {
            simulateOneWeek(i);
        }
    }

    /**
     * Increments all Guppies' ages, and kills off the old, hungry and
     * crowded-out ones. Removes all dead Guppies, and checks that the number of
     * dead is equal to the number removed. Spawns new Guppies.
     * 
     * @param weekNumber
     *            an ordinal number representing the current week
     */
    public void simulateOneWeek(int weekNumber) {
        int diedOfOldAge = 0;
        int numberRemoved = 0;
        int starvedToDeath = 0;
        int newFry = 0;
        int crowdedOut = 0;
        for (Pool pool : pools) {
            diedOfOldAge += pool.incrementAges();
            numberRemoved += pool.removeDeadGuppies();
            starvedToDeath += pool.applyNutrientCoefficient();
            numberRemoved += pool.removeDeadGuppies();
            crowdedOut += pool.adjustForCrowding();
            numberRemoved += pool.removeDeadGuppies();
            newFry += pool.spawn();
        }

        if (diedOfOldAge + starvedToDeath + crowdedOut == numberRemoved) {
            System.out.println("Week " + weekNumber + ":");
            System.out.println("Died of old age: " + diedOfOldAge);
            System.out.println("Died of starvation: " + starvedToDeath);
            System.out.println("Died of overcrowding: " + crowdedOut);
            System.out.println("Number of births: " + newFry);
            for (Pool pool : pools) {
                System.out.println(pool.getName() + " population: "
                        + pool.getPopulation());
            }
            System.out.println("Ecosystem population: " + getGuppyPopulation());

        } else {
            System.out.println("PAAAAAANNNNIIIIIIIIIIIC!!!!");
        }
    }

    /**
     * Adjusts all the Pools for crowding by calling adjustForCrowding().
     * 
     * @return the amount of Guppies crowded out
     */
    public int adjustForCrowding() {
        int crowdedOut = 0;

        for (Pool pool : pools) {
            crowdedOut += pool.adjustForCrowding();
        }

        return crowdedOut;
    }

}
