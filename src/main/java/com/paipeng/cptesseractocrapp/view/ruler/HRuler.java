package com.paipeng.cptesseractocrapp.view.ruler;

import javafx.scene.shape.Line;
import javafx.scene.text.Text;

public class HRuler extends Ruler {
    public HRuler() {
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
        addLine(new Line(20, 19, 1440, 19));
        addLine(new Line(20, 0, 20, 19));


        int position = 0;
        int h = 5;
        for (double i = 20; i < 1440; i += getPixelsPerMillimeter()) {

            if (position % 10 == 0) {
                addLine(new Line(i, 19 - h * 3, i, 19));
                if (position > 0) {
                    addText(new Text(i + 2, 19 - h - 2, String.valueOf(position)));
                }
            } else if (position % 5 == 0) {
                addLine(new Line(i, 19 - h * 2, i, 19));
            } else {
                addLine(new Line(i, 19 - h, i, 19));
            }

            position++;
        }
    }
}
