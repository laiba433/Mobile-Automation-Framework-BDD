package com.appium.Main.JsonClasses;

import com.appium.Main.Logger.MyLogger;
import io.appium.java_client.MobileElement;
import org.json.simple.JSONObject;
import org.openqa.selenium.By;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class ParseLocators extends ReadFile {

    public static String key;
    public static String locator;
    public static String value;

    public ParseLocators(String locator, String value) {
        this.locator = locator;
        this.value = value;
    }

    static FileReader reader;

    public static JSONObject GetLocatorObject(String key) throws IOException {
        JSONObject obj = null;
        try {
            if (App.key.equals("iOS")) {
                reader = GetJsonFile(GlobalVariables.iOSLocatorsFile);
            } else if (App.key.equals("Android")) {
                reader = GetJsonFile(GlobalVariables.AndroidLocatorsFile);
            } else {
                MyLogger.log.error(key + " File not found");
            }

            JSONObject jsonObject = JsonHelper.ParseJson(reader);
            obj = (JSONObject) jsonObject.get(key);
            reader.close();
        } catch (Exception ex) {
            MyLogger.log.error(ex);
        }
        return obj;
    }

    public static ParseLocators GetValues(String Key) {
        JSONObject jsonObject = null;
        try {
            jsonObject = GetLocatorObject(Key);
        } catch (Exception e) {
            MyLogger.log.error(Key + " Key is not found in " + App.key + " JSON file");
        }

        ParseLocators KeyObject = null;
        String locator = jsonObject.get("locator").toString();
        String value = jsonObject.get("value").toString();
        KeyObject = new ParseLocators(locator, value);
        return KeyObject;
    }

    public static By GetLocator(String Key) {
        By Locator = null;
        ParseLocators KeyObject = ParseLocators.GetValues(Key);
        try {

            switch (KeyObject.locator)
            {
                case "Name": {
                    Locator = By.name(KeyObject.value);
                    break;
                }
                case "XPath": {
                    Locator = By.xpath(KeyObject.value);
                    break;
                }
                case "Class": {
                    Locator = By.className(KeyObject.value);
                    break;
                }
                case "Id": {
                    Locator = By.id(KeyObject.value);
                    break;
                }
                default: {
                    MyLogger.log.error("Given Locate Searching Strategy i.e. " + KeyObject.locator + "is not defined in the scope");
                }

            }
        }
        catch (Exception ex)
        {
            MyLogger.log.error(ex);
        }
        return Locator;
    }
}