package Model;

/**
 *
 * This OutSourced class will extend the outsourced parts.
 *
 * @author Ariel Johnson
 *
 */
public class OutSourced extends Part {

    /**
     *
     * This is the string variable companyName.
     *
     */
    private String companyName;

    /**
     *
     * This is the constructor for an OutSourced object.
     *
     * @param id This is the ID for the outsourced part.
     * @param name This is the name for the outsourced part.
     * @param price This is the price for the outsourced part.
     * @param stock This is the inventory level for the outsourced part.
     * @param min This is the minimum for the outsourced part.
     * @param max This is the maximum for the outsourced part.
     * @param companyName This is the machine ID for the outsourced part.
     *
     */
    public OutSourced(int id, String name, double price, int stock, int min, int max, String companyName) {
        super(id, name, price, stock, min, max);
        this.companyName = companyName;
    }

    /**
     *
     * This is the getter for the companyName.
     *
     * @return companyName
     *
     */
    public String getCompanyName() {
        return companyName;
    }

    /**
     *
     * This is the setter for the companyName variable.
     *
     * @param companyName This is the companyName of the outsourced part.
     *
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
