package code.model;

import java.util.ArrayList;
import java.util.Random;

/**
 * A Pool class with some Guppies in it.
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
    private String name;

    /**
     * The volume of the Pool, in litres.
     */
    private double volumeLitres;

    /**
     * The nutrient coefficient of the Pool. Must be between 0.0 and 1.0,
     * inclusive. The higher the nutrient coefficient, the less likely it is for
     * any given Guppy to die.
     */
    private double nutrientCoefficient;

    /**
     * A unique identifier for the Pool.
     */
    private int identificationNumber;

    /**
     * An ArrayList of all the Guppies currently in the Pool.
     */
    private FishGroup guppiesInPool;

    /**
     * A random number generator, used to determine which Guppies survive and
     * which die.
     */
    private Random randomNumberGenerator;

    /**
     * An ArrayList of all Streams leading to this Pool.
     */
    private ArrayList<Stream> streamsTo;

    /**
     * An ArrayList of all Streams leading away from this Pool.
     */
    private ArrayList<Stream> streamsFrom;

    /**
     * Instantiates a Pool with default parameters.
     */
    public Pool() {

        super();

        streamsTo = new ArrayList<Stream>();
        streamsFrom = new ArrayList<Stream>();
        setVolumeLitres(0.0);
        setName(DEFAULT_POOL_NAME);
        setNutrientCoefficient(DEFAULT_NUTRIENT_COEFFICIENT);
        setGuppiesInPool(new ArrayList<Guppy>());

        randomNumberGenerator = new Random();

        identificationNumber = ++numberOfPools;

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
        setName(DEFAULT_POOL_NAME);
        setNutrientCoefficient(DEFAULT_NUTRIENT_COEFFICIENT);

        setVolumeLitres(newVolumeLitres);
        setName(newName);
        setTemperatureCelsius(newTemperatureCelsius);
        setPH(newPH);
        setNutrientCoefficient(newNutrientCoefficient);

        ArrayList<Guppy> newGuppiesInPool = new ArrayList<Guppy>();
        setGuppiesInPool(newGuppiesInPool);

        randomNumberGenerator = new Random();

        identificationNumber = ++numberOfPools;

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
     * Sets the name of the Pool.
     * 
     * @param newName
     *            the name of the Pool
     */
    public void setName(String newName) {
        if (newName != null) {
            newName = newName.replaceAll("\\s", "");
            if (!newName.equals("")) {
                String firstLetter = newName.substring(0, 1);
                String restOfName = newName.substring(1, newName.length());
                firstLetter = firstLetter.toUpperCase();
                restOfName = restOfName.toLowerCase();

                name = firstLetter + restOfName;
            }
        }
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
        if (streamsFrom != null && !streamsFrom.isEmpty()) {
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
     * Returns an ArrayList object holding the Guppies in the Pool.
     * 
     * @return an ArrayList object holding the Guppies in the Pool
     */
    public FishGroup getGuppiesInPool() {
        return guppiesInPool;
    }

    /**
     * Sets the FishGroup object holding the Guppies in the Pool.
     * 
     * @param newGuppiesInPool
     *            an FishGroup object holding the Guppies in the Pool
     */
    public void setGuppiesInPool(FishGroup newGuppiesInPool) {
        if (newGuppiesInPool != null) {
            guppiesInPool = newGuppiesInPool;
        }
    }

    /**
     * Sets the FishGroup object holding the Guppies in the Pool; the FishGroup
     * object is creating using an ArrayList object.
     * 
     * @param newGuppiesInPool
     *            an ArrayList object holding the Guppies in the Pool
     */
    public void setGuppiesInPool(ArrayList<Guppy> newGuppiesInPool) {
        if (newGuppiesInPool != null) {
            ArrayList<Fish> fish = new ArrayList<Fish>();
            for (Guppy guppy : newGuppiesInPool) {
                fish.add(guppy);
            }
            guppiesInPool = new FishGroup(fish);
        }
    }

    /**
     * Returns an ArrayList object holding all Streams to this Pool.
     * 
     * @return an ArrayList object holding all Streams to this Pool
     */
    public ArrayList<Stream> getStreamsTo() {
        return streamsTo;
    }

    /**
     * Sets the ArrayList object holding all Streams to this Pool.
     * 
     * @param newStreamsTo
     *            an ArrayList object holding all Streams to this Pool
     */
    public void setStreamsTo(ArrayList<Stream> newStreamsTo) {
        streamsTo = newStreamsTo;
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
            if (!streamsTo.contains(stream)) {
                streamsTo.add(stream);
            }
        }
    }

    /**
     * Returns an ArrayList object holding all Streams from this Pool.
     * 
     * @return an ArrayList object holding all Streams from this Pool
     */
    public ArrayList<Stream> getStreamsFrom() {
        return streamsFrom;
    }

    /**
     * Sets the ArrayList object holding all Streams from this Pool.
     * 
     * @param newStreamsFrom
     *            an ArrayList object holding all Streams from this Pool
     */
    public void setStreamsFrom(ArrayList<Stream> newStreamsFrom) {
        streamsFrom = newStreamsFrom;
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
            if (!streamsFrom.contains(stream)) {
                streamsFrom.add(stream);
            }
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
     * Adds a Guppy to the Pool.
     * 
     * @param guppy
     *            the Guppy to add to the Pool
     * @return true if a Guppy was successfully added; false otherwise
     */
    public boolean addGuppy(Guppy guppy) {
        return guppiesInPool.addFish(guppy);
    }

    /**
     * Counts and returns the number of Guppies in the Pool.
     * 
     * @return the number of Guppies in the Pool
     */
    public int getPopulation() {
        return guppiesInPool.getPopulation();
    }

    /**
     * Counts and returns the number of living Guppies in the Pool.
     * 
     * @return the number of living Guppies in the Pool
     */
    public int getLivingPopulation() {
        return guppiesInPool.getLivingPopulation();
    }

    /**
     * Loops through all the Guppies in the Pool and determines if each
     * survives. Uses the Nutrient Coefficient to determine how likely it is
     * that any given Guppy survives.
     * 
     * @return the number of newly dead Guppies
     */
    public int applyNutrientCoefficient() {
        return guppiesInPool.applyNutrientCoefficient(nutrientCoefficient);
    }

    /**
     * Removes any dead Guppies from the guppiesInPool array.
     * 
     * @return the number of Guppies removed from the array
     */
    public int removeDeadGuppies() {
        return guppiesInPool.removeDeadFish();
    }

    /**
     * Calculates and returns the total volume needed to sustain all the Guppies
     * currently in the Pool, in litres.
     * 
     * @return the total volume needed to sustain all the Guppies currently in
     *         the Pool, in litres
     */
    public double getGuppyVolumeRequirementInLitres() {
        return guppiesInPool.getFishVolumeRequirementInLitres();
    }

    /**
     * Calculates and returns the average age in weeks of all the Guppies in the
     * Pool.
     * 
     * @return the average age in weeks of all the Guppies in the Pool
     */
    public double getAverageAgeInWeeks() {
        return guppiesInPool.getAverageAgeInWeeks();
    }

    /**
     * Calculates and returns the average health coefficient of all the Guppies
     * in the Pool.
     * 
     * @return the average health coefficient of all the Guppies in the Pool
     */
    public double getAverageHealthCoefficient() {
        return guppiesInPool.getAverageHealthCoefficient();
    }

    /**
     * Calculates and returns the percentage of living Guppies in the Pool that
     * are female.
     * 
     * @return the average health coefficient of all the Guppies in the Pool
     */
    public double getFemalePercentage() {
        return guppiesInPool.getFemalePercentage();
    }

    /**
     * Evaluates and returns the median age of all the living Guppies in the
     * Pool.
     * 
     * @return the median age of all the living Guppies in the Pool
     */
    public double getMedianAge() {
        return guppiesInPool.getMedianAge();
    }

    /**
     * Invokes the spawn() method on all the Guppies in the Pool. Any baby
     * Guppies born are added to the Pool. Returns the number of baby Guppies
     * added to the Pool.
     * 
     * @return the number of baby Guppies added to the Pool
     */
    public int spawn() {
        return guppiesInPool.spawn();
    }

    /**
     * Increments the ages of all the Guppies in the pool. Returns the number of
     * Guppies that die of old age.
     * 
     * @return the number of Guppies that have died of old age
     */
    public int incrementAges() {
        return guppiesInPool.incrementAges();
    }

    /**
     * Crowds out the weakest Guppies until the volume requirement is less than
     * or equal to the Pool's volume.
     * 
     * @return the number of Guppies killed off
     */
    public int adjustForCrowding() {
        int killed = 0;

        while (getGuppyVolumeRequirementInLitres() > volumeLitres) {
            Fish weakest = guppiesInPool.getWeakest();
            if (weakest instanceof Guppy) {
                Guppy weakestGuppy = (Guppy) weakest;
                //crowdOut(weakestGuppy);
                weakestGuppy.kill();
                if (!weakestGuppy.isAlive()) {
                    killed++;
                }
            }
        }
        return killed;
    }

    /**
     * Crowds out a Guppy by either killing it or sending it downstream.
     * 
     * @param guppy
     *            the Guppy to crowd out. Must be in this Pool.
     */
    private void crowdOut(Guppy guppy) {
        if (!guppiesInPool.contains(guppy)) {
            throw new IllegalArgumentException();
        }
        if (streamsFrom.isEmpty()) {
            guppy.kill();
        } else {
            double healthCoefficient = guppy.getHealthCoefficient();
            Random generator = new Random();
            double healthRoll = generator.nextDouble();
            if (healthRoll < healthCoefficient) {
                sendDownstream(guppy);
            } else {
                guppy.kill();
            }
        }
    }

    /**
     * Sends a Guppy downstream. Chooses a random Stream away from this Pool and
     * sends the Guppy to the right destination Pool.
     * 
     * @param guppy
     *            the Guppy to send downstream. Must be in this Pool.
     */
    private void sendDownstream(Guppy guppy) {
        if (!guppiesInPool.contains(guppy)) {
            throw new IllegalArgumentException();
        }
        guppiesInPool.remove(guppy);
        int streams = streamsFrom.size();

        int streamNumber = randomNumberGenerator.nextInt(streams);
        streamsFrom.get(streamNumber).getDestination().addGuppy(guppy);

    }

    @Override
    public String toString() {
        return "Pool [name=" + name + ", volumeLitres=" + volumeLitres
                + ", temperatureCelsius=" + getTemperatureCelsius() + ", pH="
                + getPH() + ", nutrientCoefficient=" + nutrientCoefficient
                + ", identificationNumber=" + identificationNumber
                + ", guppiesInPool=" + guppiesInPool
                + ", randomNumberGenerator=" + randomNumberGenerator + "]";
    }
}