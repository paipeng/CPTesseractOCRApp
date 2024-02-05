module CPTesseractOCRApp {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.slf4j;
    requires com.sun.jna;
    requires java.desktop;

    exports com.paipeng.cptesseractocrapp;
    opens com.paipeng.cptesseractocrapp to javafx.fxml;
}
