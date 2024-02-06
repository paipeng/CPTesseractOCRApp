package com.paipeng.cptesseractocrapp.controller;

import com.paipeng.cptesseractocrapp.util.CommonUtil;
import com.paipeng.cptesseractocrapp.util.ImageUtil;
import com.paipeng.cptesseractocrapp.util.TesseractUtil;
import com.paipeng.cptesseractocrapp.util.VersionProperties;
import com.paipeng.cptesseractocrapp.view.CPStatusPane;
import com.paipeng.cptesseractocrapp.view.CPToolPane;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class HomeViewController implements Initializable {
    private static final String FXML_FILE = "HomeViewController.fxml";
    private static final String CSS_FILE = "/css/HomeViewController.css";
    public static Logger logger = LoggerFactory.getLogger(HomeViewController.class);
    private static Stage stage;
    @FXML
    private CPToolPane toolPane;
    @FXML
    private CPStatusPane statusPane;
    @FXML
    private SplitPane splitPane;

    @FXML
    private ImageView previewImageView;

    @FXML
    private TextArea ocrTextArea;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        logger.trace("initialize IdLabel version: " + VersionProperties.getInstance().getVersion());
        splitPane.getDividers().get(0).positionProperty().addListener((observable, oldValue, newValue) -> {
            logger.trace("splitPane divider 0 changed: " + newValue);
            autoResize();
        });


        autoResize();
        splitPane.getDividers().get(0).setPosition(0.51);


        toolPane.setCPToolPaneInterface(new CPToolPane.CPToolPaneInterface() {
            @Override
            public void close() {
                Platform.exit();
                System.exit(0);
            }

            @Override
            public void decode(String filePath, String language) {
                logger.trace("decode: " + filePath + " language: " + language);
                String ocrText = TesseractUtil.decode(filePath, language);
                logger.trace("ocrText: " + ocrText);
                ocrTextArea.setText(ocrText);
            }

            @Override
            public void selectFile(String filePath) {
                logger.trace("selectFile: " + filePath);
                if (previewImageView != null) {
                    logger.trace("previewImageView valid");
                    try {
                        BufferedImage bufferedImage = ImageUtil.readBufferedImage(filePath);
                        previewImageView.setImage(ImageUtil.convertToFxImage(bufferedImage));

                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                } else {
                    logger.error("previewImageView invalid");
                }
            }
        });
    }

    private void autoResize() {
        Pane parentPane = (Pane) ocrTextArea.getParent();
        if (parentPane != null) {
            logger.trace("parentPane size: " + parentPane.getWidth() + "-" + parentPane.getHeight());
            if (parentPane.getWidth() > 0 && parentPane.getHeight() > 0) {
                ocrTextArea.setPrefWidth(parentPane.getWidth());
                ocrTextArea.setPrefHeight(parentPane.getHeight());
                ocrTextArea.setMinWidth(parentPane.getWidth());
                ocrTextArea.setMinHeight(parentPane.getHeight());
            }
        }
    }

    public static void start() throws IOException {
        logger.info("start");

        ResourceBundle resources = ResourceBundle.getBundle("bundles.languages", CommonUtil.getCurrentLanguageLocale());
        Parent root = FXMLLoader.load(Objects.requireNonNull(HomeViewController.class.getResource(FXML_FILE)));
        //URL url = HomeController.class.getResource(FXML_FILE);
        //Parent root = FXMLLoader.load(Objects.requireNonNull(url), resources);
        Scene scene = new Scene(root);
        String css = Objects.requireNonNull(HomeViewController.class.getResource(CSS_FILE)).toExternalForm();
        scene.getStylesheets().add(css);
        stage = new Stage();
        stage.setTitle(resources.getString("title"));
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setResizable(true);
        //stage.getIcons().add(new Image(Objects.requireNonNull(HomeController.class.getResourceAsStream("/images/idcard-logo.png"))));
        logger.trace("stage show now");

        stage.getScene().getWindow().addEventFilter(WindowEvent.WINDOW_CLOSE_REQUEST, event -> {
            logger.info("WINDOW_CLOSE_REQUEST");
        });

        stage.show();
    }
}
