package com.paipeng.cptesseractocrapp.util;

import java.util.Locale;

public class CommonUtil {
    public static Locale getCurrentLanguageLocale() {
        Locale locale;
        if (true) {
            locale = new Locale("zh", "Zh");

        } else {
            locale = new Locale("en", "En");
        }
        return locale;
    }
}
