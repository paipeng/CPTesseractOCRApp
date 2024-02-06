package com.paipeng.cptesseractocrapp.view.canvas;

import com.paipeng.cptesseractocrapp.model.Size;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class ResizableCanvas extends Canvas {
    protected Logger logger;
    protected Size size;
    private boolean shiftKeyPressed;
    protected boolean dragged;
    protected double zoomFactor = 1;
    protected double originalWidth;
    protected double originalHeight;

    @SuppressWarnings("unused")
    public ResizableCanvas() {
        // Redraw canvas when size changes.
        widthProperty().addListener(evt -> updateCanvasSize());
        heightProperty().addListener(evt -> updateCanvasSize());
    }

    public ResizableCanvas(double width, double height, Size size) {
        super(width, height);
        originalWidth = width;
        originalHeight = height;
        this.size = size;
        logger = LoggerFactory.getLogger(this.getClass());
    }

    protected void drawShadow() {
        DropShadow ds = new DropShadow();
        ds.setOffsetY(3.0);
        ds.setOffsetX(3.0);
        ds.setColor(Color.GRAY);

        setEffect(ds);
    }

    private void updateCanvasSize() {
        double width = getWidth();
        double height = getHeight();

        GraphicsContext gc = getGraphicsContext2D();
        gc.clearRect(0, 0, width, height);
    }

    public void draw() {
        logger.trace("draw on canvas: " + this.getClass().getSimpleName());
        GraphicsContext gc = getGraphicsContext2D();
        if (size != null) {
            gc.clearRect(0, 0, size.getWidth() * zoomFactor, size.getHeight() * zoomFactor);
        } else {
            double width = getWidth();
            double height = getHeight();
            gc.clearRect(0, 0, width, height);
        }
    }


    public void setShiftKeyPressed(boolean shiftKeyPressed) {
        this.shiftKeyPressed = shiftKeyPressed;
    }

    public boolean isShiftKeyPressed() {
        return shiftKeyPressed;
    }



    public void setZoomFactor(double zoomFactor) {
    }

    @Override
    public boolean isResizable() {
        return true;
    }

    @Override
    public double prefWidth(double height) {
        return getWidth();
    }

    @Override
    public double prefHeight(double width) {
        return getHeight();
    }
}
