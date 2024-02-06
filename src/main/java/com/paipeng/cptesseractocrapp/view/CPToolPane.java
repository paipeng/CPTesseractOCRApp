package com.paipeng.cptesseractocrapp.view;

import com.paipeng.cptesseractocrapp.util.FXMLUtil;
import javafx.scene.layout.VBox;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CPToolPane extends VBox {
    private static final String FXML_FILE = "com/paipeng/cptesseractocrapp/view";
    public static Logger logger = LoggerFactory.getLogger(CPToolPane.class);

    public CPToolPane() {
        super();
        FXMLUtil.loadFXML(this, FXML_FILE);
    }

}
