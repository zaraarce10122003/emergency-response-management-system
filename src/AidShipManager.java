import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Manages a collection of aid ships loaded from a CSV file.
 */
public class AidShipManager {
    private ArrayList<AidShip> aidShipList;
    private String dataFilePath;

    /**
     * Constructs a manager with the default data file path.
     */
    public AidShipManager() {
        this.aidShipList = new ArrayList<>();
        this.dataFilePath = "data/aid_ships.csv";
    }

    /**
     * Adds an aid ship to the list.
     *
     * @param aidShip ship to add
     */
    public void addAidShip(AidShip aidShip) {
        if (aidShip != null) {
            aidShipList.add(aidShip);
        }
    }

    /**
     * Loads aid ships from the CSV file.
     *
     * @throws IOException if the file cannot be read
     */
    public void loadAidShips() throws IOException {
        aidShipList.clear();
        Path path = Paths.get(dataFilePath);
        List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);

        for (int i = 1; i < lines.size(); i++) {
            String line = lines.get(i).trim();
            if (!line.isEmpty()) {
                addAidShip(convertLineToAidShip(line));
            }
        }
    }

    /**
     * Finds a ship by registration number.
     *
     * @param registrationNumber registration number to search for
     * @return matching ship or null
     */
    public AidShip findAidShip(String registrationNumber) {
        for (AidShip ship : aidShipList) {
            if (ship.getRegistrationNumber().equalsIgnoreCase(registrationNumber)) {
                return ship;
            }
        }
        return null;
    }

    /**
     * Checks whether a ship exists.
     *
     * @param registrationNumber registration number to search for
     * @return true if found
     */
    public boolean isAidShipExists(String registrationNumber) {
        return findAidShip(registrationNumber) != null;
    }

    /**
     * Updates an existing aid ship.
     *
     * @param updatedAidShip replacement ship object
     * @return true if updated
     * @throws IOException if the file cannot be written
     */
    public boolean updateAidShip(AidShip updatedAidShip) throws IOException {
        for (int i = 0; i < aidShipList.size(); i++) {
            if (aidShipList.get(i).getRegistrationNumber().equalsIgnoreCase(updatedAidShip.getRegistrationNumber())) {
                aidShipList.set(i, updatedAidShip);
                saveDataToFile();
                return true;
            }
        }
        return false;
    }

    /**
     * Deletes an aid ship by registration number.
     *
     * @param registrationNumber registration number of the ship to delete
     * @return true if deleted
     * @throws IOException if the file cannot be written
     */
    public boolean deleteAidShip(String registrationNumber) throws IOException {
        AidShip ship = findAidShip(registrationNumber);
        if (ship != null) {
            aidShipList.remove(ship);
            saveDataToFile();
            return true;
        }
        return false;
    }

    /**
     * Saves current aid ship data back to the CSV file.
     *
     * @throws IOException if the file cannot be written
     */
    public void saveDataToFile() throws IOException {
        List<String> lines = new ArrayList<>();
        lines.add("name,registrationNumber,tonnage,crewSize,port,aidType,aidCapacity,hasHelipad");
        for (AidShip ship : aidShipList) {
            lines.add(convertAidShipToLine(ship));
        }
        Files.write(Paths.get(dataFilePath), lines, StandardCharsets.UTF_8);
    }

    /**
     * Converts a CSV line into an AidShip object.
     *
     * @param line CSV line
     * @return parsed AidShip
     */
    public AidShip convertLineToAidShip(String line) {
        String[] values = splitCsvLine(line);
        return new AidShip(
                values[0],
                values[1],
                Double.parseDouble(values[2]),
                Integer.parseInt(values[3]),
                values[4],
                values[5],
                Integer.parseInt(values[6]),
                Boolean.parseBoolean(values[7])
        );
    }

    /**
     * Converts an AidShip object into a CSV line.
     *
     * @param aidShip ship to convert
     * @return CSV line
     */
    public String convertAidShipToLine(AidShip aidShip) {
        return escapeCsv(aidShip.getName()) + "," +
                escapeCsv(aidShip.getRegistrationNumber()) + "," +
                aidShip.getTonnage() + "," +
                aidShip.getCrewSize() + "," +
                escapeCsv(aidShip.getCurrentPort()) + "," +
                escapeCsv(aidShip.getAidType()) + "," +
                aidShip.getSuppliesOnBoard() + "," +
                aidShip.isHasHelipad();
    }

    private String[] splitCsvLine(String line) {
        String[] raw = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
        for (int i = 0; i < raw.length; i++) {
            raw[i] = unquote(raw[i].trim());
        }
        return raw;
    }

    private String escapeCsv(String value) {
        if (value.contains(",") || value.contains("\"")) {
            return '"' + value.replace("\"", "\"\"") + '"';
        }
        return value;
    }

    private String unquote(String value) {
        if (value.length() >= 2 && value.startsWith("\"") && value.endsWith("\"")) {
            return value.substring(1, value.length() - 1).replace("\"\"", "\"");
        }
        return value;
    }

    public ArrayList<AidShip> getAidShipList() {
        return aidShipList;
    }

    public void setAidShipList(ArrayList<AidShip> aidShipList) {
        this.aidShipList = aidShipList;
    }

    public String getDataFilePath() {
        return dataFilePath;
    }

    public void setDataFilePath(String dataFilePath) {
        this.dataFilePath = dataFilePath;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (AidShip ship : aidShipList) {
            builder.append(ship).append(System.lineSeparator());
        }
        return builder.toString().trim();
    }
}
