package com.paipeng.cptesseractocrapp.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Locale;
import java.util.ResourceBundle;

public class CommonUtil {
    public static Logger logger = LoggerFactory.getLogger(CommonUtil.class);
    public static Locale getCurrentLanguageLocale() {
        Locale locale;
        if (true) {
            locale = new Locale("zh", "Zh");

        } else {
            locale = new Locale("en", "En");
        }
        return locale;
    }


    public static String getString(String key) {
        try {
            ResourceBundle resources = ResourceBundle.getBundle("bundles.languages", CommonUtil.getCurrentLanguageLocale());
            return resources.getString(key);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return "";
    }
}
