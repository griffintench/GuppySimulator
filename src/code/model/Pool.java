package code.model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * A Pool class with some Fish in it.
 * 
 * @author griffin
 * @version 1
 */
public class Pool extends WaterBody {

    /**
     * A default name for the pool.
     */
    public static final String DEFAULT_POOL_NAME = "Unnamed";

    /**
     * A default nutrient coefficient for the pool.
     */
    public static final double DEFAULT_NUTRIENT_COEFFICIENT = 0.50;

    /**
     * A minimum nutrient coefficient for the pool.
     */
    public static final double MINIMUM_NUTRIENT_COEFFICIENT = 0.0;

    /**
     * A maximum nutrient coefficient for the pool.
     */
    public static final double MAXIMUM_NUTRIENT_COEFFICIENT = 1.0;

    /**
     * The number of Pools in existence.
     */
    private static int numberOfPools;

    /**
     * The name of the Pool.
     */
    private final String name;

    /**
     * The volume of the Pool, in litres.
     */
    private double volumeLitres;

    /**
     * The nutrient coefficient of the Pool. Must be between 0.0 and 1.0,
     * inclusive. The higher the nutrient coefficient, the less likely it is for
     * any given Fish to die.
     */
    private double nutrientCoefficient;

    /**
     * A unique identifier for the Pool.
     */
    private final int identificationNumber;

    /**
     * An group of all the Fish currently in the Pool.
     */
    private FishGroup fishInPool;

    /**
     * A random number generator, used to determine which Fish survive and which
     * die.
     */
    private final Random randomNumberGenerator;

    /**
     * An ArrayList of all Streams leading to this Pool.
     */
    private final ArrayList<Stream> streamsTo;

    /**
     * An ArrayList of all Streams leading away from this Pool.
     */
    private final ArrayList<Stream> streamsFrom;

    /**
     * Instantiates a Pool with default parameters.
     */
    public Pool() {
        this(DEFAULT_POOL_NAME, 0.0, DEFAULT_TEMP_CELSIUS, NEUTRAL_PH,
                DEFAULT_NUTRIENT_COEFFICIENT);
    }

    /**
     * Instantiates a Pool with non-default parameters.
     * 
     * @param newName
     *            the name of the Pool
     * @param newVolumeLitres
     *            the Pool's volume, in litres
     * @param newTemperatureCelsius
     *            the Pool's temperature, in degrees Celsius
     * @param newPH
     *            the Pool's pH level
     * @param newNutrientCoefficient
     *            the Pool's nutrient coefficient
     */
    public Pool(String newName, double newVolumeLitres,
            double newTemperatureCelsius, double newPH,
            double newNutrientCoefficient) {

        super(newTemperatureCelsius, newPH);

        streamsTo = new ArrayList<Stream>();
        streamsFrom = new ArrayList<Stream>();
        setVolumeLitres(0.0);
        setNutrientCoefficient(DEFAULT_NUTRIENT_COEFFICIENT);

        setVolumeLitres(newVolumeLitres);
        name = processName(newName);
        setTemperatureCelsius(newTemperatureCelsius);
        setPH(newPH);
        setNutrientCoefficient(newNutrientCoefficient);

        setFishInPool(new FishGroup());

        randomNumberGenerator = new Random();

        identificationNumber = ++numberOfPools;

    }

    private static String processName(String inputName) {
        if (inputName == null) {
            return DEFAULT_POOL_NAME;
        }
        inputName = inputName.replaceAll("\\s", "");
        if (inputName.equals("")) {
            return DEFAULT_POOL_NAME;
        }
        String firstLetter = inputName.substring(0, 1);
        String restOfName = inputName.substring(1, inputName.length());
        firstLetter = firstLetter.toUpperCase();
        restOfName = restOfName.toLowerCase();

        return firstLetter + restOfName;
    }

    /**
     * Returns the name of the Pool.
     * 
     * @return the name of the Pool
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the volume of the Pool in litres.
     * 
     * @return the volume of the Pool in litres
     */
    public double getVolumeLitres() {
        return volumeLitres;
    }

    /**
     * Sets the volume of the Pool in litres. Nonpositive values are ignored.
     * 
     * @param newVolumeLitres
     *            the volume of the Pool in litres.
     */
    public void setVolumeLitres(double newVolumeLitres) {
        if (newVolumeLitres > 0.0) {
            volumeLitres = newVolumeLitres;
        }
    }

    @Override
    public void setTemperatureCelsius(double newTemperatureCelsius) {
        super.setTemperatureCelsius(newTemperatureCelsius);
        if (streamsFrom != null && !streamsFrom.isEmpty()) {
            for (Stream stream : streamsFrom) {
                stream.setTemperatureCelsius(newTemperatureCelsius);
            }
        }
    }

    @Override
    public void setPH(double newPH) {
        super.setPH(newPH);
        if (streamsFrom != null) {
            for (Stream stream : streamsFrom) {
                stream.setPH(newPH);
            }
        }
    }

    /**
     * Returns the nutrient coefficient of the Pool.
     * 
     * @return the nutrient coefficient of the Pool
     */
    public double getNutrientCoefficient() {
        return nutrientCoefficient;
    }

    /**
     * Sets the nutrient coefficient of the Pool. The nutrient coefficient must
     * be between 0 and 1.0, inclusive.
     * 
     * @param newNutrientCoefficient
     *            the nutrient coefficient of the Pool
     */
    public void setNutrientCoefficient(double newNutrientCoefficient) {
        if (newNutrientCoefficient >= MINIMUM_NUTRIENT_COEFFICIENT
                && newNutrientCoefficient <= MAXIMUM_NUTRIENT_COEFFICIENT) {
            nutrientCoefficient = newNutrientCoefficient;
        }
    }

    /**
     * Returns the unique identification number of the Pool.
     * 
     * @return the unique identification number of the Pool
     */
    public int getIdentificationNumber() {
        return identificationNumber;
    }

    /**
     * Sets the FishGroup object holding the Fish in the Pool.
     * 
     * @param newFishInPool
     *            an FishGroup object holding the Fish in the Pool
     */
    public void setFishInPool(FishGroup newFishInPool) {
        if (newFishInPool != null) {
            fishInPool = newFishInPool;
        }
    }

    /**
     * Sets the FishGroup object holding the Fish in the Pool; the FishGroup
     * object is creating using an List object.
     * 
     * @param newFishInPool
     *            an List object holding the Fish in the Pool
     */
    public void setFishInPool(List<Fish> newFishInPool) {
        if (newFishInPool != null) {
            LinkedList<Fish> fish = new LinkedList<Fish>();
            for (Fish newFish : newFishInPool) {
                fish.add(newFish);
            }
            fishInPool = new FishGroup(fish);
        }
    }

    /**
     * Adds a Stream that leads to this Pool; no duplicates allowed.
     * 
     * @param stream
     *            a Stream that leads to this Pool
     */
    public void addStreamTo(Stream stream) {
        if (!streamsTo.contains(stream)) {
            streamsTo.add(stream);
        }
    }

    /**
     * Adds multiple Streams that lead to this Pool; no duplicates allowed.
     * 
     * @param streams
     *            an ArrayList object holding Streams that lead to this Pool
     */
    public void addStreamsTo(ArrayList<Stream> streams) {
        for (Stream stream : streams) {
            addStreamTo(stream);
        }
    }

    /**
     * Adds a Stream that leads from this Pool; no duplicates allowed.
     * 
     * @param stream
     *            a Stream that leads from this Pool
     */
    public void addStreamFrom(Stream stream) {
        if (!streamsFrom.contains(stream)) {
            streamsFrom.add(stream);
        }
    }

    /**
     * Adds multiple Streams that lead from this Pool; no duplicates allowed.
     * 
     * @param streams
     *            an ArrayList object holding Streams that lead from this Pool
     */
    public void addStreamsFrom(ArrayList<Stream> streams) {
        for (Stream stream : streams) {
            addStreamFrom(stream);
        }
    }

    /**
     * Returns the number of Pool objects that have been created.
     * 
     * @return the number of Pool objects that have been created
     */
    public static int getNumberCreated() {
        return numberOfPools;
    }

    /**
     * Prints the details of this Pool to the console.
     */
    public void printDetails() {
        System.out.println(toString());
    }

    /**
     * Adds a Fish to the Pool.
     * 
     * @param fish
     *            the Fish to add to the Pool
     * @return true if a Fish was successfully added; false otherwise
     */
    public boolean addFish(Fish fish) {
        return fishInPool.addFish(fish);
    }

    /**
     * Counts and returns the number of Fish in the Pool.
     * 
     * @return the number of Fish in the Pool
     */
    public int getPopulation() {
        return fishInPool.getPopulation();
    }

    /**
     * Counts and returns the number of living Fish in the Pool.
     * 
     * @return the number of living Fish in the Pool
     */
    public int getLivingPopulation() {
        return fishInPool.getLivingPopulation();
    }

    /**
     * Loops through all the Fish in the Pool and determines if each survives.
     * Uses the Nutrient Coefficient to determine how likely it is that any
     * given Fish survives.
     * 
     * @return the number of newly dead Fish
     */
    public int applyNutrientCoefficient() {
        return fishInPool.applyNutrientCoefficient(nutrientCoefficient);
    }

    /**
     * Removes any dead Fish from the fishInPool array.
     * 
     * @return the number of Fish removed from the array
     */
    public int removeDeadFish() {
        return fishInPool.removeDeadFish();
    }

    /**
     * Calculates and returns the total volume needed to sustain all the Fish
     * currently in the Pool, in litres.
     * 
     * @return the total volume needed to sustain all the Fish currently in the
     *         Pool, in litres
     */
    public double getFishVolumeRequirementInLitres() {
        return fishInPool.getFishVolumeRequirementInLitres();
    }

    /**
     * Calculates and returns the average age in weeks of all the Fish in the
     * Pool.
     * 
     * @return the average age in weeks of all the Fish in the Pool
     */
    public double getAverageAgeInWeeks() {
        return fishInPool.getAverageAgeInWeeks();
    }

    /**
     * Calculates and returns the average health coefficient of all the Fish in
     * the Pool.
     * 
     * @return the average health coefficient of all the Fish in the Pool
     */
    public double getAverageHealthCoefficient() {
        return fishInPool.getAverageHealthCoefficient();
    }

    /**
     * Calculates and returns the percentage of living Fish in the Pool that are
     * female.
     * 
     * @return the average health coefficient of all the Fish in the Pool
     */
    public double getFemalePercentage() {
        return fishInPool.getFemalePercentage();
    }

    /**
     * Evaluates and returns the median age of all the living Fish in the Pool.
     * 
     * @return the median age of all the living Fish in the Pool
     */
    public double getMedianAge() {
        return fishInPool.getMedianAge();
    }

    /**
     * Invokes the spawn() method on all the Fish in the Pool. Any baby Fish
     * born are added to the Pool. Returns the number of baby Fish added to the
     * Pool.
     * 
     * @return the number of baby Fish added to the Pool
     */
    public int spawn() {
        return fishInPool.spawn();
    }

    /**
     * Increments the ages of all the Fish in the pool. Returns the number of
     * Fish that die of old age.
     * 
     * @return the number of Fish that have died of old age
     */
    public int incrementAges() {
        return fishInPool.incrementAges();
    }

    /**
     * Crowds out the weakest Fish until the volume requirement is less than or
     * equal to the Pool's volume.
     * 
     * @return the number of Fish killed off
     */
    public int adjustForCrowding() {
        final double maxVolReq = 0.75;
        int killed = 0;
        double volReq;

        while ((volReq = getFishVolumeRequirementInLitres()) > volumeLitres) {
            double difference = volReq - volumeLitres;
            int minFishToRemove = (int) (difference / maxVolReq);
            if (minFishToRemove == 0) {
                minFishToRemove = 1;
            }

            List<Fish> weakest = fishInPool.getWeakest(minFishToRemove);

            for (Fish weakFish : weakest) {
                crowdOut(weakFish);
                if (!weakFish.isAlive()) {
                    killed++;
                }
            }
        }
        return killed;
    }

    /**
     * Crowds out a Fish by either killing it or sending it downstream.
     * 
     * IMPORTANT NOTE - I've commented out the first three lines for the sake of
     * better performance. I am trusting that this method will never be used on
     * a Fish that isn't actually in this Pool, since the Pool class doesn't
     * actually reference other Pools anyway. If another Pool gets referenced in
     * this Pool class, the lines should be uncommented.
     * 
     * @param fishToCrowd
     *            the Fish to crowd out
     */
    private void crowdOut(Fish fishToCrowd) {
        // NOTE TO SELF DO NOT DELETE
        // if (!fishInPool.contains(fishToCrowd)) {
        // throw new IllegalArgumentException();
        // }
        if (streamsFrom.isEmpty()) {
            fishInPool.killFish(fishToCrowd);
        } else {
            double healthCoefficient = fishToCrowd.getHealthCoefficient();
            double healthRoll = randomNumberGenerator.nextDouble();
            if (healthRoll < healthCoefficient) {
                sendDownstream(fishToCrowd);
            } else {
                fishInPool.killFish(fishToCrowd);
            }
        }
        fishInPool.setAsNotSorted();
    }

    /**
     * Sends a Fish downstream. Chooses a random Stream away from this Pool and
     * sends the Fish to the right destination Pool.
     * 
     * @param fishToSend
     *            the Fish to send downstream.
     */
    private void sendDownstream(Fish fishToSend) {
        boolean success = fishInPool.remove(fishToSend);
        if (success) {
            int streams = streamsFrom.size();

            int streamNumber = randomNumberGenerator.nextInt(streams);
            streamsFrom.get(streamNumber).sendDownstream(fishToSend);
        } else {
            throw new IllegalArgumentException(
                    "Tried to remove fish, but fish is not in this pool ("
                            + name + ")");
        }

    }

    /**
     * Returns an int array with the number of fish of each health type.
     * 
     * @return an int array with the number of fish of each health type
     */
    public int[] getFishHealthNumbers() {
        return fishInPool.getFishHealthNumbers();
    }

    /**
     * Sets this pool's Fish as not sorted.
     */
    public void setAsNotSorted() {
        fishInPool.setAsNotSorted();
    }

    @Override
    public String toString() {
        return "Pool [name=" + name + ", volumeLitres=" + volumeLitres
                + ", temperatureCelsius=" + getTemperatureCelsius() + ", pH="
                + getPH() + ", nutrientCoefficient=" + nutrientCoefficient
                + ", identificationNumber=" + identificationNumber
                + ", fishInPool=" + fishInPool + ", randomNumberGenerator="
                + randomNumberGenerator + "]";
    }
}
