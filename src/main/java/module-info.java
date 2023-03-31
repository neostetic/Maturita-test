module cz.spsmb.demo {
    requires javafx.controls;
    requires javafx.fxml;


    opens cz.spsmb.demo to javafx.fxml;
    exports cz.spsmb.demo;
    exports cz.spsmb.demo.csv;
    opens cz.spsmb.demo.csv to javafx.fxml;
}