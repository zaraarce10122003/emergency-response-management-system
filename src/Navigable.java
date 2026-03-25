/**
 * Represents movement capabilities for a ship.
 */
public interface Navigable {
    /**
     * Moves the ship to a new port.
     *
     * @param destinationPort destination port name
     */
    void navigateTo(String destinationPort);
}
