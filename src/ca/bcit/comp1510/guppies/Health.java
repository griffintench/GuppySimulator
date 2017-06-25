package ca.bcit.comp1510.guppies;

/**
 * Represents the health of a fish (or other animal).
 * 
 * @author griffin
 * @version 1.0
 */
@SuppressWarnings("rawtypes")
public class Health implements Comparable {

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
     * True if the animal is alive; false otherwise.
     */
    private boolean isAlive;

    /**
     * A number between 0.0 and 1.0, representing how healthy the animal is (the
     * higher, the healthier).
     */
    private double healthCoefficient;

    /**
     * Default constructor. Sets the animal to alive and gives it a health
     * coefficient of 0.5.
     */
    public Health() {
        healthCoefficient = DEFAULT_HEALTH_COEFFICIENT;
        isAlive = true;
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
        setIsAlive(isAlive);
        setHealthCoefficient(healthCoefficient);
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

    @Override
    public int compareTo(Object o) {
        if (this == o) {
            return 0;
        }
        if (o == null) {
            throw new IllegalArgumentException();
        }
        if (getClass() != o.getClass()) {
            throw new IllegalArgumentException();
        }
        Health other = (Health) o;
        if (isAlive && !other.isAlive) {
            return 1;
        }
        if (!isAlive && other.isAlive) {
            return -1;
        }
        if (healthCoefficient > other.healthCoefficient) {
            return 1;
        }
        if (healthCoefficient < other.healthCoefficient) {
            return -1;
        }
        return 0;
    }
}
