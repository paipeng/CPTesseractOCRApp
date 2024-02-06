package com.paipeng.cptesseractocrapp.util;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageUtil {
    public static Logger logger = LoggerFactory.getLogger(ImageUtil.class);
    public static Image convertToFxImage(BufferedImage image) {
        WritableImage wr = null;
        if (image != null) {
            wr = new WritableImage(image.getWidth(), image.getHeight());
            PixelWriter pw = wr.getPixelWriter();
            for (int x = 0; x < image.getWidth(); x++) {
                for (int y = 0; y < image.getHeight(); y++) {
                    pw.setArgb(x, y, image.getRGB(x, y));
                }
            }
        }

        return new ImageView(wr).getImage();
    }
    public static BufferedImage readBufferedImage(String imageFileName) throws IOException {
        logger.trace("readBufferedImage: " + imageFileName);
        File file = new File(imageFileName);
        if (file.exists()) {
            logger.trace("file: " + file.getAbsolutePath());
            return ImageIO.read(file);
        } else {
            logger.error("file not exists");
            return null;
        }
    }
}
