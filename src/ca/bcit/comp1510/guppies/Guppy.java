package ca.bcit.comp1510.guppies;

import java.util.ArrayList;
import java.util.Random;
// test comment
/**
 * A class representing a Guppy.
 * 
 * @author griffin
 * @version 1
 */
public class Guppy implements Comparable {

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
     * The default health coefficient for guppies.
     */
    public static final double DEFAULT_HEALTH_COEFFICIENT = 0.5;

    /**
     * The minimum health coefficient for guppies.
     */
    public static final double MINIMUM_HEALTH_COEFFICIENT = 0.0;

    /**
     * The maximum health coefficient for guppies.
     */
    public static final double MAXIMUM_HEALTH_COEFFICIENT = 1.0;

    /**
     * The number of guppies that have been born since the program started
     * running.
     */
    private static int numberOfGuppiesBorn;

    /**
     * The first word of the scientific binomial name.
     */
    private String genus;

    /**
     * The second word of the scientific binomial name.
     */
    private String species;

    /**
     * The fish's age, in weeks.
     */
    private int ageInWeeks;

    /**
     * True is the fish is female; false if male.
     */
    private boolean isFemale;

    /**
     * The number corresponding to the fish's generation.
     */
    private int generationNumber;

    /**
     * True if the fish is alive; false if dead.
     */
    private boolean isAlive;

    /**
     * A double representing the fish's health.
     */
    private double healthCoefficient;

    /**
     * A unique identifier for the fish.
     */
    private final int identificationNumber;

    /**
     * Initializes a newly born zero-generation female Guppy object with the
     * default genus, species and health coefficient.
     */
    public Guppy() {

        setAgeInWeeks(0);
        setGenerationNumber(0);
        setGenus(DEFAULT_GENUS);
        setSpecies(DEFAULT_SPECIES);
        setIsFemale(true);
        setIsAlive(true);
        setHealthCoefficient(DEFAULT_HEALTH_COEFFICIENT);

        identificationNumber = ++numberOfGuppiesBorn;

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

        setAgeInWeeks(newAgeInWeeks);
        setGenerationNumber(newGenerationNumber);
        if (newGenus == null || newGenus.equals("")) {
            genus = DEFAULT_GENUS;
        } else {
            setGenus(newGenus);
        }
        if (newSpecies == null || newSpecies.equals("")) {
            species = DEFAULT_SPECIES;
        } else {
            setSpecies(newSpecies);
        }
        setIsFemale(newIsFemale);
        setIsAlive(true);
        setHealthCoefficient(newHealthCoefficient);

        identificationNumber = ++numberOfGuppiesBorn;

    }

    /**
     * Increments the fish's age, and then checks to see if the fish has died of
     * old age.
     */
    public void incrementAge() {
        ageInWeeks++;

        if (ageInWeeks == MAXIMUM_AGE_IN_WEEKS) {
            isAlive = false;
        }
    }

    /**
     * Accessor method for genus.
     * 
     * @return the genus of the fish.
     */
    public String getGenus() {
        return genus;
    }

    /**
     * Mutator method for genus.
     * 
     * @param newGenus
     *            the new genus for the fish.
     */
    public final void setGenus(String newGenus) {
        if (newGenus != null && !newGenus.equals("")) {
            String firstLetter = newGenus.substring(0, 1);
            String restOfGenus = newGenus.substring(1, newGenus.length());

            firstLetter = firstLetter.toUpperCase();
            restOfGenus = restOfGenus.toLowerCase();

            genus = firstLetter + restOfGenus;
        }
    }

    /**
     * Accessor method for species.
     * 
     * @return the species of the fish.
     */
    public String getSpecies() {
        return species;
    }

    /**
     * Mutator method for species.
     * 
     * @param newSpecies
     *            the new species for the fish.
     */
    public void setSpecies(String newSpecies) {
        if (newSpecies != null && !newSpecies.trim().equals("")) {
            species = newSpecies.toLowerCase();
        }
    }

    /**
     * Accessor method for ageInWeeks.
     * 
     * @return the fish's age in weeks.
     */
    public int getAgeInWeeks() {
        return ageInWeeks;
    }

    /**
     * Mutator method for ageInWeeks.
     * 
     * @param newAgeInWeeks
     *            the new age in weeks for the fish.
     */
    public void setAgeInWeeks(int newAgeInWeeks) {
        if (newAgeInWeeks < 0 || newAgeInWeeks >= MAXIMUM_AGE_IN_WEEKS) {
            ageInWeeks = 0;
        } else {
            ageInWeeks = newAgeInWeeks;
        }
    }

    /**
     * Accessor method for isFemale.
     * 
     * @return true if the fish is female; false if male.
     */
    public boolean getIsFemale() {
        return isFemale;
    }

    /**
     * Mutator method for isFemale.
     * 
     * @param newIsFemale
     *            true if the fish is female; false if male.
     */
    public void setIsFemale(boolean newIsFemale) {
        isFemale = newIsFemale;
    }

    /**
     * Accessor method for generationNumber.
     * 
     * @return the number corresponding to the fish's generation.
     */
    public int getGenerationNumber() {
        return generationNumber;
    }

    /**
     * Mutator method for generationNumber.
     * 
     * @param newGenerationNumber
     *            the fish's generation number.
     */
    public void setGenerationNumber(int newGenerationNumber) {
        if (newGenerationNumber < 0) {
            generationNumber = 0;
        } else {
            generationNumber = newGenerationNumber;
        }
    }

    /**
     * Accessor method for isAlive.
     * 
     * @return true if the fish is alive; false if dead.
     */
    public boolean getIsAlive() {
        return isAlive;
    }

    /**
     * Mutator method for isAlive.
     * 
     * @param newIsAlive
     *            true if the fish is alive; false if dead.
     */
    public void setIsAlive(boolean newIsAlive) {
        isAlive = newIsAlive;
    }

    /**
     * Accessor method for healthCoefficient.
     * 
     * @return the health coefficient of the fish.
     */
    public double getHealthCoefficient() {
        return healthCoefficient;
    }

    /**
     * Mutator method for healthCoefficient.
     * 
     * @param newHealthCoefficient
     *            the health coefficient of the fish.
     */
    public void setHealthCoefficient(double newHealthCoefficient) {
        if (newHealthCoefficient < MINIMUM_HEALTH_COEFFICIENT
                || newHealthCoefficient > MAXIMUM_HEALTH_COEFFICIENT) {
            healthCoefficient = DEFAULT_HEALTH_COEFFICIENT;
        } else {
            healthCoefficient = newHealthCoefficient;
        }
    }

    /**
     * Accessor method for identificationNumber.
     * 
     * @return the fish's identification number.
     */
    public int getIdentificationNumber() {
        return identificationNumber;
    }

    /**
     * Accessor for numberOfGuppiesBorn static variable.
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
     * Adds or subtracts a value to or from the fish's health coefficient.
     * Health coefficient cannot exceed a maximum. If the fish's health
     * coefficient is too small, the fish dies.
     * 
     * @param delta
     *            the amount by which the health coefficient changes.
     */
    public void changeHealthCoefficient(double delta) {

        healthCoefficient = healthCoefficient + delta;

        if (healthCoefficient < MINIMUM_HEALTH_COEFFICIENT) {
            healthCoefficient = 0.0;
            isAlive = false;
        } else if (healthCoefficient > MAXIMUM_HEALTH_COEFFICIENT) {
            healthCoefficient = MAXIMUM_HEALTH_COEFFICIENT;
        }

    }

    /**
     * Checks to see if the Guppy is female and old enough to spawn. If so,
     * there is a 1/4 chance that this Guppy spawns 0-100 fry.
     * 
     * @return an ArrayList object holding all of the baby Guppies, or null if
     *         this Guppy is male or under 10 weeks in age.
     */
    public ArrayList<Guppy> spawn() {
        final int ageToSpawn = 10;
        final int maxFry = 100;
        ArrayList<Guppy> babyGuppies = new ArrayList<Guppy>();
        if (!isFemale || ageInWeeks < ageToSpawn) {
            return babyGuppies;
        } else {
            Random generator = new Random();
            double spawnRoll = generator.nextDouble();
            final double probabilityOfSpawn = 0.25;
            if (spawnRoll <= probabilityOfSpawn) {
                int numberOfFry = generator.nextInt(maxFry + 1);

                Guppy babyGuppy;
                double isFemaleRoll;
                double newHealthCoefficient = (1.0 + healthCoefficient) / 2.0;
                final double probabilityOfFemale = 0.5;
                for (int i = 1; i <= numberOfFry; i++) {
                    isFemaleRoll = generator.nextDouble();
                    babyGuppy = new Guppy(genus, species, 0,
                            isFemaleRoll <= probabilityOfFemale,
                            generationNumber + 1, newHealthCoefficient);
                    babyGuppies.add(babyGuppy);
                }
            }

            return babyGuppies;
        }
    }

    @Override
    public String toString() {
        return "Guppy [genus=" + genus + ", species=" + species
                + ", ageInWeeks=" + ageInWeeks + ", isFemale=" + isFemale
                + ", generationNumber=" + generationNumber + ", isAlive="
                + isAlive + ", healthCoefficient=" + healthCoefficient
                + ", identificationNumber=" + identificationNumber + "]";
    }

    /**
     * Compares based on health coefficient.
     */
    @Override
    public int compareTo(Object o) {
        if (this == o) {
            return 0;
        }
        if (o == null) {
            throw new IllegalArgumentException();
        }
        if (getClass() != o.getClass()) {
            throw new IllegalArgumentException(); //ClassMismatchException
        }
        Guppy other = (Guppy) o;
        if (healthCoefficient > other.healthCoefficient) {
            return 1;
        }
        if (healthCoefficient < other.healthCoefficient) {
            return -1;
        }
        return 0;
    }

}
