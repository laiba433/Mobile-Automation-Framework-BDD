package com.appium.Main.DriverClasses;

import com.appium.Main.Common.ElementFinder;
import com.appium.Main.Factory.DriverFactory;
import com.appium.Main.Interface.IDriverGeneralMethods;
import com.appium.Main.Logger.MyLogger;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.Capabilities;

import java.net.URL;

public class iOS extends IOSDriver implements IDriverGeneralMethods {

    public static iOS iosDriver;

    public iOS(URL remoteAddress, Capabilities desiredCapabilities) {
        super(remoteAddress, desiredCapabilities);
    }

    @Override
    public String SubmitVerificationCode(String Code)
    {
        String result;
        try
        {
            ElementFinder.FindElement("CodeField", DriverFactory.driver).sendKeys(Code);
            result= "Success";

        }
        catch(Exception ex)
        {
            MyLogger.log.error(ex);
            result ="Fail";
        }
        return result;
    }

}
