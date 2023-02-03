package com.appium.Main.JsonClasses;

import com.appium.Main.Logger.MyLogger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;

public class JsonHelper {

    public static JsonHelper Instance= null;

    public static  JSONArray GetJsonArray(JSONObject obj, String Key)
    {
        JSONArray ja = (JSONArray) obj.get(Key);
        return ja;
    }

public static JSONObject ParseJson(FileReader reader)
{
    JSONParser jsonParser = new JSONParser();
    JSONObject obj;

    try
    {
        obj = (JSONObject) jsonParser.parse(reader);
    }

    catch (IOException e)
    {
        MyLogger.log.error(e);
        throw new RuntimeException(e);
    }

    catch (ParseException e)
    {
        MyLogger.log.error(e);
        throw new RuntimeException(e);
    }

    return obj;
}

}
