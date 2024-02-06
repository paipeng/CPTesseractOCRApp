package com.paipeng.cptesseractocrapp.view;

import com.paipeng.cptesseractocrapp.model.Size;
import com.paipeng.cptesseractocrapp.util.FXMLUtil;
import com.paipeng.cptesseractocrapp.util.ImageUtil;
import com.paipeng.cptesseractocrapp.util.SleepAsynchronTaskUtil;
import com.paipeng.cptesseractocrapp.view.canvas.PreviewCanvas;
import com.paipeng.cptesseractocrapp.view.canvas.ResizableCanvas;
import com.paipeng.cptesseractocrapp.view.ruler.HRuler;
import com.paipeng.cptesseractocrapp.view.ruler.VRuler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.image.BufferedImage;
import java.io.IOException;

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

    private Size size;

    private double zoomFactor;


    private PreviewCanvas previewCanvas;

    public CPPreviewPane() {
        super();
        FXMLUtil.loadFXML(this, FXML_FILE);

        size = new Size();
        size.setWidth(600);
        size.setHeight(600);

        if (previewCanvas == null) {
            previewCanvas = new PreviewCanvas(size.getWidth(), size.getHeight(), size);
        }
        canvasPane.getChildren().add(previewCanvas);
    }

    public void setPreviewImage(String filePath) {
        try {
            BufferedImage bufferedImage = ImageUtil.readBufferedImage(filePath);
            previewCanvas.drawImage(ImageUtil.convertToFxImage(bufferedImage));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void setZoom(double zoomFactor) {
        logger.trace("setZoom: " + zoomFactor);
        this.zoomFactor = zoomFactor;
        for (Node node : canvasPane.getChildren()) {
            ResizableCanvas canvas = (ResizableCanvas) node;
            if (canvas != null) {
                canvas.setScaleX(zoomFactor);
                canvas.setScaleY(zoomFactor);
                canvas.setLayoutX(size.getWidth() * (1 - zoomFactor) / 2);
                canvas.setLayoutY(size.getHeight() * (1 - zoomFactor) / 2);
            }
        }
        updateCanvasPaneSize();
    }

    private void updateCanvasPaneSize() {
        logger.trace("updateCanvasPaneSize");

        SleepAsynchronTaskUtil.startTask(200, () -> {
            Pane parentPane = (Pane) getParent();
            if (parentPane != null) {
                logger.trace("parentPane size: " + parentPane.getWidth() + "-" + parentPane.getHeight());

                if (parentPane.getHeight() > 0 && parentPane.getWidth() > 0) {
                    contentScrollPane.setPrefWidth(parentPane.getWidth());
                    contentScrollPane.setPrefHeight(parentPane.getHeight());
                    contentScrollPane.setMinWidth(parentPane.getWidth());
                    contentScrollPane.setMinHeight(parentPane.getHeight());
                } else {
                    //contentScrollPane.setPrefWidth(parentPane.getWidth());
                    contentScrollPane.setPrefHeight(700);
                    //contentScrollPane.setMinWidth(parentPane.getWidth());
                    contentScrollPane.setMinHeight(700);
                }
            } else {
                logger.error("parent pane invalid");
            }
        });
    }
}
