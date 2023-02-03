package com.appium.Main.DriverClasses;

import com.appium.Main.Factory.DriverFactory;
import com.appium.Main.Interface.IComboBox;
import com.appium.Main.JsonClasses.GlobalVariables;
import com.appium.Main.JsonClasses.JsonHelper;
import com.appium.Main.Logger.MyLogger;
import io.appium.java_client.MobileElement;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.testng.Assert;


import java.io.FileReader;

import static com.appium.Main.JsonClasses.ReadFile.GetJsonFile;

public class iOSComboBox implements IComboBox
{
    public static iOSComboBox Instance=null;
    public static iOSComboBox Instance(){
        {
            if(Instance== null)
            {
                Instance = new iOSComboBox();

            }
            return Instance;
        }
    }

    public MobileElement ComboElement= null;
    public int i =0;
    public void OpenComboBox(JSONArray ja)
    {

        while(i<ja.size())
        {
            String val= ja.get(i).toString();
            System.out.println(val);
            try
            {
                ComboElement = (MobileElement) DriverFactory.driver.findElementByXPath("\t\n//XCUIElementTypeButton[@name=\"" + val + "\"]");
                if (ComboElement != null)
                {
                    ComboElement.click();
                    break;
                }
            }
            catch (Exception exception)
            {
                i++;
                if(i< ja.size() && i != ja.size())
                {
                    OpenComboBox(ja);
                }
                else
                {
                    i=0;
                    MyLogger.log.error("Element not found");
                    Assert.fail();
                }
            }
        }
        i=0;
    }

public void SelectComboValue(String ComboBoxType, String Value){
    FileReader reader= GetJsonFile(GlobalVariables.ComboBoxValueFile);
    JSONObject obj= JsonHelper.ParseJson(reader);
    JSONArray arr= JsonHelper.GetJsonArray(obj,ComboBoxType) ;
    iOSComboBox.Instance().OpenComboBox(arr);
    MobileElement element = null;
    try
    {
        element = (MobileElement) DriverFactory.driver.findElementByXPath("\t\n//XCUIElementTypeButton[@name=\"" + Value + "\"]");
        element.click();
    }
    catch(Exception ex)
    {
        if(element == null)
        {
            MyLogger.log.error("Element is null, Unable to select the "+ComboBoxType+ " Combo Box value "+Value );
        }
        else
        {
            MyLogger.log.error("Unable to select the "+ComboBoxType+ " Combo Box value "+Value+" due to following exception" );
            MyLogger.log.error(ex);
        }
        Assert.fail();
    }
}
}
