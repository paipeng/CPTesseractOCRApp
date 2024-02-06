package com.paipeng.cptesseractocrapp.view.canvas;

import com.paipeng.cptesseractocrapp.model.Size;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;


public class PreviewCanvas extends ResizableCanvas {
    private Image image;
    public PreviewCanvas(double width, double height, Size size) {
        super(width, height, size);

        drawShadow();
    }


    @Override
    public void draw() {
        super.draw();

        drawIdPageOnCanvas(size);
    }


    public void drawImage(Image image) {
        this.image = image;
        draw();
    }
    protected void drawIdPageOnCanvas(Size size) {
        logger.trace("drawIdPageOnCanvas");
        GraphicsContext graphicContext = getGraphicsContext2D();
        if (graphicContext != null) {
            if (this.image != null) {
                graphicContext.drawImage(image, 0, 0, size.getWidth(), size.getHeight());
            }
        }
    }
}
