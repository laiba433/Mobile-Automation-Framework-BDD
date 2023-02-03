package com.appium.Main.JsonClasses;

import com.appium.Main.Logger.MyLogger;
import org.json.simple.JSONObject;

import java.io.FileReader;
import java.io.IOException;

public class App extends ReadFile {
    public static String key;
    public static String PlatformName;
    public static String PlatformVersion;
    public static String DeviceName;
    public static App AppDetails;

    public static App GetAppDetails(String Key, String DeviceName, String PlatformVersion, String PlatformName) {
        if (AppDetails == null)
        {
            AppDetails = new App(Key, DeviceName, PlatformVersion, PlatformName);
        }
        return AppDetails;
    }
public App(String Key, String DeviceName, String PlatformVersion,String PlatformName)
{
    this.key = Key;
    this.DeviceName=DeviceName;
    this.PlatformVersion=PlatformVersion;
    this.PlatformName=PlatformName;

}
    public static void ParseAppJson(String AppFilePath) {

        FileReader reader= GetJsonFile(AppFilePath);
        JSONObject obj= JsonHelper.ParseJson(reader);
        key= obj.get("Key").toString();
        PlatformName= obj.get("Platform_Name").toString();
        PlatformVersion= obj.get("Platform_Version").toString();
        DeviceName= obj.get("DeviceName").toString();
    }

}
