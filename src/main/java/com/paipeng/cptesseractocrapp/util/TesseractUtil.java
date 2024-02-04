package com.paipeng.cptesseractocrapp.util;

import java.io.File;

public class TesseractUtil {
    public static void decode(String fileName) {
        File imageFile = new File(fileName); //"eurotext.tif"
        ITesseract instance = new Tesseract();  // JNA Interface Mapping
        // ITesseract instance = new Tesseract1(); // JNA Direct Mapping
        instance.setDatapath("tessdata"); // path to tessdata directory

        try {
            String result = instance.doOCR(imageFile);
            System.out.println(result);
        } catch (TesseractException e) {
            System.err.println(e.getMessage());
        }
    }
}