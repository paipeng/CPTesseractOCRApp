package com.paipeng.cptesseractocrapp.view;

import com.paipeng.cptesseractocrapp.util.FXMLUtil;
import com.paipeng.cptesseractocrapp.util.TesseractUtil;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CPToolPane extends VBox {
    private static final String FXML_FILE = "com/paipeng/cptesseractocrapp/view";
    public static Logger logger = LoggerFactory.getLogger(CPToolPane.class);

    @FXML
    private Button closeButton;

    @FXML
    private Button previewZoomIn;

    @FXML
    private Button previewZoomOut;

    public CPToolPane() {
        super();
        FXMLUtil.loadFXML(this, FXML_FILE);
        init();
    }

    private void init() {
        closeButton.setOnAction(event -> {
            logger.trace("close button");
            String ocrText = TesseractUtil.decode("src/test/resources/images/test.png", "chi_sim");
            logger.trace("ocrText: " + ocrText);
        });
        previewZoomIn.setOnAction(event -> {

        });
        previewZoomOut.setOnAction(event -> {

        });
    }

}
