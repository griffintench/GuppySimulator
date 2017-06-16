package ca.bcit.comp1510.guppies;

/**
 * A stream from one pool to another.
 * 
 * @author griffin
 * @version 1.0
 *
 */
public class Stream {

    /**
     * The Pool that this Stream leads away from.
     */
    private Pool origin;

    /**
     * The Pool that this Stream leads to.
     */
    private Pool destination;

    /**
     * The pH of this Stream; by default it is equal to the pH of the origin
     * Pool.
     */
    private double pH;

    /**
     * The temperature of this Stream by Celsius; by default it is equal to the
     * temperature of the origin Pool.
     */
    private double temperatureCelsius;

    /**
     * Constructor; sets the pH and temperature to those of the origin Pool.
     * 
     * @param origin
     *            the Pool that this Stream leads away from
     * @param destination
     *            the Pool that this Stream leads to
     */
    public Stream(Pool origin, Pool destination) {
        this.origin = origin;
        origin.addStreamFrom(this);
        this.destination = destination;
        destination.addStreamTo(this);
        this.pH = origin.getPH();
        this.temperatureCelsius = origin.getTemperatureCelsius();
    }

    /**
     * Returns the origin Pool.
     * 
     * @return the origin Pool
     */
    public Pool getOrigin() {
        return origin;
    }

    /**
     * Sets the origin Pool.
     * 
     * @param newOrigin
     *            the origin Pool
     */
    public void setOrigin(Pool newOrigin) {
        origin = newOrigin;
    }

    /**
     * Returns the destination Pool.
     * 
     * @return the destination Pool
     */
    public Pool getDestination() {
        return destination;
    }

    /**
     * Sets the destination Pool.
     * 
     * @param newDestination
     *            the destination Pool
     */
    public void setDestination(Pool newDestination) {
        destination = newDestination;
    }

    /**
     * Returns the pH level of this Stream.
     * 
     * @return the pH level of this Stream
     */
    public double getPH() {
        return pH;
    }

    /**
     * Sets the pH level of this Stream.
     * 
     * @param newPH
     *            the pH level of this Stream
     */
    public void setPH(double newPH) {
        pH = newPH;
    }

    /**
     * Returns the temperature of this Stream in degrees Celsius.
     * 
     * @return the temperature of this Stream in degrees Celsius
     */
    public double getTemperatureCelsius() {
        return temperatureCelsius;
    }

    /**
     * Sets the temperature of this Stream in degrees Celsius.
     * 
     * @param newTemp
     *            the temperature of this Stream in degrees Celsius
     */
    public void setTemperatureCelsius(double newTemp) {
        temperatureCelsius = newTemp;
    }

}
