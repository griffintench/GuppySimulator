package code.model;

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
    private final Pool origin;

    /**
     * The Pool that this Stream leads to.
     */
    private final Pool destination;

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
     * Returns the destination Pool.
     * 
     * @return the destination Pool
     */
    public Pool getDestination() {
        return destination;
    }

    /**
     * Sends a Guppy to the destination pool.
     * 
     * @param guppy
     *            the Guppy to send
     */
    public void sendDownstream(Guppy guppy) {
        destination.addGuppy(guppy);
    }

}
