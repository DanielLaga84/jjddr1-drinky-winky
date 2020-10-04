package com.infoshare.drinkywinky.properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigLoader {
    private Properties prop = null;
    private static final Logger STDOUT = LoggerFactory.getLogger("CONSOLE_OUT");
    public static final String RECIPE_SORT_TYPE_KEY = "recipe.sort.type";
    public static final String DATE_FORMAT_KEY = "date.format";

    public ConfigLoader loadAppConfig() {
        AppConfig.recipeSortType = getProperty( RECIPE_SORT_TYPE_KEY,"ASC");
        AppConfig.dateFormat = getProperty( DATE_FORMAT_KEY,"HH:mm:ss dd-MM-yyyy");

        return null;
    }

    private String getProperty(String key, String defaultValue) {
        String result = null;
        loadProperties();
        if (prop.getProperty(key) == null) {
            STDOUT.info("Wrong key: " + key + " in config file!  " + "\n" + "Set new default value: "
                    + defaultValue);
            result = (String) prop.get(key);
        } else {
            result = prop.getProperty(key);
        }
        return result;
    }

    public void setProp(Properties prop) {
        this.prop = prop;
    }

    private void loadProperties() {
        try (InputStream input
                     = new FileInputStream("./resources/config.properties")) {

            if (input == null) {
                STDOUT.info("Sorry, unable to find config.properties");
                return;
            }
            prop = new Properties();
            prop.load(input);

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}