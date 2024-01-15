module myapp.inventory_management_app {
    requires javafx.controls;
    requires javafx.fxml;


    exports Controller;
    opens Controller to javafx.fxml;
    exports Main;
    opens Main to javafx.fxml;
    exports Model;
    opens Model to javafx.base;
}