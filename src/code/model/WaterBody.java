package code.model;

/**
 * The temperature and pH conditions of a body of water.
 * 
 * @author griffin
 * @version 1.0
 */
public class WaterBody {

    /**
     * The default water temperature, in degrees Celsius.
     */
    public static final double DEFAULT_TEMP_CELSIUS = 40.0;

    /**
     * The minimum temperature that water can be before it becomes ice, in
     * degrees Celsius.
     */
    public static final double MINIMUM_TEMP_CELSIUS = 0.0;

    /**
     * The maximum temperature that water can be before it boils, in degrees
     * Celsius.
     */
    public static final double MAXIMUM_TEMP_CELSIUS = 100.0;

    /**
     * The neutral pH level.
     */
    public static final double NEUTRAL_PH = 7.0;

    /**
     * The minimum pH level for a water body.
     */
    public static final double MINIMUM_PH = 0.0;

    /**
     * The maximum pH level for a water body.
     */
    public static final double MAXIMUM_PH = 14.0;

    /**
     * The temperature of the water, in degrees Celsius.
     */
    private double temperatureCelsius;

    /**
     * The pH level of the water.
     */
    private double pH;

    /**
     * Default constructor; sets the temperature and pH to their default and
     * neutral values, respectively.
     */
    public WaterBody() {
        temperatureCelsius = DEFAULT_TEMP_CELSIUS;
        pH = NEUTRAL_PH;
    }

    /**
     * Constructor; sets the temperature and pH level accordingly.
     * 
     * @param temperatureCelsius
     *            the temperature of the water
     * @param pH
     *            the pH level of the water
     */
    public WaterBody(double temperatureCelsius, double pH) {
        setTemperatureCelsius(DEFAULT_TEMP_CELSIUS);
        setTemperatureCelsius(temperatureCelsius);
        setPH(pH);
    }

    /**
     * Returns the temperature of the water, in degrees Celsius.
     * 
     * @return the temperature of the water, in degrees Celsius
     */
    public double getTemperatureCelsius() {
        return temperatureCelsius;
    }

    /**
     * Sets the temperature of the water, in degrees Celsius.
     * 
     * @param newTemperatureCelsius
     *            the new temperature of the water, in degrees Celsius
     */
    public void setTemperatureCelsius(double newTemperatureCelsius) {
        if (newTemperatureCelsius >= MINIMUM_TEMP_CELSIUS
                && newTemperatureCelsius <= MAXIMUM_TEMP_CELSIUS) {
            temperatureCelsius = newTemperatureCelsius;
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
     * Returns the pH level of the Water Body.
     * 
     * @return the pH level of the Water Body
     */
    public double getPH() {
        return pH;
    }

    /**
     * Sets the pH level of the Water Body. The pH level must be between 0 and
     * 14.0, inclusive.
     * 
     * @param newPH
     *            the pH level of the Water Body
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
    }
}
