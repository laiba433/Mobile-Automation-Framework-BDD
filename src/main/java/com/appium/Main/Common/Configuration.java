package com.appium.Main.Common;

import com.appium.Main.Factory.ComboBoxFactory;
import com.appium.Main.Factory.DriverFactory;
import com.appium.Main.Factory.ListFactory;
import com.appium.Main.Interface.IDriverGeneralMethods;
import com.appium.Main.JsonClasses.App;
import com.appium.Main.JsonClasses.GlobalVariables;
import com.appium.Main.Manager.PropertyManager;

public class Configuration
{
    public static void InitializeGlobalVariables() {
        GlobalVariables.Instance().SetGlobalVariables();
        ListFactory.GetListControl(App.key);
        ComboBoxFactory.GetComboBoxControl(App.key);
        DriverFactory.getDriver();
        DriverFactory.GetDriverSpecificMethods((IDriverGeneralMethods) DriverFactory.driver);
    }
    public static void InitializeProperties(String Udid)
    {
        PropertyManager.UpdateProperty("Udid",Udid, "config.properties");
        System.setProperty("extent.reporter.spark.start","true");
        System.setProperty("extent.reporter.spark.out","Reports/"+App.key+"_"+App.DeviceName+"_"+App.PlatformVersion+"-Report.html");
    }
    public static void InitializeAppDetails(String Key, String DeviceName, String PlatformVersion, String PlatformName)
    {
        App.GetAppDetails(Key, DeviceName, PlatformVersion, PlatformName);
    }
}
