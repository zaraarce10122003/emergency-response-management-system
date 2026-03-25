/**
 * Base class for ships.
 */
public class Ship implements Navigable {
    private String name;
    private String registrationNumber;
    private double tonnage;
    private int crewSize;
    private String currentPort;

    /**
     * Default constructor.
     */
    public Ship() {
    }

    /**
     * Full constructor.
     *
     * @param name ship name
     * @param registrationNumber registration number
     * @param tonnage ship tonnage
     * @param crewSize crew size
     * @param currentPort current port
     */
    public Ship(String name, String registrationNumber, double tonnage, int crewSize, String currentPort) {
        this.name = name;
        this.registrationNumber = registrationNumber;
        this.tonnage = tonnage;
        this.crewSize = crewSize;
        this.currentPort = currentPort;
    }

    @Override
    public void navigateTo(String destinationPort) {
        this.currentPort = destinationPort;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public double getTonnage() {
        return tonnage;
    }

    public void setTonnage(double tonnage) {
        this.tonnage = tonnage;
    }

    public int getCrewSize() {
        return crewSize;
    }

    public void setCrewSize(int crewSize) {
        this.crewSize = crewSize;
    }

    public String getCurrentPort() {
        return currentPort;
    }

    public void setCurrentPort(String currentPort) {
        this.currentPort = currentPort;
    }

    @Override
    public String toString() {
        return "Ship{" +
                "name='" + name + '\'' +
                ", registrationNumber='" + registrationNumber + '\'' +
                ", tonnage=" + tonnage +
                ", crewSize=" + crewSize +
                ", currentPort='" + currentPort + '\'' +
                '}';
    }
}
