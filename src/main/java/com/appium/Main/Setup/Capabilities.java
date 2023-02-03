package com.appium.Main.Setup;

import com.appium.Main.Factory.factory;
import com.appium.Main.JsonClasses.App;
import com.appium.Main.Logger.MyLogger;
import com.appium.Main.Manager.PropertyManager;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Properties;

public class Capabilities extends DesiredCapabilities
{
    public static Capabilities caps = null;
    public static Capabilities Instance(){
        if (caps == null)
        {
            caps = new Capabilities();
        }

        return caps;
    }

    public Capabilities()
    {
        Properties props = new PropertyManager().getProps("config.properties");
        try
        {
            if (App.key.equals("iOS")) {
                DesiredCapabilities capabilities = factory.CreateDesiredCapabilitiesInstance();
                capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, App.PlatformName);
                capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, App.PlatformVersion);
                capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, App.DeviceName);
                capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, props.getProperty("iOSAutomationName"));
                capabilities.setCapability(MobileCapabilityType.UDID, props.getProperty("Udid"));
                capabilities.setCapability(MobileCapabilityType.APP, props.getProperty("iOSApp"));
            } else if (App.key.equals("Android")) {
                DesiredCapabilities capabilities = factory.CreateDesiredCapabilitiesInstance();
                capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, App.DeviceName);
                capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, App.PlatformName);
                capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, App.PlatformVersion);
                factory.capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, props.getProperty("AndroidAutomationName"));
                capabilities.setCapability(MobileCapabilityType.APP, System.getProperty("user.dir") + props.getProperty("AndroidApp"));
                capabilities.setCapability("enableMultiWindows", true);
            }
            else
            {
                MyLogger.log.error("Invalid Driver Key, Unable to set driver capabilities");
            }
        }
        catch(Exception ex)
        {
            MyLogger.log.error(ex);
        }
    }

}
