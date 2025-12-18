package com.framework.config;

public class FrameworkConfig {

    private String baseUrl;
    private String browser;
    private String runMode;
    private String gridUrl;
    private int explicitWait;
    private boolean headless;

    public String getBaseUrl(){
        return baseUrl;
    }
    public void setBaseUrl(String baseUrl){
        this.baseUrl = baseUrl;
    }
    public String getBrowser(){
        return browser;
    }
    public void setBrowser(String browser){
        this.browser = browser;
    }
    public String getRunMode(){
        return runMode;
    }
    public void setRunMode(String runMode){
        this.runMode = runMode;
    }
    public String getGridUrl(){
        return gridUrl;
    }
    public void setGridUrl(String gridUrl){
        this.gridUrl = gridUrl;
    }
    public int getExplicitWait(){
        return explicitWait;
    }
    public void setExplicitWait(int explicitWait){
        this.explicitWait = explicitWait;
    }
    public boolean isHeadless(){
        return headless;
    }
    public void setHeadless(boolean headless){
        this.headless = headless;
    }
}
