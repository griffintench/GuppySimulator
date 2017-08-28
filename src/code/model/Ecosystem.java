package code.model;

import java.util.ArrayList;

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

    /**
     * Constructs the list of pools, and the number of Guppies of each health
     * type.
     * 
     * @return the list of pools, and the number of Guppies of each health type
     */
    public ArrayList<int[]> constructPoolList() {
        ArrayList<int[]> result = new ArrayList<int[]>();

        for (Pool pool : pools) {
            result.add(pool.getGuppyHealthNumbers());
        }

        return result;
    }
    
    /**
     * Returns all the Guppies of a specified Pool.
     * 
     * @param poolIndex
     *            the index of the Pool
     * @return an ArrayList of Fish (all Guppies)
     */
    public ArrayList<Fish> getGuppies(int poolIndex) {
        return pools.get(poolIndex).getGuppiesInPool();
    }

}