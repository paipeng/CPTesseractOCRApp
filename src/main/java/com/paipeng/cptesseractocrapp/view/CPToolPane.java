package com.paipeng.cptesseractocrapp.view;

import com.paipeng.cptesseractocrapp.util.FXMLUtil;
import com.paipeng.cptesseractocrapp.util.TesseractUtil;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import org.controlsfx.control.CheckComboBox;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

public class CPToolPane extends VBox {
    private static final String FXML_FILE = "com/paipeng/cptesseractocrapp/view";
    public static Logger logger = LoggerFactory.getLogger(CPToolPane.class);

    @FXML
    private Button closeButton;

    @FXML
    private Button previewZoomIn;

    @FXML
    private Button previewZoomOut;

    @FXML
    private Button selectFileButton;

    @FXML
    private TextField inputFileTextField;

    @FXML
    private CheckComboBox<String> languageCheckComboBox;

    @FXML
    private Button ocrDecodeButton;

    private CPToolPaneInterface cpToolPaneInterface;

    public CPToolPane() {
        super();
        FXMLUtil.loadFXML(this, FXML_FILE);
        init();
    }

    public void setCPToolPaneInterface(CPToolPaneInterface cpToolPaneInterface) {
        this.cpToolPaneInterface = cpToolPaneInterface;
    }

    private void init() {
        closeButton.setOnAction(event -> {
            logger.trace("close button");
            cpToolPaneInterface.close();
        });
        previewZoomIn.setOnAction(event -> {
            cpToolPaneInterface.zoomIn();
        });
        previewZoomOut.setOnAction(event -> {
            cpToolPaneInterface.zoomOut();
        });
        selectFileButton.setOnAction(event -> {
            String filePath = chooseFile();
            inputFileTextField.setText(filePath);
            cpToolPaneInterface.selectFile(filePath);
        });

        ocrDecodeButton.setOnAction(event -> {
            logger.trace("OCR Decode button");
            StringBuilder language = new StringBuilder();

            for (int i = 0; i < languageCheckComboBox.getCheckModel().getCheckedItems().size(); i++) {
                String item = languageCheckComboBox.getCheckModel().getItem(i);
                language.append(item.replace(".traineddata", ""));
                if (i < languageCheckComboBox.getCheckModel().getCheckedItems().size() - 1) {
                    language.append("+");
                }
            }
            cpToolPaneInterface.decode(inputFileTextField.getText(), language.toString());
        });

        languageCheckComboBox.getItems().clear();
        final ObservableList<String> items = FXCollections.observableArrayList();
        items.addAll(TesseractUtil.getOCRLanguages());
        languageCheckComboBox.getItems().addAll(items);
        languageCheckComboBox.getCheckModel().check(0);
    }

    private String chooseFile() {
        logger.trace("chooseFile");
        FileChooser fileChooser = new FileChooser();
        //Set extension filter for text files
        String description;
        description = "Image format (*.png, *.jpeg, *.jpg, *.bmp, *.tif, *.tiff)";
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(description,
                "*.png",
                "*.jpeg",
                "*.jpg",
                "*.bmp",
                "*.tif",
                "*.tiff");

        fileChooser.getExtensionFilters().add(extFilter);

        //Show save file dialog
        File file = fileChooser.showOpenDialog(null);

        if (file != null) {
            logger.trace("select file path : " + file.getAbsolutePath());
            return file.getAbsolutePath();
        } else {
            return null;
        }
    }

    public interface CPToolPaneInterface {

        void close();

        void decode(String filePath, String language);

        void selectFile(String filePath);

        void zoomIn();
        void zoomOut();
    }
}
