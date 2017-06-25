package ca.bcit.comp1510.guppies;

/**
 * A stream from one pool to another.
 * 
 * @author griffin
 * @version 1.0
 *
 */
public class Stream extends WaterBody {

    /**
     * The Pool that this Stream leads away from.
     */
    private Pool origin;

    /**
     * The Pool that this Stream leads to.
     */
    private Pool destination;

    /**
     * Constructor; sets the pH and temperature to those of the origin Pool.
     * 
     * @param origin
     *            the Pool that this Stream leads away from
     * @param destination
     *            the Pool that this Stream leads to
     */
    public Stream(Pool origin, Pool destination) {
        super(origin.getPH(), origin.getTemperatureCelsius());
        
        this.origin = origin;
        origin.addStreamFrom(this);
        this.destination = destination;
        destination.addStreamTo(this);
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

}
