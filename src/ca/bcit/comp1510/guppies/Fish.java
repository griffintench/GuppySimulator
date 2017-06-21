package ca.bcit.comp1510.guppies;

/**
 * An abstract Fish class to be extended by Guppy.
 * 
 * @author griffin
 * @version 1.0
 */
public abstract class Fish implements Comparable {

    /**
     * The minimum health coefficient.
     */
    public static final double MINIMUM_HEALTH_COEFFICIENT = 0.0;

    /**
     * The maximum health coefficient for guppies.
     */
    public static final double MAXIMUM_HEALTH_COEFFICIENT = 1.0;

    /**
     * The number of fish that have been born since the program started running.
     */
    private static int numberOfFishBorn;

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
     * True if the fish is female; false if male.
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
    private final int fishID;

    /**
     * Null constructor. Sets the fish's age and generation number to 0; the
     * fish is female and alive. Increments the number of Fish that have been
     * created and sets it to fishID. Genus, species and health coefficient are
     * left alone.
     */
    public Fish() {
        setAgeInWeeks(0);
        setGenerationNumber(0);
        setIsFemale(true);
        setIsAlive(true);

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
        setGenus(newGenus);
        setSpecies(newSpecies);
        setAgeInWeeks(newAgeInWeeks);
        setGenerationNumber(newGenerationNumber);
        setIsFemale(newIsFemale);
        setIsAlive(true);
        setHealthCoefficient(newHealthCoefficient);

        fishID = ++numberOfFishBorn;
    }

    /**
     * Increments the fish's age, and then checks to see if the fish has died of
     * old age.
     */
    public void incrementAge() {
        ageInWeeks++;

        if (hasDiedOfOldAge()) {
            isAlive = false;
        }
    }

    /**
     * Returns true if the fish has died of old age; false otherwise.
     * 
     * @return true if the fish has died of old age; false otherwise
     */
    abstract boolean hasDiedOfOldAge();

    /**
     * Returns the genus of the Fish.
     * 
     * @return the genus of the Fish
     */
    public String getGenus() {
        return genus;
    }

    /**
     * Sets the genus of the fish.
     * 
     * @param newGenus
     *            the new genus for the fish
     */
    public void setGenus(String newGenus) {
        if (newGenus != null && !newGenus.equals("")) {
            String firstLetter = newGenus.substring(0, 1);
            String restOfGenus = newGenus.substring(1, newGenus.length());

            firstLetter = firstLetter.toUpperCase();
            restOfGenus = restOfGenus.toLowerCase();

            genus = firstLetter + restOfGenus;
        }
    }

    /**
     * Returns the species of the fish.
     * 
     * @return the species of the fish
     */
    public String getSpecies() {
        return species;
    }

    /**
     * Sets the species of the fish.
     * 
     * @param newSpecies
     *            the new species for the fish
     */
    public void setSpecies(String newSpecies) {
        if (newSpecies != null && !newSpecies.trim().equals("")) {
            species = newSpecies.toLowerCase();
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
    // TODO look into another way to do this

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
     * Returns true if the fish is alive; false if dead.
     * 
     * @return true if the fish is alive; false if dead
     */
    public boolean getIsAlive() {
        return isAlive;
    }

    /**
     * Sets the living status (alive or dead) of the fish.
     * 
     * @param newIsAlive
     *            true if the fish is alive; false if dead
     */
    public void setIsAlive(boolean newIsAlive) {
        isAlive = newIsAlive;
    }

    /**
     * Returns the health coefficient of the fish.
     * 
     * @return the health coefficient of the fish
     */
    public double getHealthCoefficient() {
        return healthCoefficient;
    }

    /**
     * Sets the health coefficient of the fish.
     * 
     * @param newHealthCoefficient
     *            the health coefficient of the fish
     */
    public abstract void setHealthCoefficient(double newHealthCoefficient);
    // TODO look into better way - maybe overload in super?

    public void setHealthCoefficient(double newHealthCoefficient,
            double defaultHealthCoefficient) {
        if (newHealthCoefficient < MINIMUM_HEALTH_COEFFICIENT
                || newHealthCoefficient > MAXIMUM_HEALTH_COEFFICIENT) {
            healthCoefficient = defaultHealthCoefficient;
        } else {
            healthCoefficient = newHealthCoefficient;
        }
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

    // TODO figure out what to do with spawn()

    // TODO figure out what to do with toString()

    /**
     * Compares based on health coefficient.
     */
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
        if (healthCoefficient > other.healthCoefficient) {
            return 1;
        }
        if (healthCoefficient < other.healthCoefficient) {
            return -1;
        }
        return 0;
    }
    // TODO figure out why @Override doesn't go here
}
