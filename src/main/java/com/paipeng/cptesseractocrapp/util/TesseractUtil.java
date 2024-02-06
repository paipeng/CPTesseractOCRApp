package com.paipeng.cptesseractocrapp.util;


import com.paipeng.cptesseractocrapp.view.CPToolPane;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

public class TesseractUtil {
    public static Logger logger = LoggerFactory.getLogger(CPToolPane.class);
    public static String decode(String fileName, String language) {
        File imageFile = new File(fileName); //"eurotext.tif"
        ITesseract tesseract = new Tesseract();  // JNA Interface Mapping
        // ITesseract instance = new Tesseract1(); // JNA Direct Mapping
        tesseract.setDatapath("src/main/resources/tessdata"); // path to tessdata directory

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
}