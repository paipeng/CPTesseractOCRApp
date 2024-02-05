package com.paipeng.cptesseractocrapp.controller;

import com.paipeng.cptesseractocrapp.util.CommonUtil;
import com.paipeng.cptesseractocrapp.util.VersionProperties;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
    private ImageView previewImageView;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        logger.trace("initialize IdLabel version: " + VersionProperties.getInstance().getVersion());
        if (previewImageView != null) {
            logger.trace("previewImageView valid");
            previewImageView.setImage(new Image(Objects.requireNonNull(HomeViewController.class.getResourceAsStream("/images/test1.png"))));

        } else {
            logger.error("previewImageView invalid");
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
