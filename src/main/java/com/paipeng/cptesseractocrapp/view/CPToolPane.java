package com.paipeng.cptesseractocrapp.view;

import com.paipeng.cptesseractocrapp.util.FXMLUtil;
import com.paipeng.cptesseractocrapp.util.TesseractUtil;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
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
    private ChoiceBox<String> languageChoiceBox;


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
            String language = languageChoiceBox.getValue();
            cpToolPaneInterface.decode(inputFileTextField.getText(), language.replace(".traineddata", ""));
        });
        previewZoomIn.setOnAction(event -> {

        });
        previewZoomOut.setOnAction(event -> {

        });
        selectFileButton.setOnAction(event -> {
            String filePath = chooseFile();
            inputFileTextField.setText(filePath);
            cpToolPaneInterface.selectFile(filePath);
        });


        languageChoiceBox.getItems().clear();
        for(String language: TesseractUtil.getOCRLanguages()) {
            languageChoiceBox.getItems().add(language);
        }
        languageChoiceBox.getSelectionModel().select(0);
    }

    private String chooseFile() {
        logger.trace("chooseFile");
        FileChooser fileChooser = new FileChooser();
        //Set extension filter for text files
        String description;
        String extensions;
        description = "Image format (*.png)";
        extensions = "*.png";
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(description, extensions);
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
    }
}
