package com.appium.Main.Common;

import com.appium.Main.DriverClasses.AndroidList;
import com.appium.Main.Factory.DriverFactory;
import com.appium.Main.JsonClasses.App;
import com.appium.Main.JsonClasses.ParseLocators;
import com.appium.Main.Logger.MyLogger;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import java.util.List;



public class ElementFinder
{

    public static MobileElement FindElement(String Key, AppiumDriver driver)
    {
        String PageSource= DriverFactory.driver.getPageSource();
        return FindElement(Key, driver,2,false,false,PageSource);
    }

    public static MobileElement FindElement(String Key, MobileElement ParentElement)
    {
        String PageSource= DriverFactory.driver.getPageSource();
        return FindElement(Key,ParentElement,2,false,false,PageSource);
    }

    private static MobileElement FindElement(String Key, AppiumDriver driver,int attempts, boolean endOfPage, boolean topOfthePage, String previousPageSource)
    {
        MobileElement element = null;
        try
        {
            GeneralHelper.WaitForVisibility(5,Key);
            element = (MobileElement) driver.findElement(ParseLocators.GetLocator(Key));

            if (element != null)
            {
                MyLogger.log.info(Key + " Element has found");
            }
            return element;
        }
        catch (Exception e)
        {
            if (element == null)
            {
                attempts--;
                if (attempts > 0)
                {
                    MyLogger.log.info("Second Attempt to find the element");
                    return FindElement(Key, driver, attempts, endOfPage,topOfthePage,previousPageSource);

                }
                else
                {
                    if (!endOfPage && topOfthePage==false)
                    {
                        GeneralHelper.SwipeDown();
                        endOfPage = previousPageSource.equals(DriverFactory.driver.getPageSource());
                        previousPageSource = DriverFactory.driver.getPageSource();
                        return FindElement(Key, driver, attempts, endOfPage,topOfthePage,previousPageSource);
                    }
                    else if (!topOfthePage && endOfPage==true)
                    {
                        GeneralHelper.SwipeUP();
                        topOfthePage = previousPageSource.equals(DriverFactory.driver.getPageSource());
                        previousPageSource = DriverFactory.driver.getPageSource();
                        return FindElement(Key, driver, attempts, endOfPage,topOfthePage,previousPageSource);
                    }
                    else
                    {
                        MyLogger.log.error("Cannot find an element " + Key + " Locator strategy even after scroll the window");
                        return element;
                    }
                }
            }
            return element;
        }

    }
    private static MobileElement FindElement(String Key, MobileElement ParentElement,int attempts, boolean endOfPage,boolean topOfthePage, String previousPageSource)
    {
        MobileElement element = null;
        try {
            GeneralHelper.WaitForVisibility(5,Key);
            element = ParentElement.findElement(ParseLocators.GetLocator(Key));

            if (element != null)
            {
                MyLogger.log.info(Key + " Element has found");
            }
            return element;
        }
        catch (Exception e)
        {
            if (element == null)
            {
                attempts--;
                if (attempts > 0)
                {
                    MyLogger.log.info("Second Attempt to find the element");
                    return FindElement(Key, ParentElement, attempts, endOfPage,topOfthePage,previousPageSource);

                }
                else
                {

                    if (!endOfPage && topOfthePage==false)
                    {
                        GeneralHelper.SwipeDown();
                        endOfPage = previousPageSource.equals(DriverFactory.driver.getPageSource());
                        previousPageSource = DriverFactory.driver.getPageSource();
                        return FindElement(Key, ParentElement, attempts, endOfPage,topOfthePage,previousPageSource);
                    }
                    else if (!topOfthePage && endOfPage==true)
                    {
                        GeneralHelper.SwipeUP();
                        topOfthePage = previousPageSource.equals(DriverFactory.driver.getPageSource());
                        previousPageSource = DriverFactory.driver.getPageSource();
                        return FindElement(Key, ParentElement, attempts, endOfPage,topOfthePage,previousPageSource);
                    }
                    else
                    {
                        MyLogger.log.error("Cannot find an element " + Key + " Locator strategy even after scroll the window");
                        return element;
                    }
                }
            }
            return element;
        }

    }

    public static List<MobileElement> GetElementList(String Key, AppiumDriver driver){
       List<MobileElement> ElementList= null;

try
{
    GeneralHelper.WaitForVisibilityofElements(2,Key);

    if(App.key.equals("Android"))
    {
        ElementList=  AndroidList.Instance().GetAndroidList(Key,driver);
    }
    else
    {
        ElementList= driver.findElements(ParseLocators.GetLocator(Key));
    }

}

catch (Exception e)
{
    if(ElementList == null)
    {
        MyLogger.log.error(e);
        MyLogger.log.error("Cannot find an element "+Key);
        return ElementList;
    }
}

return ElementList;
    }

    public static List<MobileElement> GetElementList(String Key, MobileElement ParentElement){
        List<MobileElement> elementList= null;

        try
        {
            GeneralHelper.WaitForVisibilityofElements(2,Key);

            if(App.key.equals("Android"))
            {
                elementList=  AndroidList.Instance().GetAndroidList(Key,ParentElement);
            }
            else
            {
                elementList= ParentElement.findElements(ParseLocators.GetLocator(Key));
            }

        }


        catch (Exception e)
        {
            if(elementList == null)
            {
                MyLogger.log.error(e);
                MyLogger.log.error("Cannot find an element "+Key+"");
                return elementList;
            }
        }

        return elementList;

    }


}
