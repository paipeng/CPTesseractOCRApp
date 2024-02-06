package com.paipeng.cptesseractocrapp.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ResourceBundle;

public class FXMLUtil {
    public static Logger logger = LoggerFactory.getLogger(FXMLUtil.class);
    private static final String PREFIX = "/";//""/fxml/";
    public static <T extends Parent> Parent loadFXML(T component) {

        ResourceBundle resources = ResourceBundle.getBundle("bundles.languages", CommonUtil.getCurrentLanguageLocale());
        //Parent root = FXMLLoader.load(MainViewController.class.getResource(FXML_FILE), resources);

        FXMLLoader loader = new FXMLLoader();
        loader.setRoot(component);
        loader.setControllerFactory(theClass -> component);

        String fileName = PREFIX + component.getClass().getSimpleName() + ".fxml";
        try {
            loader.setResources(resources);
            return loader.load(component.getClass().getResourceAsStream(fileName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public static <T extends Parent> void loadFXML(T component, String fxmlPath) {

        ResourceBundle resources = ResourceBundle.getBundle("bundles.languages", CommonUtil.getCurrentLanguageLocale());
        //Parent root = FXMLLoader.load(MainViewController.class.getResource(FXML_FILE), resources);

        FXMLLoader loader = new FXMLLoader();
        loader.setRoot(component);
        loader.setControllerFactory(theClass -> component);

        String fileName = PREFIX + fxmlPath + "/" + component.getClass().getSimpleName() + ".fxml";
        logger.info("loadFXML: " + fileName);
        try {
            loader.setResources(resources);
            loader.load(component.getClass().getResourceAsStream(fileName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public static <T extends Parent> void loadFXML(T component, String fxmlPath, String xmlFilename) {

        ResourceBundle resources = ResourceBundle.getBundle("bundles.languages", CommonUtil.getCurrentLanguageLocale());
        //Parent root = FXMLLoader.load(MainViewController.class.getResource(FXML_FILE), resources);

        FXMLLoader loader = new FXMLLoader();
        loader.setRoot(component);
        loader.setControllerFactory(theClass -> component);

        String fileName = PREFIX + fxmlPath + "/" + xmlFilename + ".fxml";
        logger.trace("loadFXML: " + fileName);
        try {
            loader.setResources(resources);
            loader.load(component.getClass().getResourceAsStream(fileName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
