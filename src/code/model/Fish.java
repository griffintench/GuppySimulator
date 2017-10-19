package code.model;

import java.util.ArrayList;

/**
 * An abstract Fish class to be extended by Guppy.
 * 
 * @author griffin
 * @version 1.0
 */
public abstract class Fish implements Comparable<Fish> {

    /**
     * The number of fish that have been born since the program started running.
     */
    private static int numberOfFishBorn;

    /**
     * The scientific binomial name of the fish.
     */
    private final BinomialName binomialName;

    /**
     * True if the fish is female; false if male.
     */
    private boolean isFemale;

    /**
     * The number corresponding to the fish's generation.
     */
    private final int generationNumber;

    /**
     * A health object representing the fish's health, as well as whether it is
     * alive or dead.
     */
    private final Health health;

    /**
     * A unique identifier for the fish.
     */
    private final int fishID;

    /**
     * Constructor with only genus, species and maxAge parameters. Typically
     * used by the zero-parameter constructor of a particular species of fish.
     * 
     * @param newGenus
     *            the genus of the fish
     * @param newSpecies
     *            the species of the fish
     * @param maxAgeInWeeks
     *            the maximum age of the fish (species-specific)
     */
    public Fish(String newGenus, String newSpecies, int maxAgeInWeeks) {
        this(new BinomialName(newGenus, newSpecies), 0, maxAgeInWeeks, true, 0,
                Health.DEFAULT_HEALTH_COEFFICIENT);
    }

    /**
     * Constructor. Sets the fish's binomial name, age in weeks, max age in
     * weeks, sex, generation number, and health coefficient; increments the
     * number of fish that have been created and sets it to fishID.
     * 
     * @param newBinomialName
     *            the binomial name of the fish
     * @param newAgeInWeeks
     *            the fish's age in weeks
     * @param maxAgeInWeeks
     *            the fish's max age in weeks (species-specific)
     * @param newIsFemale
     *            true if the fish is female; false if not
     * @param newGenerationNumber
     *            an int corresponding to the generation of the fish
     * @param newHealthCoefficient
     *            the health coefficient of the fish
     */
    public Fish(BinomialName newBinomialName, int newAgeInWeeks,
            int maxAgeInWeeks, boolean newIsFemale, int newGenerationNumber,
            double newHealthCoefficient) {
        binomialName = (newBinomialName == null) ? new BinomialName()
                : newBinomialName;
        generationNumber = (newGenerationNumber < 0) ? 0 : newGenerationNumber;
        isFemale = newIsFemale;
        health = new Health(true, newHealthCoefficient, newAgeInWeeks,
                maxAgeInWeeks);

        fishID = ++numberOfFishBorn;
    }

    /**
     * Increments the fish's age, and then checks to see if the fish has died of
     * old age. Returns true if the fish has died.
     * 
     * @return true if the fish has died, false otherwise
     */
    public boolean incrementAge() {
        return health.incrementAge();
    }

    /**
     * Returns the scientific binomial name of the fish.
     * 
     * @return the scientific binomial name of the fish
     */
    public BinomialName getBinomialName() {
        return binomialName;
    }

    /**
     * Returns the fish's age in weeks.
     * 
     * @return the fish's age in weeks
     */
    public int getAgeInWeeks() {
        return health.getAgeInWeeks();
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
     * Returns the health coefficient of the Fish.
     * 
     * @return the health coefficient of the Fish
     */
    public double getHealthCoefficient() {
        return health.getHealthCoefficient();
    }

    /**
     * Sets the health coefficient of the fish.
     * 
     * @param healthCoefficient
     *            the health coefficient to set
     */
    public void setHealthCoefficient(double healthCoefficient) {
        health.setHealthCoefficient(healthCoefficient);
    }

    /**
     * Returns true if the fish is alive; false otherwise.
     * 
     * @return true if the fish is alive; false otherwise
     */
    public boolean isAlive() {
        return health.getIsAlive();
    }

    /**
     * Kills the fish.
     */
    public void kill() {
        health.setIsAlive(false);
    }

    /**
     * Returns true if the fish is in good health.
     * 
     * @return true if the fish is in good health
     */
    public boolean isHealthy() {
        return health.isHealthy();
    }

    /**
     * Returns true if the fish is in at least okay health.
     * 
     * @return true if the fish is in at least okay health
     */
    public boolean isOkay() {
        return health.isOkay();
    }

    public int getHealthLevel() {
        return health.getHealthLevel();
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

    /**
     * Generates a random double; if the double is higher than the given
     * coefficient, the fish dies.
     * 
     * @param coefficient
     *            a coefficient with a value between 0 and 1
     * @return true if the fish dies
     */
    public boolean applyCoefficient(Coefficient coefficient) {
        if (coefficient.roll()) {
            kill();
            return true;
        }
        return false;
    }
    
    public boolean applyHealthCoefficient() {
        return health.applyHealthCoefficient();
    }

    /**
     * Compares to another fish based on health coefficient.
     * 
     * @param o
     *            another Fish to compare to
     * @return a negative number if this fish is less healthy, a positive number
     *         of this fish is more healthy, and zero if they're equally healthy
     */
    public int compareTo(Fish o) {
        if (this == o) {
            return 0;
        }
        if (o == null) {
            throw new IllegalArgumentException();
        }
        return health.compareTo(o.health);
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
                + isAlive() + ", healthCoefficient=" + getHealthCoefficient()
                + ", " + specificIDString() + "]";
    }
}
