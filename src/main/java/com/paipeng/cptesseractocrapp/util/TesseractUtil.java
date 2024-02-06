package com.paipeng.cptesseractocrapp.util;


import com.paipeng.cptesseractocrapp.view.CPToolPane;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TesseractUtil {
    private static final String DATA_PATH = "src/main/resources/tessdata";
    public static Logger logger = LoggerFactory.getLogger(CPToolPane.class);
    public static String decode(String fileName, String language) {
        File imageFile = new File(fileName); //"eurotext.tif"
        ITesseract tesseract = new Tesseract();  // JNA Interface Mapping
        // ITesseract instance = new Tesseract1(); // JNA Direct Mapping
        tesseract.setDatapath(DATA_PATH); // path to tessdata directory

        try {
            tesseract.setLanguage(language); // eng
            tesseract.setPageSegMode(1);
            tesseract.setOcrEngineMode(1);
            String result = tesseract.doOCR(imageFile);
            logger.trace(result);
            return result;
        } catch (TesseractException e) {
            logger.error(e.getMessage());
            return null;
        }
    }

    public static List<String> getOCRLanguages() {
        return Stream.of(Objects.requireNonNull(new File(DATA_PATH).listFiles()))
                .filter(file -> !file.isDirectory() && !file.getPath().contains(".DS_Store"))
                .map(File::getName)
                .toList();
    }
}