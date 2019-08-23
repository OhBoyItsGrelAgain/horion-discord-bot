package de.richard.horionbot.utils;

import java.io.*;
import java.util.Properties;

public class ConfigManager {


    public static Properties config = new Properties();

    public static void initiate() {
        try {
            File save = new File("config.xml");
            if (!save.exists()) {
                saveDefaultConfig();
            } else {
                InputStream is = new FileInputStream("config.xml");
                config.clear();
                config.loadFromXML(is);
            }
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static void save() {
        try {
            OutputStream os = new FileOutputStream("config.xml");
            String sugvar;
            if (Suggestions.suggestionsEnabled) {
                sugvar = "true";
            } else {
                sugvar = "false";
            }
            config.setProperty("suggestionsEnabled", sugvar);
            config.storeToXML(os, "Config file by Horion-Bot");
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    private static void saveDefaultConfig() {
        try {
            OutputStream os = new FileOutputStream("config.xml");
            config.clear();
            config.setProperty("token", "Token goes here");
            config.setProperty("suggestionsEnabled", "true");
            config.storeToXML(os, "Config file by Horion-Bot");
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
