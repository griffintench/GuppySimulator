package code.model;

import java.util.ArrayList;
import java.util.HashMap;

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
     * NOTE - don't remove the comments; they make it easier to troubleshoot
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
            // System.out.println("pool: " + pool.getName());
            diedOfOldAge += pool.incrementAges();
            // System.out.println("total aged: " + diedOfOldAge);
            numberRemoved += pool.removeDeadFish();
            // System.out.println("total removed: " + numberRemoved);
            starvedToDeath += pool.applyNutrientCoefficient();
            // System.out.println("total starved: " + starvedToDeath);
            numberRemoved += pool.removeDeadFish();
            // System.out.println("total removed: " + numberRemoved);
            newFry += pool.spawn();
            crowdedOut += pool.adjustForCrowding();
            // System.out.println("total crowded: " + crowdedOut);
            numberRemoved += pool.removeDeadFish();
            // System.out.println("total removed: " + numberRemoved);
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
                System.out.println(pool.getName() + " volume requirement: "
                        + pool.getFishVolumeRequirementInLitres());
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

    public HashMap<String, int[]> constructPoolList() {
        HashMap<String, int[]> result = new HashMap<String, int[]>();

        for (Pool pool : pools) {
            result.put(pool.getName(), pool.getFishHealthNumbers());
        }

        return result;
    }

}
