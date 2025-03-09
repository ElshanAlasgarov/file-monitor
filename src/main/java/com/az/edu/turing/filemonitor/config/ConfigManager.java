package com.az.edu.turing.filemonitor.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigManager {

    private static final String CONFIG_FILE = "config.properties";
    private static Properties properties = new Properties();

    public static void loadConfig() {
        try (InputStream input = ConfigManager.class.getClassLoader().getResourceAsStream(CONFIG_FILE);) {
            if (input == null) {
                System.err.println("Config file not found: " + CONFIG_FILE);
                return;
            }
            properties.load(input);
        } catch (IOException e) {
            System.err.println("Failed to load config file: " + CONFIG_FILE);
        }
    }


    public static String getInputDirectory() {
        return properties.getProperty("input.directory", "./input");
    }

    public static int getMonitoringInterval() {
        return Integer.parseInt(properties.getProperty("monitoring.interval", "5000"));
    }
}
