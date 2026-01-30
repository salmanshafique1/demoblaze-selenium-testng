package com.framework.config;

import com.framework.exceptions.FrameworkException;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import java.io.InputStream;
import java.util.Properties;

public final class ConfigManager {

    private static final Logger log = LoggerFactory.getLogger(ConfigManager.class);

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
        log.info("Loading configuration");
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


            String browser = props.getProperty("browser", "CHROME");
            fc.setBrowser(browser);
            System.setProperty("browser", browser);

            String runMode = System.getProperty("run.mode",
                    props.getProperty("run.mode", "LOCAL"));
            fc.setRunMode(runMode);

            fc.setGridUrl(props.getProperty("grid.url"));
            fc.setExplicitWait(Integer.parseInt(props.getProperty("explicit.wait", "15")));

            boolean headless = Boolean.parseBoolean(
                    System.getProperty("headless", props.getProperty("headless", "false"))
            );
            fc.setHeadless(headless);

            log.info("Config Loaded | baseUrl={} | browser={} | runMode={} ",
                    fc.getBaseUrl(), fc.getBrowser(), fc.getBaseUrl());

            return fc;

        } catch (Exception e) {
            throw new FrameworkException("Failed to load framework configuration", e);
        }
    }
}
