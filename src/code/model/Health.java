package code.model;

/**
 * Represents the health of a fish (or other animal).
 * 
 * @author griffin
 * @version 1.0
 */
public class Health implements Comparable<Health> {

    /**
     * The default health coefficient, used if a health coefficient outside the
     * acceptable range is initially assigned.
     */
    public static final double DEFAULT_HEALTH_COEFFICIENT = 0.5;

    /**
     * The minimum health coefficient.
     */
    public static final double MINIMUM_HEALTH_COEFFICIENT = 0.0;

    /**
     * The maximum health coefficient.
     */
    public static final double MAXIMUM_HEALTH_COEFFICIENT = 1.0;

    /**
     * The default maximum age in weeks.
     */
    public static final int DEFAULT_MAX_AGE_WEEKS = 10;

    /**
     * True if the animal is alive; false otherwise.
     */
    private boolean isAlive;

    /**
     * A number between 0.0 and 1.0, representing how healthy the animal is (the
     * higher, the healthier).
     */
    private double healthCoefficient;

    private int ageInWeeks;

    private int maxAgeInWeeks;

    /**
     * Default constructor. Sets the animal to alive and gives it a health
     * coefficient of 0.5.
     */
    public Health() {
        this(true, DEFAULT_HEALTH_COEFFICIENT, 0, DEFAULT_MAX_AGE_WEEKS);
    }

    /**
     * Constructor. If the health coefficient has an illegal value, sets the
     * health coefficient to 0.5.
     * 
     * @param isAlive
     *            true if the animal is alive; false otherwise
     * @param healthCoefficient
     *            a double between 0.0 and 1.0
     */
    public Health(boolean isAlive, double healthCoefficient) {
        this(isAlive, healthCoefficient, 0, DEFAULT_MAX_AGE_WEEKS);
    }

    /**
     * Constructor. Uses the default max age.
     * 
     * @param isAlive
     *            true if the animal is alive; false otherwise
     * @param healthCoefficient
     *            a double between 0.0 and 1.0
     * @param ageInWeeks
     *            a nonnegative int
     */
    public Health(boolean isAlive, double healthCoefficient, int ageInWeeks) {
        this(isAlive, healthCoefficient, ageInWeeks, DEFAULT_MAX_AGE_WEEKS);
    }

    /**
     * Constructor with every parameter.
     * 
     * @param isAlive
     *            true if the animal is alive; false otherwise
     * @param healthCoefficient
     *            a double between 0.0 and 1.0
     * @param ageInWeeks
     *            a nonnegative int, less than maxAgeInWeeks
     * @param maxAgeInWeeks
     *            a positive int
     */
    public Health(boolean isAlive, double healthCoefficient, int ageInWeeks,
            int maxAgeInWeeks) {
        setIsAlive(isAlive);
        setHealthCoefficient(healthCoefficient);
        this.maxAgeInWeeks = maxAgeInWeeks;

        this.ageInWeeks = (ageInWeeks < 0 || ageInWeeks >= maxAgeInWeeks) ? 0
                : ageInWeeks;

    }

    /**
     * Returns true if the animal is alive, false otherwise.
     * 
     * @return true if the animal is alive, false otherwise
     */
    public boolean getIsAlive() {
        return isAlive;
    }

    /**
     * Sets the alive/dead status of the animal.
     * 
     * @param newIsAlive
     *            true if the animal is alive, false otherwise
     */
    public void setIsAlive(boolean newIsAlive) {
        isAlive = newIsAlive;
    }

    /**
     * Returns the health coefficient of the animal.
     * 
     * @return the health coefficient of the animal
     */
    public double getHealthCoefficient() {
        return healthCoefficient;
    }

    /**
     * Sets the health coefficient of the animal, between 0.0 and 1.0.
     * 
     * @param newHealthCoefficient
     *            the health coefficient of the animal
     */
    public void setHealthCoefficient(double newHealthCoefficient) {
        if (newHealthCoefficient < MINIMUM_HEALTH_COEFFICIENT
                || newHealthCoefficient > MAXIMUM_HEALTH_COEFFICIENT) {
            healthCoefficient = 0.0;
        } else {
            healthCoefficient = newHealthCoefficient;
        }
    }

    /**
     * Returns the age in weeks.
     * 
     * @return the age in weeks
     */
    public int getAgeInWeeks() {
        return ageInWeeks;
    }

    /**
     * Increments the age. If the age is too large, dies.
     */
    public void incrementAge() {
        ageInWeeks++;

        if (ageInWeeks > maxAgeInWeeks) {
            setIsAlive(false);
        }
    }

    /**
     * Adds or subtracts a value to or from the animal's health coefficient.
     * Health coefficient cannot exceed a maximum. If the animal's health
     * coefficient is too small, the animal dies.
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
     * Returns true if the animal is healthy.
     * 
     * @return true if the animal is healthy
     */
    public boolean isHealthy() {
        final double goodHealthCoefficient = 0.75;
        return isAlive && healthCoefficient > goodHealthCoefficient;
    }

    /**
     * Returns true if the animal's health is at least okay.
     * 
     * @return true if the animal's health is at least okay
     */
    public boolean isOkay() {
        final double okayHealthCoefficient = 0.25;
        return isAlive && healthCoefficient > okayHealthCoefficient;
    }

    @Override
    public int compareTo(Health o) {
        if (this == o) {
            return 0;
        }
        if (o == null) {
            throw new IllegalArgumentException();
        }
        if (isAlive && !o.isAlive) {
            return 1;
        }
        if (!isAlive && o.isAlive) {
            return -1;
        }
        if (healthCoefficient > o.healthCoefficient) {
            return 1;
        }
        if (healthCoefficient < o.healthCoefficient) {
            return -1;
        }
        if ((maxAgeInWeeks - ageInWeeks) > (o.maxAgeInWeeks - o.ageInWeeks)) {
            return 1;
        }
        if ((maxAgeInWeeks - ageInWeeks) < (o.maxAgeInWeeks - o.ageInWeeks)) {
            return -1;
        }
        return 0;
    }
}
