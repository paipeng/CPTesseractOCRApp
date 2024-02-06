package com.paipeng.cptesseractocrapp.view.ruler;

import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class VRuler extends Ruler {
    public static Logger logger = LoggerFactory.getLogger(VRuler.class);

    public VRuler() {
        super();

        reDrawRuler();

    }

    @Override
    protected void drawRuler() {
        super.drawRuler();
    }

    @Override
    protected void reDrawRuler() {
        super.reDrawRuler();
        addLine(new Line(19, 0, 19, 1080));
        addLine(new Line(0, 0, 19, 0));

        int position = 0;
        int h = 5;
        for (double i = 0; i < 1080; i+=getPixelsPerMillimeter()) {

            if (position % 10 == 0) {
                addLine(new Line(19, i, 19 -h*3, i));
                if (position > 0) {
                    Text text = new Text(-2, i + h * 3, String.valueOf(position));
                    text.setRotate(90);
                    addText(text);
                }
            } else if (position % 5 == 0) {
                addLine(new Line(19, i, 19 -h*2, i));
            } else {
                addLine(new Line(19, i, 19 -h, i));
            }

            position ++;
        }
    }
}
