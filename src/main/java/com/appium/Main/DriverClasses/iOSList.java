package com.appium.Main.DriverClasses;

import com.appium.Main.Common.ElementFinder;
import com.appium.Main.Common.GeneralHelper;
import com.appium.Main.Factory.DriverFactory;
import com.appium.Main.Interface.IList;
import com.appium.Main.Logger.MyLogger;
import io.appium.java_client.MobileElement;
import io.cucumber.datatable.DataTable;
import org.junit.Assert;

import java.util.List;
import java.util.Map;

public class iOSList implements IList
{
    public static iOSList Instance=null;
    public static iOSList Instance(){
        {
            if(Instance== null)
            {
                Instance = new iOSList();

            }
            return Instance;
        }
    }

    @Override
    public int GetGridIndexValueOfPosition(String Symbol, String Position, String AccountValue) {

        GeneralHelper.Wait(5000);
        List<MobileElement> SymbolList = ElementFinder.GetElementList("PositionSymbolLabel", DriverFactory.driver);
        List<MobileElement> PositionStatusList = ElementFinder.GetElementList("PositionStatusLabel", DriverFactory.driver);

        if (SymbolList.size() != 0)
        {
            MyLogger.log.info("There are " + SymbolList.size() + " Positions against " + AccountValue + " account");
        } else
        {
            MyLogger.log.info("There is no position against " + AccountValue + " account, Cannot find the" + Symbol + "Position as list is empty");
            Assert.fail();
        }
        int index = -1;
        for (int i= 0; i<SymbolList.size();i++)
        {
            index++;
            if (SymbolList.get(i).getText().equals(Symbol) && PositionStatusList.get(i).getText().equals(Position)) {
                MyLogger.log.info("Index of Position " + Symbol + " against " + AccountValue + " is " + index);
                break;
            }
            else if (index == SymbolList.size() - 1)
            {
                MyLogger.log.error(Position + " Position does not exist of Symbol " + Symbol + " and Account" + AccountValue);
                Assert.fail();
            }
        }
        return index;
    }

    @Override
    public void ValidatePositionValues(String Symbol, String Position, String AccountValue, DataTable table) {
        int index = GetGridIndexValueOfPosition(Symbol, Position, AccountValue);
        ValidateListValues("PositionList",index,table);
    }
    @Override
    public void ValidateListValues (String ListName, int index, DataTable table)
    {
        int count =0;
        List<Map<String, String>> data = table.asMaps(String.class, String.class);
        for (int i = 0; i < data.size(); i++) {
            GeneralHelper.Wait(5000);
          List<MobileElement> list= ElementFinder.GetElementList(data.get(i).get("Key"), DriverFactory.driver);
            String elementText = (list.get(index)).getText();
            if (elementText.equals(data.get(i).get("Value")))
            {
                MyLogger.log.info(data.get(i).get("Key") + " Value " + data.get(i).get("Value") + " is validated in " +ListName);
            }
            else
            {
                MyLogger.log.error(data.get(i).get("Key") + " Value " + data.get(i).get("Value") + " is not validated in "+ListName);
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
        int index = iOSList.Instance().GetGridIndexValueOfPosition(Symbol, Position, AccountValue);
        List<MobileElement> List = ElementFinder.GetElementList("PositionMenuIcon", DriverFactory.driver);
        List.get(index).click();
    }

    @Override
    public void SelectPositionMenuOption(String Option) {
        switch (Option)
        {
            case "Increase":
            {
                ElementFinder.FindElement("Increase", DriverFactory.driver).click();
                break;
            }
            case "Decrease":
            {
                ElementFinder.FindElement("Decrease", DriverFactory.driver).click();
                break;
            }
            case "Flatten":
            {
                ElementFinder.FindElement("Flatten", DriverFactory.driver).click();
                break;
            }
            default:
            {
                MyLogger.log.error("Invalid Option Given "+Option);
                Assert.fail();
            }
        }
    }

}
