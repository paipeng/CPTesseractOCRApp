package com.paipeng.cptesseractocrapp.view;

import com.paipeng.cptesseractocrapp.util.FXMLUtil;
import com.paipeng.cptesseractocrapp.view.ruler.HRuler;
import com.paipeng.cptesseractocrapp.view.ruler.VRuler;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CPPreviewPane extends Pane {
    private static final String FXML_FILE = "com/paipeng/cptesseractocrapp/view";
    private static Logger logger = LoggerFactory.getLogger(CPPreviewPane.class);

    @FXML
    private ScrollPane contentScrollPane;

    @FXML
    private Pane canvasPane;
    @FXML
    private HRuler hRuler;
    @FXML
    private VRuler vRuler;

    public CPPreviewPane() {
        super();
        FXMLUtil.loadFXML(this, FXML_FILE);
    }
}
