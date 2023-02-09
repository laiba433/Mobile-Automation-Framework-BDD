package com.appium.Main.Factory;

import com.appium.Main.DriverClasses.*;
import com.appium.Main.Interface.IDriverGeneralMethods;
import com.appium.Main.JsonClasses.App;
import com.appium.Main.JsonClasses.GlobalVariables;
import com.appium.Main.Logger.MyLogger;
import com.appium.Main.Setup.Capabilities;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

import java.net.URL;

public class DriverFactory {

    public static AppiumDriver<MobileElement> driver = null;

    public static IDriverGeneralMethods appium = null;

    public static AppiumDriver<MobileElement> getDriver() {
        try
        {
            Capabilities.Instance();
            if (App.key.equalsIgnoreCase("iOS"))
            {
                iOS.iosDriver = new iOS(new URL(GlobalVariables.Instance().iOSServerURL), factory.capabilities);
                driver = iOS.iosDriver;
                MyLogger.log.info("IOS Driver is set");

            }
            else if (App.key.equalsIgnoreCase("Android"))
            {
                Android.androidDriver = new Android(new URL(GlobalVariables.Instance().AndroidServerURL), factory.capabilities);
                driver = Android.androidDriver;
                MyLogger.log.info("Android Driver is set");
            }
            else
            {
                MyLogger.log.error("Driver Key is undefined. Unable to set the driver");
            }
        }
        catch (Exception ex)
        {
            MyLogger.log.error(ex);
        }

        return driver;
    }


    public static IDriverGeneralMethods GetDriverSpecificMethods(IDriverGeneralMethods driver) {
        if (appium == null)
        {
            appium = driver;
        }
        return appium;
    }


}