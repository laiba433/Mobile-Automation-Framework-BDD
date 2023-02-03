package com.appium.Main.JsonClasses;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class ReadFile {

   public static FileReader GetJsonFile(String FileName){
       FileReader fileReader = null;
       try {
           fileReader = new FileReader(FileName);
       } catch (FileNotFoundException e) {
           throw new RuntimeException(e);
       }
       return fileReader;
   }

}
