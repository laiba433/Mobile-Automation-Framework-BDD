package com.appium.Main.Manager;

import com.appium.Main.Logger.MyLogger;
import com.google.common.io.FileBackedOutputStream;

import java.io.*;
import java.util.Properties;

public class PropertyManager
{

        private static Properties props = new Properties();

        public Properties getProps(String Filename)  {
            InputStream is = null;

            if(props.isEmpty()){
                try{
                    MyLogger.log.info("loading config properties");
                    is = getClass().getClassLoader().getResourceAsStream(Filename);
                    props.load(is);
                }
                catch (Exception e) {
                    e.printStackTrace();
                    MyLogger.log.fatal("Failed to load config properties. ABORT!!" + e.toString());

                }
                finally
                {
                    if(is != null){
                        try
                        {
                            is.close();
                        }
                        catch (IOException e)
                        {
                            throw new RuntimeException(e);
                        }
                    }
                }
            }
            return props;
        }

        public static void UpdateProperty(String Key,String Value,String Filename) {
            InputStream is = null;
            OutputStream out=null;
            if(props.isEmpty()){
                try{
                    is = PropertyManager.class.getClassLoader().getResourceAsStream(Filename);
                    out= new FileOutputStream(Filename);
                    props.load(is);
                    props.setProperty(Key, Value);
                    props.store(out, null);
                    MyLogger.log.info("Property Updated");
                }
                catch (Exception e) {
                    e.printStackTrace();
                    MyLogger.log.fatal("Failed to update config properties. ABORT!!" + e.toString());
                }
                finally
                {
                    if(is != null){
                        try
                        {
                            is.close();
                        } catch (Exception e)
                        {
                         MyLogger.log.error(e);
                        }
                    }
                }
            }
        }


}
