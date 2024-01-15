package Controller;

import Model.InHouse;
import Model.Inventory;
import Model.OutSourced;
import Model.Part;
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

/**
 *
 * The controller class that will supply the control and logic for the modify part form/screen.
 *
 * @author Ariel Johnson
 *
 */
public class ModifyPartController implements Initializable {

    Stage stage;
    Parent scene;

    /**
     *
     * This is the part object that is selected in the main screen to be modified.
     *
     */
    public Part partSelected;

    /**
     *
     * This is the integer part ID.
     *
     */
    private int partID;

    /**
     *
     * This is the toggle group for the Modify Part inhouse and outsourced radio buttons.
     *
     */
    @FXML
    private ToggleGroup modPartINHorOSTG;

    /**
     *
     * This is the In-House radio button for the Modify Part form.
     *
     */
    @FXML
    private RadioButton modifyinhpart;

    /**
     *
     * This is the OutSourced radio button for the Modify Part form.
     *
     */
    @FXML
    private RadioButton modifyospart;

    /**
     *
     * This is the textfield on the Modify Part Form for the ID.
     *
     */
    @FXML
    private TextField modifypartid;

    /**
     *
     * This is the textfield on the Modify Part Form for the inventory level.
     *
     */
    @FXML
    private TextField modifypartinv;

    /**
     *
     * This is the textfield on the Modify Part Form for machine ID or company name.
     *
     */
    @FXML
    private TextField modifypartmachidorcompname;

    /**
     *
     * This is the textfield on the Modify Part Form for the maximum.
     *
     */
    @FXML
    private TextField modifypartmax;

    /**
     *
     * This is the textfield on the Modify Part Form for the minimum.
     *
     */
    @FXML
    private TextField modifypartmin;

    /**
     *
     * This is the textfield on the Modify Part Form for the name.
     *
     */
    @FXML
    private TextField modifypartname;

    /**
     *
     * This is the textfield on the Modify Part Form for the price.
     *
     */
    @FXML
    private TextField modifypartprice;

    /**
     *
     * This is the label on the Modify Part form that switches from Machine ID to Company name.
     *
     * The label switch is dependent upon which radio button is selected.
     *
     */
    @FXML
    private Label modparttypelabel;

    /**
     *
     * This will load the main screen controller and display the main screen.
     *
     * @param event This is the action for the Cancel button on the Modify Part form.
     * @throws IOException From the FXMLLoader.
     *
     */
    @FXML
    public void onActionDisplayMainForm(ActionEvent event) throws IOException {
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("MainScreen.fxml"));
        Scene scene = new Scene(loader.load());
        stage.setTitle("Inventory Management System");
        stage.setScene(scene);
        stage.show();
    }

    /**
     *
     * This sets the label name for when the In-House radio button is selected.
     *
     * @param event This is the inhouse radio button action.
     *
     */
    @FXML
    void onActionModINHPart(ActionEvent event) {
        modparttypelabel.setText("Machine ID: ");
    }

    /**
     *
     * This sets the label name for when the OutSourced radio button is selected.
     *
     * @param event This is the outsourced radio button action.
     *
     */
    @FXML
    void onActionModOSPart(ActionEvent event) {
        modparttypelabel.setText("Company Name: ");
    }

    /**
     *
     * This is the method that will set the part that was selected in the main screen
     * into the modify part form.
     *
     * The method will also take in if the selected part is an In-House or OutSourced part.
     *
     * @param partSelected This is the part that is selected on the Main Screen.
     *
     */
    public void setPart(Part partSelected) {
        this.partSelected = partSelected;
        partID = Inventory.getAllParts().indexOf(partSelected);
        modifypartid.setText(Integer.toString(partSelected.getId()));
        modifypartinv.setText(Integer.toString(partSelected.getStock()));
        modifypartname.setText(partSelected.getName());
        modifypartmax.setText(Integer.toString(partSelected.getMax()));
        modifypartmin.setText(Integer.toString(partSelected.getMin()));
        modifypartprice.setText(Double.toString(partSelected.getPrice()));

        if(partSelected instanceof InHouse) {
            InHouse inhouse = (InHouse) partSelected;
            modifyinhpart.setSelected(true);
            this.modparttypelabel.setText("Machine ID ");
            modifypartmachidorcompname.setText(Integer.toString(inhouse.getMachineID()));
        } else {
            OutSourced outsourced = (OutSourced) partSelected;
            modifyospart.setSelected(true);
            this.modparttypelabel.setText("Company Name: ");
            modifypartmachidorcompname.setText(outsourced.getCompanyName());
        }
    }

    /**
     *
     * This will save the modified part from the Modify Part form to the inventory.
     *
     * The textfields have validations that display error alerts with messages that will prevent
     * invalid values from being saved, and if there are any empty textfields.
     *
     * The main screen controller will load after the modified part is saved.
     *
     * @param event This is the action for the Save button.
     * @throws IOException From the FXMLLoader.
     *
     */
    @FXML
    void onActionSaveModifyPart(ActionEvent event) throws IOException {
        try {
                //User input
                int modStock = Integer.parseInt(modifypartinv.getText());
                int modMin = Integer.parseInt(modifypartmin.getText());
                int modMax = Integer.parseInt(modifypartmax.getText());

                if (modMin > modMax) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Minimum value cannot be greater than maximum value! ");
                    alert.showAndWait();
                } else if (modStock > modMax) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Inventory value must be in between minimum and maximum! ");
                    alert.showAndWait();
                } else {
                    int id = Integer.parseInt(modifypartid.getText());
                    String name = modifypartname.getText();
                    double price = Double.parseDouble(modifypartprice.getText());
                    int stock = Integer.parseInt(modifypartinv.getText());
                    int min = Integer.parseInt(modifypartmin.getText());
                    int max = Integer.parseInt(modifypartmax.getText());

                    if (modifyinhpart.isSelected()) {
                        int machineID = Integer.parseInt(modifypartmachidorcompname.getText());
                        InHouse inhouse = new InHouse(id, name, price, stock, min, max, machineID);
                        Inventory.updatePart(partID, inhouse);
                        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("MainScreen.fxml"));
                        Scene scene = new Scene(loader.load());
                        stage.setTitle("Inventory Management System");
                        stage.setScene(scene);
                        stage.show();
                    } else {
                            String companyName = modifypartmachidorcompname.getText();
                            OutSourced outsourced = new OutSourced(id, name, price, stock, min, max, companyName);
                            Inventory.updatePart(partID, outsourced);
                            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("MainScreen.fxml"));
                            Scene scene = new Scene(loader.load());
                            stage.setTitle("Inventory Management System");
                            stage.setScene(scene);
                            stage.show();
                            }
                    }
            //Catch any exceptions and display an error alert
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
    public void initialize (URL url, ResourceBundle resourceBundle){

        }
    }


