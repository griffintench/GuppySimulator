package ca.bcit.comp1510.guppies;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.Random;

/*
 * printDetails?
 * applyNutrientCoefficient() - can you declare a variable more than once
 *  (nested for loops do this right?)
 * the last four methods - necessary to check if Guppy is alive or should that
 *  be given? (esp the last one)
*/
/**
 * A Pool class with some Guppies in it.
 * 
 * @author griffin
 * @version 1
 */
public class Pool {

    /**
     * A default name for the pool.
     */
    public static final String DEFAULT_POOL_NAME = "Unnamed";

    /**
     * A default temperature for the pool, in degrees Celsius.
     */
    public static final double DEFAULT_POOL_TEMP_CELSIUS = 40.0;

    /**
     * The minimum temperature for the pool, in degrees Celsius. Any lower and
     * the pool would freeze over.
     */
    public static final double MINIMUM_POOL_TEMP_CELSIUS = 0.0;

    /**
     * The maximum temperature for the pool, in degrees Celsius. Any lower and
     * the pool would boil.
     */
    public static final double MAXIMUM_POOL_TEMP_CELSIUS = 100.0;

    /**
     * The point in the middle of the pH scale.
     */
    public static final double NEUTRAL_PH = 7.0;

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
     * The current temperature of the Pool, in degrees Celsius.
     */
    private double temperatureCelsius;

    /**
     * The current pH level of the Pool. Must be between 0.0 and 14.0,
     * inclusive.
     */
    private double pH;

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
    private ArrayList<Guppy> guppiesInPool;

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

        setVolumeLitres(0.0);
        setName(DEFAULT_POOL_NAME);
        setTemperatureCelsius(DEFAULT_POOL_TEMP_CELSIUS);
        setPH(NEUTRAL_PH);
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

        setVolumeLitres(0.0);
        setName(DEFAULT_POOL_NAME);
        setTemperatureCelsius(DEFAULT_POOL_TEMP_CELSIUS);
        setPH(NEUTRAL_PH);
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

    /**
     * Returns the temperature of the Pool in degrees Celsius.
     * 
     * @return the temperature of the Pool in degrees Celsius
     */
    public double getTemperatureCelsius() {
        return temperatureCelsius;
    }

    /**
     * Sets the temperature of the Pool in degrees Celsius. Temperature must be
     * between 0 and 100.0, inclusive.
     * 
     * @param newTemperatureCelsius
     *            the temperature of the Pool in degrees Celsius
     */
    public void setTemperatureCelsius(double newTemperatureCelsius) {
        if (newTemperatureCelsius >= MINIMUM_POOL_TEMP_CELSIUS
                && newTemperatureCelsius <= MAXIMUM_POOL_TEMP_CELSIUS) {
            temperatureCelsius = newTemperatureCelsius;
            for (Stream stream : streamsFrom) {
                stream.setTemperatureCelsius(newTemperatureCelsius);
            }
        }
    }

    /**
     * Returns the pH level of the Pool.
     * 
     * @return the pH level of the Pool
     */
    public double getPH() {
        return pH;
    }

    /**
     * Sets the pH level of the Pool. The pH level must be between 0 and 14.0,
     * inclusive. Also sets the pH level of all Streams leading away from the
     * Pool.
     * 
     * @param newPH
     *            the pH level of the Pool
     */
    public void setPH(double newPH) {
        final double minimumPH = 0.0;
        final double maximumPH = 14.0;
        double pHToSet;

        if (newPH >= minimumPH && newPH <= maximumPH) {
            pHToSet = newPH;
        } else {
            pHToSet = NEUTRAL_PH;
        }

        pH = pHToSet;
        for (Stream stream : streamsFrom) {
            stream.setPH(pHToSet);
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
    public ArrayList<Guppy> getGuppiesInPool() {
        return guppiesInPool;
    }

    /**
     * Sets the ArrayList object holding the Guppies in the Pool.
     * 
     * @param newGuppiesInPool
     *            an ArrayList object holding the Guppies in the Pool
     */
    public void setGuppiesInPool(ArrayList<Guppy> newGuppiesInPool) {
        if (newGuppiesInPool != null) {
            guppiesInPool = newGuppiesInPool;
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
     * Changes the temperature by a specified number of degrees Celsius.
     * 
     * @param delta
     *            the number of degrees Celsius by which the temperature changes
     */
    public void changeTemperature(double delta) {
        setTemperatureCelsius(temperatureCelsius + delta);
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
        boolean result = false;
        // boolean added = false;

        // obsolete now that guppiesInPool is an ArrayList
        // if (guppy != null) {
        // int i = 0;
        // while (i < guppiesInPool.size() && !added) {
        // if (guppiesInPool.get(i) == null) {
        // guppiesInPool.add(guppy);
        // result = true;
        // added = true;
        // }
        // i++;
        // }
        // }

        if (guppy != null) {
            guppiesInPool.add(guppy);
        }
        result = true;

        return result;
    }

    /**
     * Counts and returns the number of Guppies in the Pool.
     * 
     * @return the number of Guppies in the Pool
     */
    public int getPopulation() {
        int population = 0;

        for (int i = 0; i < guppiesInPool.size(); i++) {
            if (guppiesInPool.get(i) != null) {
                population++;
            }
        }

        return population;
    }

    public int getLivingPopulation() {
        int population = 0;

        for (int i = 0; i < guppiesInPool.size(); i++) {
            if (guppiesInPool.get(i) != null
                    && guppiesInPool.get(i).getIsAlive()) {
                population++;
            }
        }

        return population;
    }

    /**
     * Loops through all the Guppies in the Pool and determines if each
     * survives. Uses the Nutrient Coefficient to determine how likely it is
     * that any given Guppy survives.
     * 
     * @return the number of newly dead Guppies
     */
    public int applyNutrientCoefficient() {

        double roll;
        int numberOfDeaths = 0;
        Guppy curGuppy;

        for (int i = 0; i < guppiesInPool.size(); i++) {
            curGuppy = guppiesInPool.get(i);
            if (curGuppy != null) {
                roll = randomNumberGenerator.nextDouble();

                if (roll > nutrientCoefficient) {
                    curGuppy.setIsAlive(false);
                    numberOfDeaths++;
                }
            }
        }

        return numberOfDeaths;

    }

    /**
     * Removes any dead Guppies from the guppiesInPool array.
     * 
     * @return the number of Guppies removed from the array
     */
    public int removeDeadGuppies() {

        Guppy currentGuppy;
        boolean isAlive;
        int removedGuppies = 0;

        // for (int i = 0; i < guppiesInPool.size(); i++) {
        // currentGuppy = guppiesInPool.get(i);
        //
        // if (currentGuppy != null) {
        // isAlive = currentGuppy.getIsAlive();
        //
        // if (!isAlive) {
        // guppiesInPool.remove(i);
        // removedGuppies++;
        // i--;
        // }
        // }
        // }
        Iterator<Guppy> iterator = guppiesInPool.iterator();
        while (iterator.hasNext()) {
            if (!iterator.next().getIsAlive()) {
                iterator.remove();
                removedGuppies++;
            }
        }

        // for (Guppy guppy : guppiesInPool) {
        // if (!guppy.getIsAlive()) {
        // guppiesInPool.remove(guppiesInPool.indexOf(guppy));
        // }
        // }

        return removedGuppies;

    }

    /**
     * Calculates and returns the total volume needed to sustain all the Guppies
     * currently in the Pool, in litres.
     * 
     * @return the total volume needed to sustain all the Guppies currently in
     *         the Pool, in litres
     */
    public double getGuppyVolumeRequirementInLitres() {

        double totalVolumeLitres = 0.0;
        double totalVolumeMillilitres = 0.0;
        final int millilitresInLitre = 1000;

        Guppy currentGuppy;
        for (int i = 0; i < guppiesInPool.size(); i++) {
            currentGuppy = guppiesInPool.get(i);

            if (currentGuppy != null && currentGuppy.getIsAlive()) {
                totalVolumeMillilitres += currentGuppy.getVolumeNeeded();
            }
        }

        totalVolumeLitres = totalVolumeMillilitres / millilitresInLitre;
        return totalVolumeLitres;

    }

    /**
     * Calculates and returns the average age in weeks of all the Guppies in the
     * Pool.
     * 
     * @return the average age in weeks of all the Guppies in the Pool
     */
    public double getAverageAgeInWeeks() {

        int guppyCount = getPopulation();
        int ageSum = 0;
        double averageAge;

        Guppy currentGuppy;

        if (guppyCount == 0) {
            return 0.0;
        } else {

            for (int i = 0; i < guppiesInPool.size(); i++) {
                currentGuppy = guppiesInPool.get(i);

                if (currentGuppy != null && currentGuppy.getIsAlive()) {
                    ageSum += currentGuppy.getAgeInWeeks();
                }
            }

            averageAge = (double) ageSum / guppyCount;
            return averageAge;
        }
    }

    /**
     * Calculates and returns the average health coefficient of all the Guppies
     * in the Pool.
     * 
     * @return the average health coefficient of all the Guppies in the Pool
     */
    public double getAverageHealthCoefficient() {

        int guppyCount = getPopulation();
        double healthCoefficientSum = 0;
        double averageHealthCoefficient;

        Guppy currentGuppy;

        if (guppyCount == 0) {
            return 0.0;
        } else {

            for (int i = 0; i < guppiesInPool.size(); i++) {
                currentGuppy = guppiesInPool.get(i);

                if (currentGuppy != null && currentGuppy.getIsAlive()) {

                    healthCoefficientSum += currentGuppy.getHealthCoefficient();
                }
            }

            averageHealthCoefficient = (double) healthCoefficientSum
                    / guppyCount;
            return averageHealthCoefficient;
        }
    }

    /**
     * Calculates and returns the percentage of living Guppies in the Pool that
     * are female.
     * 
     * @return the average health coefficient of all the Guppies in the Pool
     */
    public double getFemalePercentage() {
        int guppyCount = getPopulation();
        int femaleCount = 0;
        double femalePercentage;

        Guppy currentGuppy;

        if (getPopulation() == 0) {
            return 0.0;
        } else {
            for (int i = 0; i < guppiesInPool.size(); i++) {
                currentGuppy = guppiesInPool.get(i);

                if (currentGuppy != null && currentGuppy.getIsAlive()
                        && currentGuppy.getIsFemale()) {
                    femaleCount++;
                }
            }
            femalePercentage = (double) femaleCount / guppyCount;
            return femalePercentage;
        }
    }

    /**
     * Evaluates and returns the median age of all the living Guppies in the
     * Pool.
     * 
     * @return the median age of all the living Guppies in the Pool
     */
    public double getMedianAge() {

        int population = getPopulation();

        double medianAge;
        double median1;
        double median2;
        int index1;
        int index2;
        int[] guppyAges = new int[population];
        Guppy currentGuppy;
        int currentAge = 0;

        if (population == 0) {
            return 0.0;
        } else {

            for (int i = 0; i < guppiesInPool.size(); i++) {
                currentGuppy = guppiesInPool.get(i);

                if (currentGuppy != null && currentGuppy.getIsAlive()) {
                    guppyAges[currentAge] = currentGuppy.getAgeInWeeks();
                    currentAge++;
                }
            }

            Arrays.sort(guppyAges);

            if (population % 2 == 0) {
                index1 = population / 2;
                index2 = index1 - 1;

                median1 = guppyAges[index1];
                median2 = guppyAges[index2];

                medianAge = (median1 + median2) / 2;
            } else {
                index1 = population / 2;

                medianAge = guppyAges[index1];
            }

            return medianAge;
        }
    }

    /**
     * Invokes the spawn() method on all the Guppies in the Pool. Any baby
     * Guppies born are added to the Pool. Returns the number of baby Guppies
     * added to the Pool.
     * 
     * @return the number of baby Guppies added to the Pool
     */
    public int spawn() {
        ArrayList<Guppy> newBabies = new ArrayList<Guppy>();

        for (Guppy guppy : guppiesInPool) {
            ArrayList<Guppy> newGuppies = guppy.spawn();
            newBabies.addAll(newGuppies);
        }
        guppiesInPool.addAll(newBabies);

        return newBabies.size();
    }

    /**
     * Increments the ages of all the Guppies in the pool. Returns the number of
     * Guppies that die of old age.
     * 
     * @return the number of Guppies that have died of old age
     */
    public int incrementAges() {
        int numberOfDead = 0;

        for (Guppy guppy : guppiesInPool) {
            if (guppy != null) {
                guppy.incrementAge();
                if (!guppy.getIsAlive()) {
                    numberOfDead++;
                }
            }
        }

        return numberOfDead;
    }

    /**
     * Kills off the weakest Guppies until the volume requirement is less than
     * or equal to the Pool's volume.
     * 
     * @return the number of Guppies killed off
     */
    public int adjustForCrowding() {
        int crowdedOut = 0;

        Collections.sort(guppiesInPool);
        while (getGuppyVolumeRequirementInLitres() > volumeLitres) {
            Guppy weakestGuppy = guppiesInPool.get(crowdedOut);
            weakestGuppy.setIsAlive(false);
            crowdedOut++;
        }
        return crowdedOut;
    }

    @Override
    public String toString() {
        return "Pool [name=" + name + ", volumeLitres=" + volumeLitres
                + ", temperatureCelsius=" + temperatureCelsius + ", pH=" + pH
                + ", nutrientCoefficient=" + nutrientCoefficient
                + ", identificationNumber=" + identificationNumber
                + ", guppiesInPool=" + guppiesInPool
                + ", randomNumberGenerator=" + randomNumberGenerator + "]";
    }
}
