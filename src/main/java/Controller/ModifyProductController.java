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
 * The controller class that will supply the control and logic for the modify product form/screen.
 *
 * @author Ariel Johnson
 *
 */
public class ModifyProductController implements Initializable {

    Stage stage;
    Parent scene;

    /**
     *
     * This is the product object that is selected in the main screen to be modified.
     *
     */
    private Product productSelected;

    /**
     *
     * This is the modified product object.
     *
     */
    private Product modifiedProduct;

    /**
     *
     * This is the integer productID.
     *
     */
    private int productID;

    /**
     *
     * This is a list that contains associated parts for the product.
     *
     */
    private ObservableList<Part> associatedParts = FXCollections.observableArrayList();

    /**
     *
     * This is the textfield for the ID in the Modify Product form.
     *
     */
    @FXML
    private TextField modifyproductid;

    /**
     *
     * This is the textfield for the inventory level in the Modify Product form.
     *
     */
    @FXML
    private TextField modifyproductinv;

    /**
     *
     * This is the textfield on the Modify Product Form for machine ID or company name.
     *
     */
    @FXML
    private TextField modifyproductmachid;

    /**
     *
     * This is the textfield for the maximum in the Modify Product form.
     *
     */
    @FXML
    private TextField modifyproductmax;

    /**
     *
     * This is the textfield for the minimum in the Modify Product form.
     *
     */
    @FXML
    private TextField modifyproductmin;

    /**
     *
     * This is the textfield for the name in the Modify Product form.
     *
     */
    @FXML
    private TextField modifyproductname;

    /**
     *
     * This is the textfield for the price in the Modify Product form.
     *
     */
    @FXML
    private TextField modifyproductprice;

    /**
     *
     * This is the ID column for the top tableview of parts.
     *
     */
    @FXML
    private TableColumn<Part, Integer> modpductaddpartidcol;

    /**
     *
     * This is the top tableview of parts.
     *
     */
    @FXML
    private TableView<Part> modpductaddparttbleview;

    /**
     *
     * This is the inventory level column for the top tableview of parts.
     *
     */
    @FXML
    private TableColumn<Part, Integer> modpductaddprtinvcol;

    /**
     *
     * This is the name column for the top tableview of parts.
     *
     */
    @FXML
    private TableColumn<Part, String> modpductaddprtnamecol;

    /**
     *
     * This is the price column for the top tableview of parts.
     *
     */
    @FXML
    private TableColumn<Part, Double> modpductaddprtpricecol;

    /**
     *
     * This is the bottom tableview of associated parts.
     *
     */
    @FXML
    private TableView<Part> modpductremoveparttbleview;

    /**
     *
     * This is the ID column for the bottom tableview of associated parts.
     *
     */
    @FXML
    private TableColumn<Part, Integer> modpductremoveprtidcol;

    /**
     *
     * This is the inventory level column for the bottom tableview of associated parts.
     *
     */
    @FXML
    private TableColumn<Part, Integer> modpductremoveprtinvcol;

    /**
     *
     * This is the name column for the bottom tableview of associated parts.
     *
     */
    @FXML
    private TableColumn<Part, String> modpductremoveprtnamecol;

    /**
     *
     * This is the price column for the bottom tableview of associated parts.
     *
     */
    @FXML
    private TableColumn<Part, Double> modpductremoveprtpricecol;

    /**
     *
     * This is the button to search for associated parts in parts tableview on the Modify Product form.
     *
     */
    @FXML
    private Button searchmodifyproductsbutton;

    /**
     *
     * This is the textfield to enter input for parts to search and add in the Modify Product form.
     *
     */
    @FXML
    private TextField searchpartstoaddtxt;

    /**
     *
     * This will add a selected part from the top tableview to the bottom tableview
     * in the Modify Product form.
     *
     * An error alert will be displayed if there is no part selected.
     *
     * @param event This is the action for the Add button in the Modify Product form.
     *
     */
    @FXML
    void onActionAddPartToModifyProduct(ActionEvent event) {

        Part partSelected = modpductaddparttbleview.getSelectionModel().getSelectedItem();

            if (partSelected == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Please select a part to add.");
            alert.showAndWait();
            } else {
            associatedParts.add(partSelected);
            modpductremoveparttbleview.setItems(associatedParts);
            }
    }

    /**
     *
     * This will cancel any action on the Modify Product form, and load the Main Screen Controller
     * to display the main screen.
     *
     * @param event This is the action for the Cancel button on the Modify Product form.
     * @throws IOException From the FXMLLoader.
     *
     */
    @FXML
    public void onActionDisplayMain(ActionEvent event) throws IOException {
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("MainScreen.fxml"));
        Scene scene = new Scene(loader.load());
        stage.setTitle("Inventory Management System");
        stage.setScene(scene);
        stage.show();
    }

    /**
     *
     * This will remove a selected associated part from the bottom tableview in the Modify Product form.
     *
     * An error alert will be displayed if there is no part selected.
     *
     * A confirmation alert will be displayed confirming if the selected part should be removed.
     *
     * @param event This is the action for the Remove Associated Part button on the Modify Product form.
     *
     */
    @FXML
    void onActionRemovePartFromModifyProduct(ActionEvent event) {

        Part partSelected = modpductremoveparttbleview.getSelectionModel().getSelectedItem();

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
                        modpductremoveparttbleview.setItems(associatedParts);
                    }
                });
            }

    }

    /**
     *
     * This is the method that will set the product that was selected in the main screen
     * into the modify product form.
     *
     * @param productSelected This is the product that is selected on the Main Screen.
     *
     */
    public void setProduct(Product productSelected) {
        this.productSelected = productSelected;
        productID = Inventory.getAllProducts().indexOf(productSelected);
        modifyproductid.setText(Integer.toString(productSelected.getId()));
        modifyproductinv.setText(Integer.toString(productSelected.getStock()));
        modifyproductname.setText(productSelected.getName());
        modifyproductmax.setText(Integer.toString(productSelected.getMax()));
        modifyproductmin.setText(Integer.toString(productSelected.getMin()));
        modifyproductprice.setText(Double.toString(productSelected.getPrice()));
        associatedParts.addAll(productSelected.getAssociatedParts());
    }

    /**
     *
     * This will save the modified product from the Modify Product form to the inventory.
     *
     * The textfields have validations that display error alerts with messages that will prevent
     * invalid values from being saved, and if there are any empty textfields.
     *
     * The main screen controller will load after the modified product is saved.
     *
     * @param event This is the action for the Save button on the Modify Product form.
     * @throws IOException From FXMLLoader.
     *
     */
    @FXML
    void onActionSaveAddOrRemove(ActionEvent event) throws IOException {
        try {
                int modProductStock = Integer.parseInt(modifyproductinv.getText());
                int modProductMin = Integer.parseInt(modifyproductmin.getText());
                int modProductMax = Integer.parseInt(modifyproductmax.getText());

                if (modProductMin > modProductMax) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Minimum value cannot be greater than maximum value! ");
                alert.showAndWait();
                } else if (modProductStock > modProductMax) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Inventory value must be in between minimum and maximum! ");
                alert.showAndWait();
                } else {
                    this.modifiedProduct = productSelected;
                    productSelected.setId(Integer.parseInt(modifyproductid.getText()));
                    productSelected.setStock(Integer.parseInt(modifyproductinv.getText()));
                    productSelected.setMax(Integer.parseInt(modifyproductmax.getText()));
                    productSelected.setMin(Integer.parseInt(modifyproductmin.getText()));
                    productSelected.setName(modifyproductname.getText());
                    productSelected.setPrice(Double.parseDouble(modifyproductprice.getText()));
                    productSelected.getAssociatedParts().clear();
                    productSelected.getAssociatedParts().addAll(associatedParts);
                    Inventory.updateProduct(productID, productSelected);

                    stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                    FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("MainScreen.fxml"));
                    Scene scene = new Scene(loader.load());
                    stage.setTitle("Inventory Management System");
                    stage.setScene(scene);
                    stage.show();
                }
        } catch (Exception error) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Please check all fields for valid input! ");
            alert.showAndWait();
            }
    }

    /**
     *
     * This method refreshes/updates the top tableview of parts.
     *
     */
    public void refreshAllPartsTableview() {
        modpductaddparttbleview.setItems(Inventory.getAllParts());
    }

    /**
     *
     * This method refreshes/updates the bottom tableview of associated parts.
     *
     */
    private void refreshAssocPartsTableview() {
        modpductremoveparttbleview.setItems(associatedParts);
    }

    /**
     *
     * This will begin a search that is based upon the values that are entered into the search
     * textfield on the Modify Product form.
     *
     * The parts can be searched by either the part ID or part name.
     *
     * If a part is found, it will be returned in the top tableview.
     *
     * If the part is not found, an alert message will be displayed.
     *
     * @param event This is the action for the Search button on the Modify Product form..
     *
     */
    @FXML
    void onActionSearchPartsToAdd(ActionEvent event) {

        String modAssociatedParts = searchpartstoaddtxt.getText();

        ObservableList<Part> modAssociatedPartsToAdd = modSearchAssociatedParts(modAssociatedParts);

        if(modAssociatedPartsToAdd.size() == 0) {
            try {
                    int id = Integer.parseInt(modAssociatedParts);
                    Part part = partID(id);
                    if (part != null)
                    modAssociatedPartsToAdd.add(part);
            } catch (NumberFormatException e) {
                //Ignore
            }
        }

        modpductaddparttbleview.setItems(modAssociatedPartsToAdd);

        searchpartstoaddtxt.setText("");

        if(modAssociatedPartsToAdd.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("No matching part! ");
            alert.showAndWait();
        }
    }

    /**
     *
     * This will search the associated parts by name.
     *
     * @param modAssociatedParts This is the search for the associated part names.
     * @return modAssociatedPartIsFound Returns the associated part that is found.
     *
     */
        private ObservableList<Part> modSearchAssociatedParts(String modAssociatedParts) {

            ObservableList<Part> modAssociatedPartIsFound = FXCollections.observableArrayList();
            ObservableList<Part> allParts = Inventory.getAllParts();

            for (Part part : allParts) {
                if (part.getName().toLowerCase().contains(modAssociatedParts.toLowerCase())) {
                modAssociatedPartIsFound.add(part);
            }
        }
        return modAssociatedPartIsFound;
    }

    /**
     *
     * This will search the associated parts by ID.
     *
     * @param id This is the search for the associated part ID's.
     * @return part Returns the associated part that is found.
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
     * This initializes the controller, populates the tableviews with data, and refresh
     * the tableviews.
     *
     * @param url The location that is used to determine the root object's relative path.
     * @param resourceBundle These are the resources that are used to contain the root object.
     *
     */
    @Override
    public void initialize (URL url, ResourceBundle resourceBundle){

        //Tableview of all parts
        modpductaddparttbleview.setItems(Inventory.getAllParts());
        modpductaddpartidcol.setCellValueFactory(new PropertyValueFactory<>("id"));
        modpductaddprtinvcol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        modpductaddprtnamecol.setCellValueFactory(new PropertyValueFactory<>("name"));
        modpductaddprtpricecol.setCellValueFactory(new PropertyValueFactory<>("price"));

        //Tableview of associated parts
        modpductremoveprtidcol.setCellValueFactory(new PropertyValueFactory<>("id"));
        modpductremoveprtinvcol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        modpductremoveprtnamecol.setCellValueFactory(new PropertyValueFactory<>("name"));
        modpductremoveprtpricecol.setCellValueFactory(new PropertyValueFactory<>("price"));

        refreshAllPartsTableview();
        refreshAssocPartsTableview();

        }
    }
