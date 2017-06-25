package ca.bcit.comp1510.guppies;

import java.util.ArrayList;
import java.util.Random;

/**
 * A class representing a Guppy.
 * 
 * @author griffin
 * @version 1
 */
public class Guppy extends Fish {

    /**
     * The oldest possible age to be considered a young fish, in weeks.
     */
    public static final int YOUNG_FISH_WEEKS = 10;

    /**
     * The oldest possible age to be considered a mature fish, in weeks.
     */
    public static final int MATURE_FISH_WEEKS = 30;

    /**
     * The oldest possible age for a fish, in weeks.
     */
    public static final int MAXIMUM_AGE_IN_WEEKS = 50;

    /**
     * The minimum volume of water that the Guppy needs, in millilitres.
     */
    public static final double MINIMUM_WATER_VOLUME_ML = 250.0;

    /**
     * The default genus of the guppy. The first word of the scientific binomial
     * name.
     */
    public static final String DEFAULT_GENUS = "Poecilia";

    /**
     * The default species of the guppy. The second word of the scientific
     * binomial name.
     */
    public static final String DEFAULT_SPECIES = "reticulata";

    /**
     * The number of guppies that have been born since the program started
     * running.
     */
    private static int numberOfGuppiesBorn;

    /**
     * A unique identifier for the guppy.
     */
    private final int guppyID;

    /**
     * Initializes a newly born zero-generation female Guppy object with the
     * default genus, species and health coefficient.
     */
    public Guppy() {

        super();
        setBinomialName(new BinomialName(DEFAULT_GENUS, DEFAULT_SPECIES));

        guppyID = ++numberOfGuppiesBorn;

    }

    /**
     * Initializes a new living Guppy object with customizable traits.
     * 
     * @param newGenus
     *            the first half of the scientific binomial name
     * @param newSpecies
     *            the second half of the scientific binomial name
     * @param newAgeInWeeks
     *            the fish's age in weeks
     * @param newIsFemale
     *            true if the fish is female; false if male
     * @param newGenerationNumber
     *            the fish's generation number
     * @param newHealthCoefficient
     *            the fish's health coefficient
     */
    public Guppy(String newGenus, String newSpecies, int newAgeInWeeks,
            boolean newIsFemale, int newGenerationNumber,
            double newHealthCoefficient) {

        super(newGenus, newSpecies, newAgeInWeeks, newIsFemale,
                newGenerationNumber, newHealthCoefficient);

        String genus;
        String species;

        if (newGenus == null || newGenus.equals("")) {
            genus = DEFAULT_GENUS;
        } else {
            genus = newGenus;
        }
        if (newSpecies == null || newSpecies.equals("")) {
            species = DEFAULT_SPECIES;
        } else {
            species = newSpecies;
        }

        setBinomialName(new BinomialName(genus, species));

        guppyID = ++numberOfGuppiesBorn;

    }

    /**
     * Returns true if the Guppy's age is above the maximum age for a Guppy.
     * 
     * @return true if the Guppy's age is above the maximum age for a Guppy;
     *          false otherwise
     */
    public boolean hasDiedOfOldAge() {
        return getAgeInWeeks() >= MAXIMUM_AGE_IN_WEEKS;
    }

    /**
     * Sets the fish's age in weeks.
     * 
     * @param newAgeInWeeks
     *            the new age in weeks for the fish
     */
    @Override
    public void setAgeInWeeks(int newAgeInWeeks) {
        super.setAgeInWeeks(newAgeInWeeks, MAXIMUM_AGE_IN_WEEKS);
    }

    /**
     * Returns the identification number of the Guppy.
     * 
     * @return the identification number of the Guppy
     */
    public int getIdentificationNumber() {
        return guppyID;
    }

    /**
     * Returns the number of Guppies that have been born.
     * 
     * @return the number of Guppies that have been born.
     */
    public static int getNumberOfGuppiesBorn() {
        return numberOfGuppiesBorn;
    }

    /**
     * Determines and returns the volume of water needed.
     * 
     * @return the volume of water needed
     */
    public double getVolumeNeeded() {

        final double oldFishWaterRatio = 1.5;
        double volumeNeeded;
        int ageInWeeks = super.getAgeInWeeks();

        if (ageInWeeks < YOUNG_FISH_WEEKS) {
            volumeNeeded = MINIMUM_WATER_VOLUME_ML;
        } else if (ageInWeeks <= MATURE_FISH_WEEKS) {
            volumeNeeded = MINIMUM_WATER_VOLUME_ML * ageInWeeks
                    / YOUNG_FISH_WEEKS;
        } else if (ageInWeeks <= MAXIMUM_AGE_IN_WEEKS) {
            volumeNeeded = MINIMUM_WATER_VOLUME_ML * oldFishWaterRatio;
        } else {
            volumeNeeded = 0.0;
        }
        return volumeNeeded;

    }

    /**
     * Checks to see if the Guppy is female and old enough to spawn. If so,
     * there is a 1/4 chance that this Guppy spawns 0-100 fry.
     * 
     * @return an ArrayList object holding all of the baby Guppies, or null if
     *         this Guppy is male or under 10 weeks in age.
     */
    // TODO how to get it to return ArrayList<Fish>
    public ArrayList<Fish> spawn() {
        final int ageToSpawn = 10;
        final int maxFry = 100;
        ArrayList<Fish> babyGuppies = new ArrayList<Fish>();
        if (!super.getIsFemale() || super.getAgeInWeeks() < ageToSpawn) {
            return babyGuppies;
        } else {
            Random generator = new Random();
            double spawnRoll = generator.nextDouble();
            final double probabilityOfSpawn = 0.25;
            if (spawnRoll <= probabilityOfSpawn) {
                int numberOfFry = generator.nextInt(maxFry + 1);

                Guppy babyGuppy;
                double isFemaleRoll;
                double newHealthCoefficient = (1.0
                        + super.getHealth().getHealthCoefficient()) / 2.0;
                final double probabilityOfFemale = 0.5;
                for (int i = 1; i <= numberOfFry; i++) {
                    isFemaleRoll = generator.nextDouble();
                    babyGuppy = new Guppy(getBinomialName().getGenus(),
                            getBinomialName().getSpecies(), 0,
                            isFemaleRoll <= probabilityOfFemale,
                            super.getGenerationNumber() + 1,
                            newHealthCoefficient);
                    babyGuppies.add(babyGuppy);
                }
            }

            return babyGuppies;
        }
    }

    @Override
    public String toString() {
        return "Guppy [genus=" + getBinomialName().getGenus() + ", species="
                + getBinomialName().getSpecies() + ", ageInWeeks="
                + getAgeInWeeks() + ", isFemale=" + getIsFemale()
                + ", generationNumber=" + getGenerationNumber() + ", isAlive="
                + getHealth().getIsAlive() + ", healthCoefficient="
                + getHealth().getHealthCoefficient() + ", guppyID=" + guppyID
                + "]";
    }

}
