package com.paipeng.cptesseractocrapp.view;

import com.paipeng.cptesseractocrapp.util.CommonUtil;
import com.paipeng.cptesseractocrapp.util.FXMLUtil;
import com.paipeng.cptesseractocrapp.util.VersionProperties;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CPStatusPane extends Pane {
    private static final String FXML_FILE = "com/paipeng/cptesseractocrapp/view";
    public static Logger logger = LoggerFactory.getLogger(CPStatusPane.class);

    @FXML
    private Label status1Label;

    public CPStatusPane() {
        super();
        FXMLUtil.loadFXML(this, FXML_FILE);
        init();
    }

    private void init() {
        status1Label.setText(CommonUtil.getString("version") + ": " + VersionProperties.getInstance().getVersion());
    }
}
