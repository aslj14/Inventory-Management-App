package Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * This Product class will model the products.
 *
 * The products can also have associated parts.
 *
 * @author Ariel Johnson
 *
 */
public class Product {

    /**
     *
     * These are the variables for the product.
     *
     * id for the ID, stock for the inventory level, min for minimum, and max for maximum.
     *
     * All of these variables are integers.
     *
     */
    private int id, stock, min, max;

    /**
     *
     * This is the string variable for the product name.
     *
     */
    private String name;

    /**
     *
     * This is the variable for the product price. The price is a double.
     *
     */
    private double price;

    /**
     *
     * This is a list of associated parts for the product.
     *
     */
    private ObservableList<Part> associatedParts = FXCollections.observableArrayList();

    /**
     *
     * This is the constructor for the product.
     *
     * @param id This is the ID for the product.
     * @param stock This is the inventory level for the product.
     * @param min This is the minimum for the product.
     * @param max This is the maximum for the product.
     * @param name This is the name for the product.
     * @param price This is the price for the product.
     *
     */
    public Product(int id, int stock, int min, int max, String name, double price)
    {
        this.id = id;
        this.stock = stock;
        this.min = min;
        this.max = max;
        this.name = name;
        this.price = price;
    }

    /**
     *
     * This is the getter for the ID of the product.
     *
     * @return getId This returns the ID of the product.
     *
     */
    public int getId() {
        return id;
    }

    /**
     *
     * This is the setter for the ID of the product.
     *
     * @param id This is the ID of the product.
     *
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     *
     * This is the getter for the inventory level of the product.
     *
     * @return This returns the inventory level of the product.
     *
     */
    public int getStock() {
        return stock;
    }

    /**
     *
     * This is the setter for the inventory level of the product.
     *
     * @param stock This is the inventory of the product.
     *
     */
    public void setStock(int stock) {
        this.stock = stock;
    }

    /**
     *
     * This is the getter for the minimum of the product.
     *
     * @return This returns the minimum of the product.
     *
     */
    public int getMin() {
        return min;
    }

    /**
     *
     * This is the setter for the minimum of the product.
     *
     * @param min This is the minimum of the product.
     *
     */
    public void setMin(int min) {
        this.min = min;
    }

    /**
     *
     * This is the getter for the maximum of the product.
     *
     * @return This returns the maximum of the product.
     *
     */
    public int getMax() {
        return max;
    }

    /**
     *
     * This is the setter for the maximum of the product.
     *
     * @param max This is the maximum of the product.
     *
     */
    public void setMax(int max) {
        this.max = max;
    }

    /**
     *
     * This is the getter for the name of the product.
     *
     * @return This returns the name of the product.
     *
     */
    public String getName() {
        return name;
    }

    /**
     *
     * This is the setter for the name of the product.
     *
     * @param name This is the name of the product.
     *
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * This is the getter for the price of the product.
     *
     * @return This returns the price of the product.
     *
     */
    public double getPrice() {
        return price;
    }

    /**
     *
     * This is the setter for the price of the product.
     *
     * @param price This is the price of the product.
     *
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     *
     * This method will add an associated part to the list for the product.
     *
     * @param part This is the associated part that will be added.
     *
     */
    public void addAssociatedPart(Part part) {
        associatedParts.add(part);
    }

    /**
     *
     * This method will delete an associated part from the list for the product.
     *
     * @param selectedAssociatedPart This is the selected associated part that will be deleted.
     * @return This will return a boolean that indicates if the part has been deleted from the list.
     *
     */
    public boolean deleteAssociatedPart(Part selectedAssociatedPart) {
        if (associatedParts.contains(selectedAssociatedPart)) {
            associatedParts.remove(selectedAssociatedPart);
            return true;
        }
        else {
            return false;
        }
    }

    /**
     *
     * This is the getter for the list of associated parts.
     *
     * @return A list of associated parts will be returned.
     *
     */
    public ObservableList<Part> getAssociatedParts() {
        return associatedParts;
    }

}
