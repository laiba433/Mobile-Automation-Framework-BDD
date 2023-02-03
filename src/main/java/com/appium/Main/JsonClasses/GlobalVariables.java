package com.appium.Main.JsonClasses;

import com.appium.Main.Logger.MyLogger;
import org.json.simple.JSONObject;
import java.io.FileReader;
import java.io.IOException;

public class GlobalVariables
{
    public static String Username;
    public static String Password;
    public static String AppJsonFile;
    public static String ComboBoxValueFile;
    public static String iOSLocatorsFile;
    public static String AndroidLocatorsFile;
    public static String iOSServerURL;
    public static String AndroidServerURL;
    public static String Success;
    public static String Fail;
    public String GlobalVariablesFile= ""+System.getProperty("user.dir")+"/src/test/resources/JsonFiles/GlobalVariables.json";
    public static GlobalVariables Instance= null;

    public static GlobalVariables Instance()
    {
        {
            if(Instance== null)
            {
                Instance = new GlobalVariables();

            }

            return Instance;
        }
    }


    public void SetGlobalVariables()
    {
        try
        {
            FileReader reader= ReadFile.GetJsonFile(GlobalVariablesFile);
            JSONObject obj= JsonHelper.ParseJson(reader);
            Username= obj.get("Username").toString();
            Password= obj.get("Password").toString();
            AppJsonFile= obj.get("AppJsonFile").toString();
            ComboBoxValueFile= obj.get("ComboBoxValueFile").toString();
            iOSLocatorsFile= obj.get("iOSLocatorsFile").toString();
            AndroidLocatorsFile= obj.get("AndroidLocatorsFile").toString();
            iOSServerURL= obj.get("iOSServerURL").toString();
            AndroidServerURL= obj.get("AndroidServerURL").toString();
            Success= obj.get("Success").toString();
            Fail= obj.get("Fail").toString();

            try
            {
                reader.close();
            }
            catch (IOException e)
            {
                throw new RuntimeException(e);
            }

           MyLogger.log.info("Global Variables have been set");

        }

        catch(Exception ex)
        {
            MyLogger.log.error("Unable to set the values of Global Variable");
            MyLogger.log.error(ex);
        }


    }
}
