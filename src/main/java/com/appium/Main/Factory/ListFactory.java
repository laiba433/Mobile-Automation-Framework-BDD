package com.appium.Main.Factory;

import com.appium.Main.DriverClasses.AndroidList;
import com.appium.Main.DriverClasses.iOSList;
import com.appium.Main.Interface.IList;
import com.appium.Main.Logger.MyLogger;

public class ListFactory
{
    public static IList AppiumList = null;
    public static IList GetListControl(String Key)
    {
        if(AppiumList == null)
        {
            if (Key.equalsIgnoreCase("Android"))
            {
                AppiumList= new AndroidList();
            }
            else if(Key.equalsIgnoreCase("iOS"))
            {
                AppiumList= new iOSList();
            }
            else
            {
                MyLogger.log.error("You have not entered correct Key i.e. "+Key);
            }
        }
        return AppiumList;
    }
}
