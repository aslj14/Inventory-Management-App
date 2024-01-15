package Model;

/**
 *
 * This InHouse class will extend the in-house parts.
 *
 * @author Ariel Johnson
 *
 */
public class InHouse extends Part {

    /**
     *
     * This is the machine ID for an in-house part.
     *
     * The machine ID is an integer.
     *
     */
    private int machineID;

    /**
     *
     * This is the constructor for an InHouse object.
     *
     * @param id This is the ID for the in-house part.
     * @param name This is the name for the in-house part.
     * @param price This is the price for the in-house part.
     * @param stock This is the inventory level for the in-house part.
     * @param min This is the minimum for the in-house part.
     * @param max This is the maximum for the in-house part.
     * @param machineID This is the machine ID for the in-house part.
     *
     */
    public InHouse(int id, String name, double price, int stock, int min, int max, int machineID) {
        super(id, name, price, stock, min, max);
        this.machineID = machineID;
    }

    /**
     *
     * This is the getter for the machineID.
     *
     * @return machineID
     *
     */
    public int getMachineID() {
        return machineID;
    }

    /**
     *
     * This is the setter for the machineID.
     *
     * @param machineID This is the machineID of the in-house part.
     *
     */
    public void setMachineID(int machineID) {
        this.machineID = machineID;
    }
}
