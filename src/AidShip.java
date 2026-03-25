/**
 * Represents an aid ship used for humanitarian missions.
 */
public class AidShip extends Ship implements EmergencySupport {
    private String aidType;
    private int suppliesOnBoard;
    private boolean hasHelipad;

    /**
     * Default constructor.
     */
    public AidShip() {
        super();
    }

    /**
     * Full constructor.
     *
     * @param name ship name
     * @param registrationNumber registration number
     * @param tonnage ship tonnage
     * @param crewSize crew size
     * @param currentPort current port
     * @param aidType aid type
     * @param suppliesOnBoard supplies on board
     * @param hasHelipad whether the ship has a helipad
     */
    public AidShip(String name, String registrationNumber, double tonnage, int crewSize, String currentPort,
                   String aidType, int suppliesOnBoard, boolean hasHelipad) {
        super(name, registrationNumber, tonnage, crewSize, currentPort);
        this.aidType = aidType;
        this.suppliesOnBoard = suppliesOnBoard;
        this.hasHelipad = hasHelipad;
    }

    @Override
    public void provideEmergencySupport() {
        System.out.println(getName() + " is providing " + aidType + " support.");
    }

    public String getAidType() {
        return aidType;
    }

    public void setAidType(String aidType) {
        this.aidType = aidType;
    }

    public int getSuppliesOnBoard() {
        return suppliesOnBoard;
    }

    public void setSuppliesOnBoard(int suppliesOnBoard) {
        this.suppliesOnBoard = suppliesOnBoard;
    }

    public boolean isHasHelipad() {
        return hasHelipad;
    }

    public void setHasHelipad(boolean hasHelipad) {
        this.hasHelipad = hasHelipad;
    }

    @Override
    public String toString() {
        return "AidShip{" +
                "name='" + getName() + '\'' +
                ", registrationNumber='" + getRegistrationNumber() + '\'' +
                ", tonnage=" + getTonnage() +
                ", crewSize=" + getCrewSize() +
                ", currentPort='" + getCurrentPort() + '\'' +
                ", aidType='" + aidType + '\'' +
                ", suppliesOnBoard=" + suppliesOnBoard +
                ", hasHelipad=" + hasHelipad +
                '}';
    }
}
