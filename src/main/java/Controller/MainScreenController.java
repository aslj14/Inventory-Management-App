package Controller;

import Model.Inventory;
import Model.Part;
import Model.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 *
 * The controller class that will supply the control and logic for the main screen.
 *<p></p>
 * <b>RUNTIME ERROR: Caused by java.lang.IllegalAccessException: module javafx.base cannot access
 * class Model.Product (in module myapp.inventory_management_app) because module
 * myapp.inventory_management_app does not open Model to javafx.base.</b>
 *<p></p>
 * <b>The tableview of Products was not populating because the Model package was not opening to the
 * javafx.base jar.</b>
 *<p></p>
 * <b>FIX: I went into the module-info.java and opened the Model package to the javafx.base jar.</b>
 *
 * @author Ariel Johnson
 *
 */
public class MainScreenController implements Initializable {
    Stage stage;
    Parent scene;

    /**
     *
     * This is the part object that will be selected in the all parts tableview.
     *
     */
    private static Part partSelected;

    /**
     *
     * This is the getter for the part that will be selected in the all parts tableview.
     *
     * @return This returns the part that is selected.
     */
    public static Part getPartSelected() {
        return partSelected;
    }

    /**
     *
     * This is the setter for the part object that will be selected in the all parts tableview.
     *
     * @param thePart This is the part that is selected.
     */
    public static void setSelectedPart(Part thePart) {
        partSelected = thePart;
    }

    /**
     *
     * This is the product object that will be selected in the all products tableview.
     *
     */
    private static Product productSelected;

    /**
     *
     * This is the getter for the product that will be selected in the all products tableview.
     *
     * @return This will return the product that is selected.
     */
    public static Product getProductSelected() {
        return productSelected;
    }

    /**
     *
     * This is the setter for the product object that will be selected in the all products tableview.
     *
     * @param theProduct This is the selected product.
     */
    public static void setSelectedProduct(Product theProduct) {
        productSelected = theProduct;
    }

    /**
     *
     * This is a list that contains associated parts for the product.
     *
     */
    ObservableList<Part> associatedParts = FXCollections.observableArrayList();

    /**
     *
     * This is the ID column for the tableview of all parts.
     *
     */
    @FXML
    private TableColumn<Part, Integer> allpartsidcol;

    /**
     *
     * This is the inventory level column for the tableview of all parts.
     *
     */
    @FXML
    private TableColumn<Part, Integer> allpartsinvcol;

    /**
     *
     * This is the name column for the tableview of all parts.
     *
     */
    @FXML
    private TableColumn<Part, String> allpartsnamecol;

    /**
     *
     * This is the price column for the tableview of all parts.
     *
     */
    @FXML
    private TableColumn<Part, Double> allpartspricecol;

    /**
     *
     * This is the tableview of all parts.
     *
     */
    @FXML
    private TableView<Part> allpartstableview;

    /**
     *
     * This is the ID column for the tableview of all products.
     *
     */
    @FXML
    private TableColumn<Product, Integer> allproductsidcol;

    /**
     *
     * This is the inventory level column for the tableview of all products.
     *
     */
    @FXML
    private TableColumn<Product, Integer> allproductsinvcol;

    /**
     *
     * This is the name column for the tableview of all products.
     *
     */
    @FXML
    private TableColumn<Product, String> allproductsnamecol;

    /**
     *
     * This is the price column for the tableview of all products.
     *
     */
    @FXML
    private TableColumn<Product, Double> allproductspricecol;

    /**
     *
     * This is the tableview of all products.
     *
     */
    @FXML
    private TableView<Product> allproductstableview;

    /**
     *
     * This is the textfield for the parts search bar in the Main Screen.
     *
     */
    @FXML
    private TextField searchparts;

    /**
     *
     * This is the textfield for the products search bar in the Main Screen.
     *
     */
    @FXML
    private TextField searchproducts;

    /**
     *
     * This method will delete a selected part from the tableview.
     *
     * This method will also display an error alert if no part is selected, and a confirmation alert
     * will be displayed confirming the deletion of the part.
     *
     * @param event This is the action of the Delete button under the Parts tableview.
     *
     */
    @FXML
    void onActionDeleteSelectedPart(ActionEvent event) {

        Part partSelected = allpartstableview.getSelectionModel().getSelectedItem();

            if(partSelected == null) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Please select a part to delete! ");
                alert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setContentText("Are you sure you want to delete this part?");
                alert.showAndWait().ifPresent(response -> {
                    if (response == ButtonType.OK) {
                        Part thePart = allpartstableview.getSelectionModel().getSelectedItem();
                        Inventory.deletePart(thePart);
                        allpartstableview.setItems(Inventory.getAllParts());
                    }
                });
            }

    }

    /**
     *
     * This method will delete a selected product from the tableview.
     *
     * This method will display an error alert if no product is selected, and a confirmation alert
     * will be displayed confirming the deletion of the product. If the product has an associated part,
     * an error alert will be displayed that alerts the user the product cannot be deleted.
     *
     * @param event This is the action of the Delete button under the Products tableview.
     *
     */
    @FXML
    void onActionDeleteSelectedProduct(ActionEvent event) {

        Product productSelected = allproductstableview.getSelectionModel().getSelectedItem();

        if (productSelected == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Please select a product to delete! ");
            alert.showAndWait();
            } else {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setContentText("Are you sure you want to delete this product? ");
                    alert.showAndWait().ifPresent(response -> {
                        if (response == ButtonType.OK) {
                            ObservableList<Part> associatedParts = productSelected.getAssociatedParts();
                            if (associatedParts.size() >= 1) {
                                Alert error = new Alert(Alert.AlertType.ERROR);
                                error.setContentText("This product has an associated part. It cannot be deleted! ");
                                error.showAndWait();
                            } else {
                                Inventory.deleteProduct(productSelected);
                                allproductstableview.setItems(Inventory.getAllProducts());
                            }
                        }
                    });
                }
            }

    /**
     *
     * This will exit the program.
     *
     * @param event This is the action for the Exit button on the Main Screen.
     *
     */
    @FXML
    void onActionExit(ActionEvent event) {
        System.exit(0);

    }

    /**
     *
     * This will load the Add Part Controller and open the Add Part form/screen.
     *
     * @param event This is the action for the Add button under the Parts tableview on the Main Screen.
     * @throws IOException From the FXMLLoader.
     *
     */
    @FXML
    void onActionOpenAddPartForm(ActionEvent event) throws IOException {
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getClassLoader().getResource("AddPart.fxml"));
        stage.setTitle("Add Part Form");
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**
     *
     * This will load the Add Product Controller and open the Add Product form/screen.
     *
     * @param event This is the action for the Add button under the Products tableview on the Main Screen.
     * @throws IOException From the FXMLLoader.
     *
     */
    @FXML
    void onActionOpenAddProductForm(ActionEvent event) throws IOException {
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getClassLoader().getResource("AddProduct.fxml"));
        stage.setTitle("Add Product Form");
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**
     *
     * This will load the Modify Part Controller and open the Modify Part form/screen.
     *
     * @param event This is the action for the Modify button under the Parts tableview on the Main Screen.
     * @throws IOException From the FXMLLoader.
     *
     */
    @FXML
    void onActionOpenModifyPartForm(ActionEvent event) throws IOException {
        try {
                Part partSelected = allpartstableview.getSelectionModel().getSelectedItem();
                if(partSelected == null) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Please select a part to modify! ");
                alert.showAndWait();
                } else {
                stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("ModifyPart.fxml"));
                scene = loader.load();
                ModifyPartController controller = loader.getController();
                controller.setPart(partSelected);
                stage.setTitle("Modify Part Form");
                stage.setScene(new Scene(scene));
                stage.show();
                }
            } catch (IOException error) {
                error.printStackTrace();
            }
    }

    /**
     *
     * This will load the Modify Product Controller and open the Modify Product form/screen.
     *
     * @param event This is the action for the Modify button under the Products tableview on the Main Screen.
     * @throws IOException From the FXMLLoader.
     *
     */
    @FXML
    void onActionOpenModifyProductForm(ActionEvent event) throws IOException {
        try {
                Product productSelected = allproductstableview.getSelectionModel().getSelectedItem();
                if(productSelected == null) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Please select a product to modify!");
                alert.showAndWait();
            } else {
                stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("ModifyProduct.fxml"));
                scene = loader.load();
                ModifyProductController controller = loader.getController();
                controller.setProduct(productSelected);
                stage.setTitle("Modify Product Form");
                stage.setScene(new Scene(scene));
                stage.show();
            }
        } catch (IOException error) {
            error.printStackTrace();
        }
    }

    /**
     *
     * This will begin a search that is based upon the values that are entered into the search
     * textfield.
     *
     * The parts can be searched by either the part ID or part name.
     *
     * If a part is found, it will be returned in the Parts tableview.
     *
     * If the part is not found, an alert message will be displayed.
     *
     * @param event This is the action for the Search button above the Parts tableview.
     *
     */
    @FXML
    void onActionSearchParts(ActionEvent event) {

        String search = searchparts.getText();

        ObservableList<Part> theParts = searchByName(search);

        if (theParts.size() == 0) {
            try {
                int id = Integer.parseInt(search);
                Part part = partID(id);
                if (part != null)
                    theParts.add(part);
            } catch (NumberFormatException e) {
                //Ignore
            }
        }

        allpartstableview.setItems(theParts);

        searchparts.setText("");

        if (theParts.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("No matching part! ");
            alert.showAndWait();
        }
    }

        /**
         *
         * This will search the parts by name.
         *
         * @param partialSearch This is the search for the part names.
         * @return partIsFound Returns the part that is found.
         *
         */
        private ObservableList<Part> searchByName(String partialSearch) {
            ObservableList<Part> partIsFound = FXCollections.observableArrayList();

            ObservableList<Part> allParts = Inventory.getAllParts();

                for (Part part : allParts) {
                    if (part.getName().toLowerCase().contains(partialSearch.toLowerCase())) {
                        partIsFound.add(part);
                    }
                }

            return partIsFound;
        }

    /**
     *
     * This will search the parts by ID.
     *
     * @param id This is the search for the part ID's.
     * @return part Returns the part that is found.
     * @return null Returns null if the part is not found.
     *
     */
        private Part partID(int id) {
            ObservableList<Part> allParts = Inventory.getAllParts();

            for(int i = 0; i < allParts.size(); i++ ) {
                Part part = allParts.get(i);

                if(part.getId() == id) {
                    return part;
                }
            }

            return null;
        }

    /**
     *
     * This will begin a search that is based upon the values that are entered into the search
     * textfield.
     *
     * The products can be searched by either the product ID or product name.
     *
     * If a product is found, it will be returned in the Products tableview.
     *
     * If the product is not found, an alert message will be displayed.
     *
     * @param event This is the action for the Search button above the Products tableview.
     *
     */
    @FXML
    void onActionSearchProducts(ActionEvent event) {

        String searchProducts = searchproducts.getText();

        ObservableList<Product> theProducts = lookupProduct(searchProducts);

        if(theProducts.size() == 0) {
            try {
                int id = Integer.parseInt(searchProducts);
                Product product = productID(id);
                if (product != null)
                    theProducts.add(product);
            } catch (NumberFormatException e) {
                //Ignore
            }
        }

        allproductstableview.setItems(theProducts);

        searchproducts.setText("");

        if(theProducts.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("No matching product! ");
            alert.showAndWait();
        }
    }

    /**
     *
     * This will search the products by name.
     *
     * @param productName This is the search for the product names.
     * @return productIsFound Returns the product that is found.
     *
     */
        private ObservableList<Product> lookupProduct(String productName) {

            ObservableList<Product> productIsFound = FXCollections.observableArrayList();
            ObservableList<Product> allProducts = Inventory.getAllProducts();

            for (Product product : allProducts) {
                if (product.getName().toLowerCase().contains(productName.toLowerCase())) {
                productIsFound.add(product);
                }
            }
            return productIsFound;
        }

    /**
     *
     * This will search the products by ID.
     *
     * @param id This is the search for the product ID's.
     * @return part Returns the product that is found.
     * @return null Returns null if the product is not found.
     *
     */
        private Product productID(int id) {
            ObservableList<Product> allProducts = Inventory.getAllProducts();

            for (int i = 0; i < allProducts.size(); i++) {
                Product product = allProducts.get(i);

                if (product.getId() == id) {
                    return product;
                }
            }
            return null;
        }



    /**
     *
     * This initializes the controller and populates the tableviews with data.
     *
     * @param url The location that is used to determine the root object's relative path.
     * @param resourceBundle These are the resources that are used to contain the root object.
     *
     */
        @Override
        public void initialize(URL url, ResourceBundle resourceBundle) {

        //Tableview of all parts
        allpartstableview.setItems(Inventory.getAllParts());
        allpartsidcol.setCellValueFactory(new PropertyValueFactory<>("id"));
        allpartsinvcol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        allpartsnamecol.setCellValueFactory(new PropertyValueFactory<>("name"));
        allpartspricecol.setCellValueFactory(new PropertyValueFactory<>("price"));

        ObservableList<Part> allParts = Inventory.getAllParts();
        allpartstableview.setItems(allParts);

        //Tableview of all products
        allproductstableview.setItems(Inventory.getAllProducts());
        allproductsidcol.setCellValueFactory(new PropertyValueFactory<>("id"));
        allproductsinvcol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        allproductsnamecol.setCellValueFactory(new PropertyValueFactory<>("name"));
        allproductspricecol.setCellValueFactory(new PropertyValueFactory<>("price"));

        ObservableList<Product> allProducts = Inventory.getAllProducts();
        allproductstableview.setItems(allProducts);
    }
}
