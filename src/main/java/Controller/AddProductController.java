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

import static Model.Inventory.getAllProducts;

/**
 *
 * The controller class that will supply the control and logic for the add product form/screen.
 *
 * @author Ariel Johnson
 *
 */
public class AddProductController implements Initializable {

    Stage stage;
    Parent scene;

    /**
     *
     * This is the method to auto generate an ID for the Add Product form.
     *
     */
    public static int getUniqueID() {
        int uniqueID = 1000;
        for (int i = 0; i < getAllProducts().size(); i++) {
            uniqueID++;
        }
        return uniqueID;
    }

    /**
     *
     * This is a list that contains associated parts for the product.
     *
     */
    private ObservableList<Part> associatedParts = FXCollections.observableArrayList();

    /**
     *
     * This is the ID column for the top tableview of parts.
     *
     */
    @FXML
    private TableColumn<Part, Integer> addpductaddpartidcol;

    /**
     *
     * This is the inventory level column for the top tableview of parts.
     *
     */
    @FXML
    private TableColumn<Part, Integer> addpductaddpartinvcol;

    /**
     *
     * This is the name column for the top tableview of parts.
     *
     */
    @FXML
    private TableColumn<Part, String> addpductaddpartnamecol;

    /**
     *
     * This is the price column for the top tableview of parts.
     *
     */
    @FXML
    private TableColumn<Part, Double> addpductaddpartpricecol;

    /**
     *
     * This is the top tableview of parts.
     *
     */
    @FXML
    private TableView<Part> addpductaddparttbleview;

    /**
     *
     * This is the ID column for the bottom tableview of associated parts.
     *
     */
    @FXML
    private TableColumn<Part, Integer> addpductremovepartidcol;

    /**
     *
     * This is the inventory level column for the bottom tableview of associated parts.
     *
     */
    @FXML
    private TableColumn<Part, Integer> addpductremovepartinvcol;

    /**
     *
     * This is the name column for the bottom tableview of associated parts.
     *
     */
    @FXML
    private TableColumn<Part, String> addpductremovepartnamecol;

    /**
     *
     * This is the price column for the bottom tableview of associated parts.
     *
     */
    @FXML
    private TableColumn<Part, Double> addpductremovepartpricecol;

    /**
     *
     * This is the bottom tableview of associated parts.
     *
     */
    @FXML
    private TableView<Part> addpductremoveparttbleview;

    /**
     *
     * This is the textfield for the ID in the Add Product form.
     *
     */
    @FXML
    private TextField addproductid;

    /**
     *
     * This is the textfield for the inventory level in the Add Product form.
     *
     */
    @FXML
    private TextField addproductinv;

    /**
     *
     * This is the textfield for the maximum in the Add Product form.
     *
     */
    @FXML
    private TextField addproductmax;

    /**
     *
     * This is the textfield for the minimum in the Add Product form.
     *
     */
    @FXML
    private TextField addproductmin;

    /**
     *
     * This is the textfield for the name in the Add Product form.
     *
     */
    @FXML
    private TextField addproductname;

    /**
     *
     * This is the textfield for the price in the Add Product form.
     *
     */
    @FXML
    private TextField addproductprice;

    /**
     *
     * This is the button to search for associated parts in parts tableview.
     *
     */
    @FXML
    private Button searchassoicatedpartsbutton;

    /**
     *
     * This is the textfield to enter input for parts to search and add in the Add Product form.
     *
     */
    @FXML
    private TextField searchassoicatedpartstxt;

    /**
     *
     * This will add a selected part from the top tableview to the bottom tableview
     * in the Add Product form.
     *
     * An error alert will be displayed if there is no part selected.
     *
     * @param event This is the action for the Add button in the Add Product form.
     *
     */
    @FXML
    void onActionAddSelectedPartToProduct(ActionEvent event) {
        Part selectedPart = addpductaddparttbleview.getSelectionModel().getSelectedItem();
            if (selectedPart == null) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Please select a part to add.");
                alert.showAndWait();
            } else {
                associatedParts.add(selectedPart);
                addpductremoveparttbleview.setItems(associatedParts);
            }
    }

    /**
     *
     * This will cancel any action on the Add Product screen, and load the Main Screen Controller
     * to display the main screen.
     *
     * @param event This is the action for the Cancel button.
     * @throws IOException From the FXMLLoader.
     *
     */
    @FXML
    public void onActionDisplayMainScreen(ActionEvent event) throws IOException {
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("MainScreen.fxml"));
        Scene scene = new Scene(loader.load());
        stage.setTitle("Inventory Management System");
        stage.setScene(scene);
        stage.show();
    }

    /**
     *
     * This will remove a selected associated part from the bottom tableview.
     *
     * An error alert will be displayed if there is no part selected.
     *
     * A confirmation alert will be displayed confirming if the selected part should be removed.
     *
     * @param event This is the action for the Remove Associated Part button.
     *
     */
    @FXML
    void onActionRemovePartFromProduct(ActionEvent event) {
        Part partSelected = addpductremoveparttbleview.getSelectionModel().getSelectedItem();

            if (partSelected == null) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Please select a part to remove! ");
                alert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setContentText("Are you sure you want to remove this part?");
                alert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    associatedParts.remove(partSelected);
                    addpductremoveparttbleview.setItems(associatedParts);
                }
            });
        }
    }

    /**
     *
     * This will save the new product from the Add Product form to the inventory.
     *
     * The textfields have validations that display error alerts with messages that will prevent
     * invalid values from being saved, and if there are any empty textfields.
     *
     * The main screen controller will load after the new product is saved.
     *
     * @param event This is the action for the Save button.
     * @throws IOException From FXMLLoader.
     *
     */
    @FXML
    void onActionSaveOrRemovePartToProduct(ActionEvent event) throws IOException {
        try {
                int id = getUniqueID();
                int stock = Integer.parseInt(addproductinv.getText());
                int max = Integer.parseInt(addproductmax.getText());
                int min = Integer.parseInt(addproductmin.getText());
                String name = addproductname.getText();
                double price = Double.parseDouble(addproductprice.getText());

                if (min > max) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Minimum value cannot be greater than maximum value! ");
                alert.showAndWait();
                } else if (stock > max) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Inventory value must be in between minimum and maximum! ");
                alert.showAndWait();
                } else if (associatedParts.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Please select a part to add.");
                alert.showAndWait();
                } else {
                    Product product = new Product(id, stock, min, max, name, price);
                    for (Part part : associatedParts) {
                    product.addAssociatedPart(part);
                }
                product.setId(getUniqueID());
                Inventory.addProduct(product);
            }
            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("MainScreen.fxml"));
            Scene scene = new Scene(loader.load());
            stage.setTitle("Inventory Management System");
            stage.setScene(scene);
            stage.show();

        } catch (Exception error) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Please check all fields for valid input! ");
            alert.showAndWait();
        }
    }

    /**
     *
     * This will begin a search that is based upon the values that are entered into the search
     * textfield.
     *
     * The parts can be searched by either the part ID or part name.
     *
     * If a part is found, it will be returned in the top tableview.
     *
     * If the part is not found, an alert message will be displayed.
     *
     * @param event This is the action for the Search button.
     *
     */
    @FXML
    void onActionSearchAssociatedParts(ActionEvent event) {

        String associatedParts = searchassoicatedpartstxt.getText();
        ObservableList<Part> associatedPartsToAdd = searchAssociatedParts(associatedParts);

        if(associatedPartsToAdd.size() == 0) {
            try {
                    int id = Integer.parseInt(associatedParts);
                    Part part = partID(id);
                    if (part != null)
                    associatedPartsToAdd.add(part);
            } catch (NumberFormatException e) {
                //Ignore
            }
        }

        addpductaddparttbleview.setItems(associatedPartsToAdd);
        searchassoicatedpartstxt.setText("");

        if(associatedPartsToAdd.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("No matching part! ");
            alert.showAndWait();
        }
    }

    /**
     *
     * This will search the associated parts by name.
     *
     * @param associatedParts These are the associated parts.
     * @return associatedPartIsFound Returns the associated part.
     *
     */
    private ObservableList<Part> searchAssociatedParts(String associatedParts) {

        ObservableList<Part> associatedPartIsFound = FXCollections.observableArrayList();
        ObservableList<Part> allParts = Inventory.getAllParts();

        for (Part part : allParts) {
            if (part.getName().toLowerCase().contains(associatedParts.toLowerCase())) {
                associatedPartIsFound.add(part);
            }
        }

        return associatedPartIsFound;
    }

    /**
     *
     * This will search the associated parts by ID.
     *
     * @param id This is the ID for the associated parts.
     * @return part Returns the associated part.
     * @return null Returns null if the associated part is not found.
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
     * This initializes the controller and populates the tableviews with data.
     *
     * @param url The location that is used to determine the root object's relative path.
     * @param resourceBundle These are the resources that are used to contain the root object.
     *
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //Tableview of all parts
        addpductaddparttbleview.setItems(Inventory.getAllParts());
        addpductaddpartidcol.setCellValueFactory(new PropertyValueFactory<>("id"));
        addpductaddpartinvcol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        addpductaddpartnamecol.setCellValueFactory(new PropertyValueFactory<>("name"));
        addpductaddpartpricecol.setCellValueFactory(new PropertyValueFactory<>("price"));

        ObservableList<Part> allParts = Inventory.getAllParts();
        addpductaddparttbleview.setItems(allParts);

        //Tableview of associated parts
        addpductremovepartidcol.setCellValueFactory(new PropertyValueFactory<>("id"));
        addpductremovepartinvcol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        addpductremovepartnamecol.setCellValueFactory(new PropertyValueFactory<>("name"));
        addpductremovepartpricecol.setCellValueFactory(new PropertyValueFactory<>("price"));
    }

}
