package com.appium.Main.DriverClasses;

import com.appium.Main.Common.GeneralHelper;
import com.appium.Main.Factory.DriverFactory;
import com.appium.Main.Interface.IDriverGeneralMethods;
import com.appium.Main.JsonClasses.ParseLocators;
import com.appium.Main.Logger.MyLogger;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.Capabilities;

import java.net.URL;
import java.util.List;

public class Android extends AndroidDriver implements IDriverGeneralMethods {

    public static Android androidDriver;

    public Android(URL remoteAddress, Capabilities desiredCapabilities) {
        super(remoteAddress, desiredCapabilities);
    }

    @Override
    public String SubmitVerificationCode(String Code)
    {
        String result;
        try
        {
            GeneralHelper.Wait(2000);
            List<MobileElement> elementlist = DriverFactory.driver.findElements(ParseLocators.GetLocator("CodeField"));
            String[] CodeArray = Code.split("", Code.length());
            if (CodeArray.length == elementlist.size())
            {
                for (int i = 0; i < elementlist.size(); i++)
                {
                    elementlist.get(i).sendKeys(CodeArray[i]);
                }
                result="Success";
            }
            else
            {
                MyLogger.log.error("Invalid Code value is given, 6 digits code should be entered and you have given " + CodeArray.length + " digits code");
                result = "Fail";
            }
        }
        catch(Exception ex)
        {
            MyLogger.log.error(ex);
            result ="Fail";
        }
        return result;
    }

}
