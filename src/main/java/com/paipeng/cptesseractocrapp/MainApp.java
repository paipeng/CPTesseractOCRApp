package com.paipeng.cptesseractocrapp;

import com.paipeng.cptesseractocrapp.controller.HomeController;
import com.sun.jna.Platform;
import javafx.application.Application;
import javafx.application.HostServices;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class MainApp extends Application {

    public static HostServices hostServices;
    public static void main(String[] args) {
        if (Platform.isMac()) {
            //com.apple.eawt.Application.getApplication().setDockIconImage(new ImageIcon(IdLabelPrintingAppLauncher.class.getResource("/images/idcard-logo.png")).getImage());
        }
        launch(args);
    }
    @Override
    public void start(Stage stage) throws Exception {
        hostServices = getHostServices();
        try {
            if (java.awt.SplashScreen.getSplashScreen() != null && java.awt.SplashScreen.getSplashScreen().isVisible()) {
                java.awt.SplashScreen.getSplashScreen().close();
            }
            HomeController.start();
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
/*
        Parent root = FXMLLoader.load(getClass().getResource("scene.fxml"));
        
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
        
        stage.setTitle("JavaFX and Gradle");
        stage.setScene(scene);
        stage.show();


 */

    }


}
