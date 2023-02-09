package com.appium.Main.DriverClasses;

import com.appium.Main.Common.ElementFinder;
import com.appium.Main.Common.GeneralHelper;
import com.appium.Main.Factory.DriverFactory;
import com.appium.Main.Interface.IComboBox;
import com.appium.Main.JsonClasses.ParseLocators;
import com.appium.Main.Logger.MyLogger;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.PointOption;
import org.testng.Assert;

import java.util.List;

import static io.appium.java_client.touch.offset.PointOption.point;

public class AndroidComboBox implements IComboBox
{
    public static AndroidComboBox Instance=null;
    public static AndroidComboBox Instance()
    {
        {
            if(Instance== null)
            {
                Instance = new AndroidComboBox();
            }
            return Instance;
        }
    }
    public void SelectComboValue(String ComboBoxType, String Value)
    {
        MobileElement ComboBox= ElementFinder.FindElement(ComboBoxType, DriverFactory.driver);
        ComboBox.click();
        FindComboBoxElement(ComboBoxType,Value,false,false, DriverFactory.driver.getPageSource());

    }
    private void scrollEntireList()
    {
        List<MobileElement> listElement = DriverFactory.driver.findElements(ParseLocators.GetLocator("ComboBoxValues"));
        TouchAction touchAction = new TouchAction(DriverFactory.driver);
        MobileElement bottomElement = listElement.get(listElement.size()-1);
        MobileElement topElement = listElement.get(0);
        touchAction.press(PointOption.point(bottomElement.getCenter())).moveTo(PointOption.point(topElement.getCenter())).release().perform();
    }
    public void FindComboBoxElement(String ComboBoxType, String Value,boolean found,boolean endOfPage, String previousPageSource)
    {
        try
        {
            GeneralHelper.WaitForVisibilityofElements(5,"ComboBoxValues");
            List<MobileElement> list = DriverFactory.driver.findElements(ParseLocators.GetLocator("ComboBoxValues"));
            if (list==null)
            {
                MyLogger.log.info("List is empty, Kindly check Configuration of " + ComboBoxType);
            }
            else
            {
                for (MobileElement element : list)
                {
                    String TextElement = element.findElementByClassName("android.widget.TextView").getText();
                    if (TextElement.equals(Value))
                    {
                        element.click();
                        found = true;
                    }
                }
                if (!found)
                {
                    if (!endOfPage)
                    {
                        endOfPage = previousPageSource.equals(DriverFactory.driver.getPageSource());
                        previousPageSource = DriverFactory.driver.getPageSource();
                        scrollEntireList();
                        FindComboBoxElement(ComboBoxType, Value, found, endOfPage,previousPageSource);
                    }
                    else
                    {
                        MyLogger.log.info(Value+ " Value is not present in dropdown");
                        Assert.fail();
                    }

                }
            }
        }
        catch (Exception ex)
        {
            MobileElement element = (MobileElement) DriverFactory.driver.findElementById("com.logiciel.vbate.android.app.qa:id/orderTypeSpinnerText");
            if ((!element.getText().equals(Value)))
            {
                MyLogger.log.info("Value not present in dropdown or corrected value didn't set properly");
                MyLogger.log.info(ex);
                Assert.fail();
            }
            else
            {
                MyLogger.log.info(ex);
                Assert.fail();
            }
        }
    }
}







