package Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * This Inventory class holds the inventory of parts and products.
 *
 * This class supplies constant data for the application.
 *
 * @author Ariel Johnson
 *
 */
public class Inventory {

    /**
     *
     * This is a list of all the parts in inventory.
     *
     */
    private static ObservableList<Part> allParts = FXCollections.observableArrayList();

    /**
     *
     * This is a list of all the products in inventory.
     *
     */
    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();

    /**
     *
     * This is the method that adds a new part to the inventory.
     *
     *@param newPart This is the new part object to add to the inventory.
     *
     */
    public static void addPart(Part newPart) {
        allParts.add(newPart);
    }

    /**
     *
     * This method will get a list of all the parts in the inventory.
     *
     * @return Returns a list of part objects.
     *
     */
    public static ObservableList<Part> getAllParts() {
        return allParts;
    }

    /**
     *
     * This method will add a new product to the inventory.
     *
     * @param newProduct This is the new product object to add to the inventory.
     *
     */
    public static void addProduct(Product newProduct) {
        allProducts.add(newProduct);
    }

    /**
     *
     * This method will get a list of all the products in the inventory.
     *
     * @return Returns a list of product objects.
     *
     */
    public static ObservableList<Product> getAllProducts() {
        return allProducts;
    }

    /**
     *
     * This method will search the list of parts by their ID.
     *
     * @param partID This is the part ID.
     * @return Returns the part object if the ID is found, null if the part is not found.
     *
     */
    public static Part lookupPart(int partID) {
        for (Part part : allParts) {
            if (part.getId() == partID) {
                return part;
            }
        }
        return null;
    }

    /**
     *
     * This method will search the list of products by their ID.
     *
     * @param productID This is the product ID.
     * @return Returns the product object if the ID is found, null if the product is not found.
     *
     */
    public static Product lookupProduct(int productID) {
        for (Product product : allProducts) {
            if (product.getId() == productID) {
                return product;
            }
        }
        return null;
    }

    /**
     *
     * This method will search the list of parts by the name.
     *
     * @param partName This is the part name.
     * @return Returns the part object if the name is found.
     *
     */
    public static ObservableList<Part> lookupPart(String partName) {
        ObservableList<Part> partIsFound = FXCollections.observableArrayList();
            for (Part part : allParts) {
                if (part.getName().equals(partName)) {
                    partIsFound.add(part);
                }
            }
        return partIsFound;
    }

    /**
     *
     * This method will search the list of products by the name.
     *
     * @param productName This is the product name.
     * @return Returns the product object if the name is found.
     *
     */
    public static ObservableList<Product> lookupProduct(String productName) {
        ObservableList<Product> productIsFound = FXCollections.observableArrayList();
        for (Product product : allProducts) {
            if (product.getName().equals(productName)) {
                productIsFound.add(product);
            }
        }
        return productIsFound;
    }

    /**
     *
     * This method will update the part.
     *
     * @param index This is the index of the selected part.
     * @param selectedPart This is the selected part that will be updated.
     *
     */
    public static void updatePart(int index, Part selectedPart) {
        allParts.set(index, selectedPart);
    }

    /**
     *
     * This method will update the product.
     *
     * @param index This is the index of the product that will be updated.
     * @param newProduct This is the product that will be updated to a new product.
     *
     */
    public static void updateProduct(int index, Product newProduct) {
        allProducts.set(index, newProduct);
    }

    /**
     *
     * This method will delete a part from the list of parts.
     *
     * @param selectedPart This is the selected part that will be deleted from the list of parts.
     * @return Returns a boolean that will indicate if the part was deleted.
     *
     */
    public static boolean deletePart(Part selectedPart) {
        if (allParts.contains(selectedPart)) {
            allParts.remove(selectedPart);
            return true;
        }
        else {
            return false;
        }
    }

    /**
     *
     * This method will delete a product from the list of products.
     *
     * @param selectedProduct This is the selected product that will be deleted from the list of products.
     * @return Returns a boolean that will indicate if the product was deleted.
     *
     */
    public static boolean deleteProduct(Product selectedProduct) {
        if (allProducts.contains(selectedProduct)) {
            allProducts.remove(selectedProduct);
            return true;
        }
        else {
            return false;
        }

    }

}