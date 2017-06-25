package ca.bcit.comp1510.guppies;

/**
 * The temperature and pH conditions of a body of water.
 * 
 * @author griffin
 * @version 1.0
 */
public class WaterBody {
    public static final double DEFAULT_TEMP_CELSIUS = 40.0;
    public static final double MINIMUM_TEMP_CELSIUS = 0.0;
    public static final double MAXIMUM_TEMP_CELSIUS = 100.0;
    public static final double NEUTRAL_PH = 7.0;
    public static final double MINIMUM_PH = 0.0;
    public static final double MAXIMUM_PH = 14.0;

    private double temperatureCelsius;

    private double pH;

    public WaterBody() {
        temperatureCelsius = DEFAULT_TEMP_CELSIUS;
        pH = NEUTRAL_PH;
    }

    public WaterBody(double temperatureCelsius, double pH) {
        setTemperatureCelsius(DEFAULT_TEMP_CELSIUS);
        setTemperatureCelsius(temperatureCelsius);
        setPH(pH);
    }

    public double getTemperatureCelsius() {
        return temperatureCelsius;
    }

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
