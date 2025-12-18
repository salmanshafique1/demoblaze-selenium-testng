package com.framework.config;

import com.framework.exceptions.FrameworkException;

import java.io.InputStream;
import java.util.Properties;

public final class ConfigManager {

    private static FrameworkConfig config;

    private ConfigManager() {}

    public static FrameworkConfig getConfig() {
        if (config == null) {
            synchronized (ConfigManager.class) {
                if (config == null) {
                    config = loadConfig();
                }
            }
        }
        return config;
    }

    private static FrameworkConfig loadConfig() {
        try (InputStream input = ConfigManager.class
                .getClassLoader()
                .getResourceAsStream("framework-config.properties")) {

            if (input == null) {
                throw new FrameworkException("framework-config.properties not found in classpath");
            }

            Properties props = new Properties();
            props.load(input);

            FrameworkConfig fc = new FrameworkConfig();

            fc.setBaseUrl(props.getProperty("base.url"));

            // Correct usage — do NOT use System.setProperty as setter value
            String browser = props.getProperty("browser", "CHROME");
            fc.setBrowser(browser);
            System.setProperty("browser", browser);

            // Fix run.mode key (your file had run.model)
            String runMode = System.getProperty("run.mode",
                    props.getProperty("run.mode", "LOCAL"));
            fc.setRunMode(runMode);

            fc.setGridUrl(props.getProperty("grid.url"));
            fc.setExplicitWait(Integer.parseInt(props.getProperty("explicit.wait", "15")));

            boolean headless = Boolean.parseBoolean(
                    System.getProperty("headless", props.getProperty("headless", "false"))
            );
            fc.setHeadless(headless);

            return fc;

        } catch (Exception e) {
            throw new FrameworkException("Failed to load framework configuration", e);
        }
    }
}
