module CPTesseractOCRApp {
    requires javafx.controls;
    requires javafx.fxml;
    
    opens com.paipeng to javafx.fxml;
    exports com.paipeng;
}
