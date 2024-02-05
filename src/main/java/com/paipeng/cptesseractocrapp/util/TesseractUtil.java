package com.paipeng.cptesseractocrapp.util;

import java.io.File;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

public class TesseractUtil {
    public static void decode(String fileName) {
        File imageFile = new File(fileName); //"eurotext.tif"
        ITesseract tesseract = new Tesseract();  // JNA Interface Mapping
        // ITesseract instance = new Tesseract1(); // JNA Direct Mapping
        tesseract.setDatapath("tessdata"); // path to tessdata directory

        try {
            tesseract.setLanguage("eng");
            tesseract.setPageSegMode(1);
            tesseract.setOcrEngineMode(1);
            String result = tesseract.doOCR(imageFile);
            System.out.println(result);
        } catch (TesseractException e) {
            System.err.println(e.getMessage());
        }
    }
}