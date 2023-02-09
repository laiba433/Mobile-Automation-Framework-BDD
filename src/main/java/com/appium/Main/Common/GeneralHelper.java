package com.appium.Main.Common;

import com.appium.Main.Factory.DriverFactory;
import com.appium.Main.JsonClasses.GlobalVariables;
import com.appium.Main.JsonClasses.ParseLocators;
import com.appium.Main.Logger.MyLogger;
import io.appium.java_client.InteractsWithApps;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.TapOptions;
import io.appium.java_client.touch.offset.ElementOption;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import static io.appium.java_client.touch.WaitOptions.waitOptions;
import static io.appium.java_client.touch.offset.PointOption.point;
import static java.time.Duration.ofMillis;

public class GeneralHelper
{

    public static void swipe(int startX, int startY, int endX, int endY, int millis){

        TouchAction t = new TouchAction(DriverFactory.driver);
        t.press(point(startX, startY)).waitAction(waitOptions(ofMillis(millis))).moveTo(point(endX, endY)).release().perform();
    }
    public static void Wait(int TimeInMilliseconds){
        try
        {
            Thread.sleep(TimeInMilliseconds);
        }
        catch (InterruptedException e)
        {
            MyLogger.log.error(e);
            throw new RuntimeException(e);
        }
    }

    public static void WaitForVisibility(int TimeInSeconds, String ElementKey)
    {
        WebDriverWait wait = new WebDriverWait(DriverFactory.driver, TimeInSeconds);
        try
        {
            wait.until(ExpectedConditions.visibilityOfElementLocated(ParseLocators.GetLocator(ElementKey)));
        }
        catch(Exception ex)
        {
            MyLogger.log.error(ex);
        }
    }

    public static void WaitForVisibilityofElements(int TimeInSeconds, String ElementKey)
    {
        WebDriverWait wait = new WebDriverWait(DriverFactory.driver, TimeInSeconds);
        try
        {
            wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(ParseLocators.GetLocator(ElementKey)));
        }
        catch(Exception ex)
        {
            MyLogger.log.error(ex);
        }
    }

    public static  void WaitUntilAlertIsPresent()
    {
        WebDriverWait wait = new WebDriverWait(DriverFactory.driver, 5);
        try
        {
            wait.until(ExpectedConditions.alertIsPresent());
        }
        catch (Exception ex)
        {
            MyLogger.log.error(ex);
        }
    }

    public static void SwipeDown() {
        Dimension size = DriverFactory.driver.manage().window().getSize();
        int startX = (int) (size.width * 0.5);
        int endX = (int) (size.width * 0.5);
        int endY = (int) (size.height * 0.4);
        int startY = (int) (size.height * 0.6);
        TouchAction t = new TouchAction(DriverFactory.driver);
        t.press(point(startX, startY)).waitAction().moveTo(point(endX, endY)).release()
                .perform();
    }

    public static void SwipeUP() {
        Dimension size = DriverFactory.driver.manage().window().getSize();
        int startX = (int) (size.width * 0.5);
        int endX = (int) (size.width * 0.5);
        int endY = (int) (size.height * 0.8);
        int startY = (int) (size.height * 0.6);
        TouchAction t = new TouchAction(DriverFactory.driver);
        t.press(point(startX, startY)).waitAction().moveTo(point(endX, endY)).release()
                .perform();
    }
    public static void LogMessage(String LoggingType, String Message){
        switch (LoggingType)
        {
            case "Debug":
            {
                MyLogger.log.debug(Message);
                break;
            }
            case "Info":
            {
                MyLogger.log.info(Message);
                break;
            }
            case "Error":
            {
                MyLogger.log.error(Message);
                break;
            }
            case "Warn":
            {
                MyLogger.log.warn(Message);
                break;
            }
            default:
            {
                MyLogger.log.error("Invalid Log type requested i.e. " +LoggingType+"");
            }
        }
    }

    public static void CheckPresenceOfElement(String Key)
    {
        String Result;
        MobileElement element = ElementFinder.FindElement(Key, DriverFactory.driver);
        WaitForVisibility(10,Key);
        boolean IsPresent = element.isDisplayed();
        if (IsPresent == true)
        {
            Result= "Success";
            MyLogger.log.info(Key+" Control is present");
        }
        else
        {
            Result="Fail";
            MyLogger.log.info(Key+" Control is not present");
        }

        Assert.assertEquals(GlobalVariables.Success,Result);
    }
    public static void closeApp()
    {
        ((InteractsWithApps) DriverFactory.driver).closeApp();
    }
    public static void  launchApp()
    {
        ((InteractsWithApps) DriverFactory.driver).launchApp();
    }

    public static boolean IsControlEnabled(String Key)
    {
        MobileElement element = ElementFinder.FindElement(Key, DriverFactory.driver);
        boolean isEnabled = element.isEnabled();

        if(isEnabled == true)
        {
            MyLogger.log.info("Control is enabled");
            return true;
        }

        else
        {
            MyLogger.log.info("Control is disabled");
            return false;
        }


    }

    public static String[] ConvertStringToStringArray(String Value, String SplitBy)
    {
        String[] ConvertedArray = Value.split(SplitBy);
        return ConvertedArray;
    }
    public static void TapOnElement(MobileElement element)
    {
        try
        {
            TouchAction action = new TouchAction(DriverFactory.driver);
            action.tap(new TapOptions().withElement(new ElementOption().withElement(element))).perform();
        }
        catch(Exception ex)
        {
            if(element==null)
            {
                MyLogger.log.error("Element is null, unable to tap on the element");
            }
            else
            {
                MyLogger.log.error(ex);
            }

            Assert.fail();
        }

    }
}
