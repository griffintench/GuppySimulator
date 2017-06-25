package ca.bcit.comp1510.guppies;

import java.util.ArrayList;

/**
 * An abstract Fish class to be extended by Guppy.
 * 
 * @author griffin
 * @version 1.0
 */
@SuppressWarnings("rawtypes")
public abstract class Fish implements Comparable {

    /**
     * The number of fish that have been born since the program started running.
     */
    private static int numberOfFishBorn;

    /**
     * The scientific binomial name of the fish.
     */
    private BinomialName binomialName;

    /**
     * The fish's age, in weeks.
     */
    private int ageInWeeks;

    /**
     * True if the fish is female; false if male.
     */
    private boolean isFemale;

    /**
     * The number corresponding to the fish's generation.
     */
    private int generationNumber;

    /**
     * A health object representing the fish's health, as well as whether it is
     * alive or dead.
     */
    private Health health;

    /**
     * A unique identifier for the fish.
     */
    private final int fishID;

    /**
     * Null constructor. Sets the fish's age and generation number to 0; the
     * fish is female and alive. Increments the number of Fish that have been
     * created and sets it to fishID. Genus and species are left alone.
     */
    public Fish() {
        setAgeInWeeks(0);
        setGenerationNumber(0);
        setIsFemale(true);
        health = new Health();

        fishID = ++numberOfFishBorn;
    }

    /**
     * Constructor. Sets the fish's genus, species, age in weeks, sex,
     * generation number, and health coefficient; increments the number of fish
     * that have been created and sets it to fishID.
     * 
     * @param newGenus
     *            the genus of the fish
     * @param newSpecies
     *            the species of the fish
     * @param newAgeInWeeks
     *            the age of the fish in weeks
     * @param newIsFemale
     *            true if the fish is female; false if male
     * @param newGenerationNumber
     *            the generation number of the fish
     * @param newHealthCoefficient
     *            the health coefficient of the fish
     */
    public Fish(String newGenus, String newSpecies, int newAgeInWeeks,
            boolean newIsFemale, int newGenerationNumber,
            double newHealthCoefficient) {
        setBinomialName(new BinomialName(newGenus, newSpecies));
        setAgeInWeeks(newAgeInWeeks);
        setGenerationNumber(newGenerationNumber);
        setIsFemale(newIsFemale);
        health = new Health(true, newHealthCoefficient);

        fishID = ++numberOfFishBorn;
    }

    /**
     * Increments the fish's age, and then checks to see if the fish has died of
     * old age.
     */
    public void incrementAge() {
        ageInWeeks++;

        if (hasDiedOfOldAge()) {
            health.setIsAlive(false);
        }
    }

    /**
     * Returns true if the fish has died of old age; false otherwise.
     * 
     * @return true if the fish has died of old age; false otherwise
     */
    abstract boolean hasDiedOfOldAge();

    /**
     * Returns the scientific binomial name of the fish.
     * 
     * @return the scientific binomial name of the fish
     */
    public BinomialName getBinomialName() {
        return binomialName;
    }

    /**
     * Sets the scientific binomial name of the fish (cannot be null).
     * 
     * @param newName
     *            the scientific binomial name of the fish (cannot be null)
     */
    public void setBinomialName(BinomialName newName) {
        if (newName != null) {
            binomialName = newName;
        }
    }

    /**
     * Returns the fish's age in weeks.
     * 
     * @return the fish's age in weeks
     */
    public int getAgeInWeeks() {
        return ageInWeeks;
    }

    /**
     * Sets the fish's age in weeks.
     * 
     * @param newAgeInWeeks
     *            the fish's new age in weeks
     */
    public abstract void setAgeInWeeks(int newAgeInWeeks);

    /**
     * Sets the fish's age in weeks, as long as it is under a given maximum.
     * 
     * @param newAgeInWeeks
     *            the fish's age in weeks
     * @param maxAgeInWeeks
     *            the maximum possible age of the fish
     */
    public void setAgeInWeeks(int newAgeInWeeks, int maxAgeInWeeks) {
        if (newAgeInWeeks < 0 || newAgeInWeeks >= maxAgeInWeeks) {
            ageInWeeks = 0;
        } else {
            ageInWeeks = newAgeInWeeks;
        }
    }

    /**
     * Returns true if the fish is female; false if male.
     * 
     * @return true if the fish is female; false if male
     */
    public boolean getIsFemale() {
        return isFemale;
    }

    /**
     * Sets the sex of the fish.
     * 
     * @param newIsFemale
     *            true if the fish is female; false if male
     */
    public void setIsFemale(boolean newIsFemale) {
        isFemale = newIsFemale;
    }

    /**
     * Returns the number corresponding to the fish's generation.
     * 
     * @return the number corresponding to the fish's generation
     */
    public int getGenerationNumber() {
        return generationNumber;
    }

    /**
     * Sets the generation number for the fish.
     * 
     * @param newGenerationNumber
     *            the fish's generation number
     */
    public void setGenerationNumber(int newGenerationNumber) {
        if (newGenerationNumber < 0) {
            generationNumber = 0;
        } else {
            generationNumber = newGenerationNumber;
        }
    }

    /**
     * Returns the Fish's Health object.
     * 
     * @return the Fish's Health object
     */
    public Health getHealth() {
        return health;
    }

    /**
     * Sets the Fihs's Health object.
     * 
     * @param newHealth
     *            the Fish's Health object
     */
    public void setHealth(Health newHealth) {
        health = newHealth;
    }

    /**
     * Returns the fish's identification number.
     * 
     * @return the fish's identification number
     */
    public int getFishID() {
        return fishID;
    }

    /**
     * Returns the number of Fish that have been born.
     * 
     * @return the number of Fish that have been born.
     */
    public static int getNumberOfFishBorn() {
        return numberOfFishBorn;
    }

    /**
     * Returns the volume needed for this fish to survive.
     * 
     * @return the volume needed for this fish to survive
     */
    public abstract double getVolumeNeeded();

    /**
     * The Fish has babies; returns the babies.
     * 
     * @return the Fish's babies
     */
    public abstract ArrayList<Fish> spawn();
    // TODO figure out what to do with spawn()

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
            throw new IllegalArgumentException(); // ClassMismatchException
        }
        Fish other = (Fish) o;
        return health.compareTo(other.health);
    }

    /**
     * Returns an ID specific to the class, to add to the toString method.
     * 
     * @return an ID specific to the class, to add to the toString method
     */
    public abstract String specificIDString();

    @Override
    public String toString() {
        return "[species=" + getBinomialName() + ", ageInWeeks="
                + getAgeInWeeks() + ", isFemale=" + getIsFemale()
                + ", generationNumber=" + getGenerationNumber() + ", isAlive="
                + getHealth().getIsAlive() + ", healthCoefficient="
                + getHealth().getHealthCoefficient() + ", " + specificIDString()
                + "]";
    }
}
