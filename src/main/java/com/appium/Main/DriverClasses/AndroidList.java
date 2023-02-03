package com.appium.Main.DriverClasses;


import com.appium.Main.Common.ElementFinder;
import com.appium.Main.Common.GeneralHelper;
import com.appium.Main.Factory.DriverFactory;
import com.appium.Main.Interface.IList;
import com.appium.Main.JsonClasses.ParseLocators;
import com.appium.Main.Logger.MyLogger;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.cucumber.datatable.DataTable;
import org.junit.Assert;

import java.util.*;

public class AndroidList implements IList {
    public static AndroidList Instance = null;

    public static AndroidList Instance() {
        {
            if (Instance == null) {
                Instance = new AndroidList();
            }

            return Instance;
        }
    }

    public List<MobileElement> GetAndroidList(String ListKey, AppiumDriver driver)
    {
        List<MobileElement> List;
        boolean endOfPage = false;
        String previousPageSource = driver.getPageSource();
        Set<MobileElement> emptyList = new HashSet<>();
        while (!endOfPage)
        {
            List = driver.findElements(ParseLocators.GetLocator(ListKey));
            for (MobileElement ele : List)
            {
                emptyList.add(ele);
            }
            GeneralHelper.Instance().SwipeDown();
            endOfPage = previousPageSource.equals(driver.getPageSource());
            previousPageSource = driver.getPageSource();
        }
        List<MobileElement> itemList = new ArrayList<>(emptyList);
        return itemList;
    }

    public List<MobileElement> GetAndroidList(String ListKey, MobileElement ParentElement)
    {   List<MobileElement> List;
        boolean endOfPage = false;
        String previousPageSource = DriverFactory.driver.getPageSource();
        Set<MobileElement> emptyList = new HashSet<>();
        while (!endOfPage)
        {
            List = ParentElement.findElements(ParseLocators.GetLocator(ListKey));
            for (MobileElement ele : List)
            {
                emptyList.add(ele);
            }

            GeneralHelper.Instance().SwipeDown();
            endOfPage = previousPageSource.equals(DriverFactory.driver.getPageSource());
            previousPageSource = DriverFactory.driver.getPageSource();
        }

        List<MobileElement> itemList = new ArrayList<>(emptyList);
        return itemList;
    }
    @Override
    public int GetGridIndexValueOfPosition(String Symbol, String Position, String AccountValue)
    {
        GeneralHelper.Instance().Wait(5000);
        List<MobileElement> List = ElementFinder.Instance().GetElementList("PositionList", DriverFactory.driver);
        if (List.size() != 0)
        {
            MyLogger.log.info("There are " + List.size() + " Positions against " + AccountValue + " account");
        }
        else
        {
            MyLogger.log.info("There is no position against " + AccountValue + " account, Cannot find the" + Symbol + "Position as list is empty");
            Assert.fail();
        }
        int index = -1;
        for (MobileElement el : List)
        {
            index++;
            MobileElement Symboltext = ElementFinder.Instance().FindElement("PositionSymbolLabel", el);
            MobileElement Positiontext = ElementFinder.Instance().FindElement("PositionStatusLabel", el);
            if (Symboltext.getText().equals(Symbol) && Positiontext.getText().equals(Position)) {
                MyLogger.log.info("Index of Position " + Symbol + " against " + AccountValue + " is " + index);
                break;
            }
            else if (index == List.size() - 1)
            {
                MyLogger.log.error(Position + " Position does not exist of Symbol " + Symbol + " and Account" + AccountValue);
                Assert.fail();
            }
        }
        return index;
    }

    @Override
    public void ValidatePositionValues(String Symbol, String Position, String AccountValue, DataTable table)
    {
        int index = GetGridIndexValueOfPosition(Symbol, Position, AccountValue);
        ValidateListValues("PositionList",index,table);
    }


    public void ValidateLocateAtIndex(int index, DataTable table)
    {
        MobileElement element = ElementFinder.Instance().FindElement("LocateList", DriverFactory.driver);
        List<MobileElement> List1 = ElementFinder.Instance().GetElementList("LocateView", element);
        index = (index + 1) + index;
        List<Map<String, String>> data = table.asMaps(String.class, String.class);
        for (int i = 0; i < data.size(); i++)
        {
            GeneralHelper.Instance().Wait(5000);
            String elementText = ElementFinder.Instance.FindElement(data.get(i).get("Key"), List1.get(index)).getText();
            System.out.println(elementText);
            System.out.println(data.get(i).get("Key"));
            if (elementText.equals(data.get(i).get("Value")))
            {
                MyLogger.log.info(data.get(i).get("Key") + " Value " + data.get(i).get("Value") + " is validated");
            } else
            {
                MyLogger.log.error(data.get(i).get("Key") + " Value " + data.get(i).get("Value") + " is not validated");
                org.testng.Assert.fail();
            }
        }
    }

    @Override
        public void ValidateListValues (String Listname,int index, DataTable table)
        {
            int count=0;
            List<MobileElement> List = ElementFinder.Instance().GetElementList(Listname, DriverFactory.driver);
            List<Map<String, String>> data = table.asMaps(String.class, String.class);
            for (int i = 0; i < data.size(); i++)
            {
                GeneralHelper.Instance().Wait(5000);
                String elementText = ElementFinder.Instance.FindElement(data.get(i).get("Key"), List.get(index)).getText();
                if (elementText.equals(data.get(i).get("Value")))
                {
                    MyLogger.log.info(data.get(i).get("Key") + " Value " + data.get(i).get("Value") + " is validated in " +Listname);
                }
                else
                {
                    MyLogger.log.error(data.get(i).get("Key") + " Value " + data.get(i).get("Value") + " is not validated in "+Listname);
                    count++;
                }
            }
            if(count > 0)
            {
                MyLogger.log.error("Validation error occured");
                Assert.fail();
            }
        }

    @Override
    public void ClickOnPositionMenuIcon(String Symbol, String Position, String AccountValue)
    {
        int index = AndroidList.Instance().GetGridIndexValueOfPosition(Symbol, Position, AccountValue);
        List<MobileElement> List = ElementFinder.Instance().GetElementList("PositionList", DriverFactory.driver);
        ElementFinder.Instance().FindElement("PositionMenuIcon", List.get(index)).click();
    }

    @Override
    public void SelectPositionMenuOption(String Option)
    {
        boolean Success = false;
        List<MobileElement> List = ElementFinder.Instance().GetElementList("PositionMenuOptionText", DriverFactory.driver);
        for (MobileElement e : List)
        {
            if (e.getText().equals(Option))
            {
                GeneralHelper.Instance().TapOnElement(e);
                Success=true;
                break;
            }
        }
        if(!Success)
        {
            MyLogger.log.error("Either you have given an invalid Option Given "+Option);
            Assert.fail();
        }
    }

}





