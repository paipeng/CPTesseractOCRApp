module com.paipeng.cptesseractocrapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.slf4j;
    requires org.slf4j.simple;
    requires com.sun.jna;
    requires java.desktop;
    requires org.controlsfx.controls;
    //requires ch.qos.logback.classic; //only runtime dependency

    exports com.paipeng.cptesseractocrapp;
    exports com.paipeng.cptesseractocrapp.controller;
    exports com.paipeng.cptesseractocrapp.view;
    exports com.paipeng.cptesseractocrapp.view.ruler;
    opens com.paipeng.cptesseractocrapp to javafx.fxml;
    opens com.paipeng.cptesseractocrapp.controller to javafx.fxml;
    opens com.paipeng.cptesseractocrapp.view to javafx.fxml;
    opens com.paipeng.cptesseractocrapp.view.ruler to javafx.fxml;
}
