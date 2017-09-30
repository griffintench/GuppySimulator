package code.model;

import java.util.Random;

/**
 * A coefficient with a minimum of 0.0, a maximum of 1.0 and a default of 0.5.
 * 
 * @author griffin
 * @version 1.0
 */
public class Coefficient implements Comparable<Coefficient> {

    /**
     * A Random object for rolling purposes.
     */
    public static final Random GENERATOR = new Random();

    /**
     * The minimum possible coefficient.
     */
    public static final double MINIMUM_COEFFICIENT = 0.0;

    /**
     * The default coefficient.
     */
    public static final double DEFAULT_COEFFICIENT = 0.5;

    /**
     * The maximum possible coefficient.
     */
    public static final double MAXIMUM_COEFFICIENT = 1.0;

    private double value;

    /**
     * Zero-parameter constructor; uses the default coefficient.
     */
    public Coefficient() {
        value = DEFAULT_COEFFICIENT;
    }

    /**
     * Constructor; sets the coefficient to the given value unless it is outside
     * the allowed range; otherwise uses the default.
     * 
     * @param value
     *            the new coefficient
     */
    public Coefficient(double value) {
        this.value = (value >= MINIMUM_COEFFICIENT
                && value <= MAXIMUM_COEFFICIENT) ? value : DEFAULT_COEFFICIENT;
    }

    /**
     * Sets a new value for the coefficient. Values outside the allowed range
     * are ignored.
     * 
     * @param value
     *            the new value
     */
    public void setValue(double value) {
        if (value >= MINIMUM_COEFFICIENT && value <= MAXIMUM_COEFFICIENT) {
            this.value = value;
        }
    }

    /**
     * Changes the value of the coefficient by the given amount. Value cannot go
     * under minimum or over maximum. If the value goes under the minimum,
     * returns true.
     * 
     * @param delta
     *            the amount by which to change the coefficient
     * @return true if the value tries to go below the minimum
     */
    public boolean changeValue(double delta) {
        value += delta;
        if (value < MINIMUM_COEFFICIENT) {
            value = MINIMUM_COEFFICIENT;
            return true;
        }
        if (value > MAXIMUM_COEFFICIENT) {
            value = MAXIMUM_COEFFICIENT;
        }
        return false;
    }

    /**
     * Returns the value of the coefficient.
     * 
     * @return the value of the coefficient
     */
    public double value() {
        return value;
    }

    /**
     * Generates a random double and returns true if the double is higher than
     * the coefficient's value.
     * 
     * @return true if the double is higher than the coefficient's value, false
     *         otherwise
     */
    public boolean roll() {
        double roll = GENERATOR.nextDouble();
        return roll > value;
    }

    @Override
    public int compareTo(Coefficient other) {
        if (this == other) {
            return 0;
        }
        if (other == null) {
            throw new IllegalArgumentException();
        }
        if (this.value > other.value) {
            return 1;
        }
        return -1;
    }
}
