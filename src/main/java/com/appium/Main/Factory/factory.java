package com.appium.Main.Factory;

import org.openqa.selenium.remote.DesiredCapabilities;

public class factory
{
    public static DesiredCapabilities capabilities = null;
    public static DesiredCapabilities CreateDesiredCapabilitiesInstance()
    {
        if (capabilities == null)
        {
            capabilities = new DesiredCapabilities();

        }
        return capabilities;
    }
}
