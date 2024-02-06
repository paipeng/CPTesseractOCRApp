package com.paipeng.cptesseractocrapp.view.ruler;

import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class Ruler extends Pane {
    public static Logger logger = LoggerFactory.getLogger(Ruler.class);
    private final int rulerWidth;
    private final Color rulerColor;
    private final int printDpi;
    private double scale;
    public Ruler() {
        super();
        this.setBackground(new Background(new BackgroundFill(Color.rgb(180, 180, 180, 1.0), CornerRadii.EMPTY, Insets.EMPTY)));
        //this.setBackground(Background.EMPTY);
        //this.setBackground(new Background(new BackgroundFill(IdColor.rgb(255, 255, 255), CornerRadii.EMPTY, Insets.EMPTY)));
        //String style = "-fx-background-idColor: rgba(255, 0, 0 1.0);";
        //this.setStyle(style);

        rulerWidth = 2;
        rulerColor = Color.rgb(0, 0,0 , 0.9);
        printDpi = 150;
        //logger.info("VRuler size: " + this.getWidth() + "-" + this.getHeight());
        scale = 1.0;
    }

    protected void drawRuler() {

    }

    public int getRulerWidth() {
        return rulerWidth;
    }

    public Color getRulerColor() {
        return rulerColor;
    }

    @SuppressWarnings("unused")
    public int getPrintDpi() {
        return printDpi;
    }

    protected double getPixelsPerMillimeter() {
        return (0.0393701 * scale * printDpi);
    }

    protected void addLine(Line line) {
        line.setStroke(getRulerColor());
        line.setStrokeWidth(getRulerWidth());
        this.getChildren().add(line);
    }

    protected void reDrawRuler() {
        this.getChildren().clear();
    }

    public double getScale() {
        return scale;
    }

    public void setScale(double scale) {
        this.scale = scale;
        reDrawRuler();
    }

    public void addText(Text text) {
        text.setScaleX(0.9);
        text.setScaleY(0.9);
        this.getChildren().add(text);
    }
}
