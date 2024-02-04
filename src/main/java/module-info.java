module CPTesseractOCRApp {
    requires javafx.controls;
    requires javafx.fxml;

    exports com.paipeng.cptesseractocrapp;
    opens com.paipeng.cptesseractocrapp to javafx.fxml;
}
