module CPTesseractOCRApp {
    requires javafx.controls;
    requires javafx.fxml;
    requires tess4j;
    requires org.slf4j;

    exports com.paipeng.cptesseractocrapp;
    opens com.paipeng.cptesseractocrapp to javafx.fxml;
}
