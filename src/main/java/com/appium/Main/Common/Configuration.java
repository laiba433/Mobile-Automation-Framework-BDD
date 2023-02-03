package com.appium.Main.Common;

import com.appium.Main.Factory.ComboBoxFactory;
import com.appium.Main.Factory.DriverFactory;
import com.appium.Main.Factory.ListFactory;
import com.appium.Main.JsonClasses.App;
import com.appium.Main.JsonClasses.GlobalVariables;
import com.appium.Main.Manager.PropertyManager;

public class Configuration
{
    public static void InitializeGlobalVariables() {
        GlobalVariables.Instance().SetGlobalVariables();
        ListFactory.GetListControl(App.key);
        ComboBoxFactory.GetComboBoxControl(App.key);
        DriverFactory.GetDriverSpecificMethods();
        DriverFactory.getDriver();
    }
    public static void InitializeProperties(String Udid)
    {
        PropertyManager.UpdateProperty("Udid",Udid, "config.properties");
        System.setProperty("extent.reporter.spark.start","true");
        System.setProperty("extent.reporter.spark.out",""+App.key+"_"+App.DeviceName+"_"+App.PlatformVersion+"/Mobile-Automation-Report.html");
    }
    public static void InitializeAppDetails(String Key, String DeviceName, String PlatformVersion, String PlatformName)
    {
        App.GetAppDetails(Key, DeviceName, PlatformVersion, PlatformName);
    }
}
