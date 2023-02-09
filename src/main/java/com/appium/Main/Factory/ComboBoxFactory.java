package com.appium.Main.Factory;

import com.appium.Main.DriverClasses.AndroidComboBox;
import com.appium.Main.DriverClasses.iOSComboBox;
import com.appium.Main.Interface.IComboBox;
import com.appium.Main.Logger.MyLogger;


public class ComboBoxFactory
{
    public static IComboBox AppiumCombBox = null;
    public static IComboBox GetComboBoxControl(String Key)
    {
        if(AppiumCombBox == null)
        {
            if (Key.equalsIgnoreCase("Android"))
            {
                AppiumCombBox= new AndroidComboBox();
            }
            else if(Key.equalsIgnoreCase("iOS"))
            {
                AppiumCombBox= new iOSComboBox();
            }
            else
            {
                MyLogger.log.error("You have not entered correct Key i.e. "+Key);
            }
        }
        return AppiumCombBox;
    }

}
