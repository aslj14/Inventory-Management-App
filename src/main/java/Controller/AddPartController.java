package Controller;

import Model.InHouse;
import Model.Inventory;
import Model.OutSourced;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static Model.Inventory.getAllParts;

/**
 *
 * The controller class that will supply the control and logic for the add part form/screen.
 *
 * @author Ariel Johnson
 *
 */
public class AddPartController implements Initializable {

    Stage stage;
    Parent scene;

    /**
     * This is the method to auto generate an ID for the Add Part form.
     */
    public static int getUniqueID() {
        int uniqueID = 1;
        for (int i = 0; i < getAllParts().size(); i++) {
            uniqueID++;
        }
        return uniqueID;
    }

    /**
     * This is the toggle group for the Add Part inhouse and outsourced radio buttons.
     */
    @FXML
    private ToggleGroup addPartINHorOSTG;

    /**
     * This is the In-House radio button for the Add Part form.
     */
    @FXML
    private RadioButton addinhpart;

    /**
     * This is the OutSourced radio button for the Add Part form.
     */
    @FXML
    private RadioButton addospart;

    /**
     * This is the textfield on the Add Part Form for the ID.
     */
    @FXML
    private TextField addpartid;

    /**
     * This is the textfield on the Add Part Form for the inventory level.
     */
    @FXML
    private TextField addpartinv;

    /**
     * This is the textfield on the Add Part Form for machine ID or company name.
     */
    @FXML
    private TextField addpartmachidorcompname;

    /**
     * This is the textfield on the Add Part Form for the maximum.
     */
    @FXML
    private TextField addpartmax;

    /**
     * This is the textfield on the Add Part Form for the minimum.
     */
    @FXML
    private TextField addpartmin;

    /**
     * This is the textfield on the Add Part Form for the name.
     */
    @FXML
    private TextField addpartname;

    /**
     * This is the textfield on the Add Part Form for the price.
     */
    @FXML
    private TextField addpartprice;

    /**
     * This is the label on the Add Part form that switches from Machine ID to Company name.
     * <p>
     * The label switch is dependent upon which radio button is selected.
     */
    @FXML
    private Label parttypelabel;

    /**
     * This sets the label name for when the In-House radio button is selected.
     *
     * @param event This is the inhouse radio button action.
     */
    @FXML
    void onActionAddINHPart(ActionEvent event) {
        this.parttypelabel.setText("Machine ID: ");
    }

    /**
     * This sets the label name for when the OutSourced radio button is selected.
     *
     * @param event This is the outsourced radio button action.
     */
    @FXML
    void onActionAddOSPart(ActionEvent event) {
        this.parttypelabel.setText("Company Name: ");
    }

    /**
     * This will load the main screen controller and display the main screen.
     *
     * @param event This is the action for the Cancel button on the Add Part form..
     * @throws IOException From the FXMLLoader.
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
     * This will save the part from the Add Part form to the inventory.
     * <p>
     * The textfields have validations that display error alerts with messages that will prevent
     * invalid values from being saved, and if there are any empty textfields.
     * <p>
     * The main screen controller will load after the new part is saved.
     *
     * @param event This is the action for the Save button.
     * @throws IOException From the FXMLLoader.
     */
    @FXML
    void onActionSaveNewPart(ActionEvent event) throws IOException {
        try {
            //User input
            int id = getUniqueID();
            int stock = Integer.parseInt(addpartinv.getText());
            int max = Integer.parseInt(addpartmax.getText());
            int min = Integer.parseInt(addpartmin.getText());
            String name = addpartname.getText();
            double price = Double.parseDouble(addpartprice.getText());

            //Add new in house part
            if (addinhpart.isSelected()) {
                if (min > max) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Minimum value cannot be greater than maximum value! ");
                    alert.showAndWait();
                } else if (stock > max) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Inventory value must be in between minimum and maximum! ");
                    alert.showAndWait();
                } else {
                    int machineID = Integer.parseInt(addpartmachidorcompname.getText());
                    InHouse inhouse = new InHouse(id, name, price, stock, min, max, machineID);
                    Inventory.getAllParts().addAll(inhouse);
                }
            } else if (addospart.isSelected()) {
                if (min > max) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Minimum value cannot be greater than maximum value! ");
                    alert.showAndWait();
                } else if (stock > max) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Inventory value must be in between minimum and maximum values! ");
                    alert.showAndWait();
                } else {
                    String companyName = addpartmachidorcompname.getText();
                    OutSourced outsourced = new OutSourced(id, name, price, stock, min, max, companyName);
                    Inventory.getAllParts().addAll(outsourced);
                }
            }
            //Load main screen
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
     * This initializes the controller.
     *
     * @param url The location that is used to determine the root object's relative path.
     * @param resourceBundle These are the resources that are used to contain the root object.
     *
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
