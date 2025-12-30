package com.listeners;

import com.framework.core.DriverManager;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class AllureScreenshotListener implements ITestListener {

    @Attachment(value = "Failure Screenshot", type = "image/png")
    public byte[] captureScreenshot(){
        if(DriverManager.getDriver()==null){
            return new byte[0];
        }
        return ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.BYTES);
    }
    @Override
    public void onTestFailure(ITestResult result){
        captureScreenshot();
    }


}
