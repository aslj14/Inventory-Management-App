package Main;


import Model.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 *
 * This Inventory Management System program implements and extends
 * an application that manages an inventory of Apple products and the
 * associated parts that can be added to those products.
 *<p></p>
 * <b>FUTURE ENHANCEMENT: A future enhancement for this program would be to add a feature that
 * adds and saves businesses/customers who would want to buy the products and parts in bulk.</b>
 *
 * @author Ariel Johnson
 *
 */
public class Main extends Application {

    /**
     *
     * The start method will load the first scene and also create the stage for the FXML file.
     *
     * @param stage This is the stage.
     * @throws IOException From the FXMLLoader.
     *
     * */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("MainScreen.fxml"));
        Scene scene = new Scene(loader.load(), 1000, 500);
        stage.setTitle("Inventory Management System");
        stage.setScene(scene);
        stage.show();
    }

    /**
     *
     * This is the main method and the first method that will be called upon running this application.
     *
     * This method will create the parts and products and launch the application.
     *
     * @param args The string arguments.
     *
     * */
    public static void main(String[] args) {

        //Add the in-house parts
        Inventory.addPart(new InHouse(1, "AirPods", 249.99, 18, 1, 30, 1234));
        Inventory.addPart(new InHouse(2, "Watch Band", 99.99, 25, 1, 30, 5678));
        Inventory.addPart(new InHouse(3, "Lightning Cable", 18.99, 30, 1, 30, 8910));

        //Add the outsourced parts
        Inventory.addPart(new OutSourced(4, "iPad Case", 89.95, 15, 1, 30, "OtterBox"));
        Inventory.addPart(new OutSourced(5, "iPhone Case", 69.99, 20, 1, 30, "OtterBox"));
        Inventory.addPart(new OutSourced(6, "Power Adapter", 22.99, 25, 1, 30, "Anker"));

        //Add the products
        Inventory.addProduct(new Product(1000, 30, 1, 50, "Apple Watch", 549.99));
        Inventory.addProduct(new Product(1001, 20, 1, 50, "iPhone 13", 699.99));
        Inventory.addProduct(new Product(1002, 15, 1, 50, "iPhone 14", 799.99));
        Inventory.addProduct(new Product(1003, 8, 1, 50, "iPad Pro", 1099.99));

        launch();
    }

}