package com.paipeng.cptesseractocrapp.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class VersionProperties {
    private static Properties p;
    private static VersionProperties instance;

    public VersionProperties() {
        p = new Properties();
        try {
            InputStream in = getClass().getResourceAsStream("/version.properties");
            p.load(in);
            assert in != null;
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static VersionProperties getInstance() {
        if (instance == null)
            instance = new VersionProperties();
        return instance;
    }

    public String getValue(String key) throws IOException {
        return p.getProperty(key, "");
    }

    public String getVersion() {
        return p.getProperty("version", "");
    }
}
