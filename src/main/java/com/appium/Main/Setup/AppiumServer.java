package com.appium.Main.Setup;
import com.appium.Main.Logger.MyLogger;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecuteResultHandler;
import org.apache.commons.exec.DefaultExecutor;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
public class AppiumServer
{
    public static String query= "call start cmd.exe /k C:\\Program Files\\nodejs\\node.exe C:\\Users\\laiba.aftab\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js --address 0.0.0.0 --port 4723\n";
    public static AppiumDriverLocalService service;
   // public static CommandLine command = new CommandLine(query);
    //    public static String NODE_PATH= "C:\\Program Files\\nodejs\\node.exe";
//    public static String APPIUM_PATH= "C:\\Users\\laiba.aftab\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js";
//
    public static String NODE_PATH= "/opt/homebrew/Cellar/node/17.4.0/bin/node";
    public static String APPIUM_PATH= "/opt/homebrew/lib/node_modules/appium/build/lib/main.js";

    public static void startServer() {

//        AppiumServiceBuilder builder = new AppiumServiceBuilder();
//        //    builder.usingAnyFreePort();
//        // Tell builder where node is installed. Or set this path in an environment variable named NODE_PATH
//        builder.usingDriverExecutable(new File(NODE_PATH));
//        // Tell builder where Appium is installed. Or set this path in an environment variable named APPIUM_PATH
//        builder.withAppiumJS(new File(APPIUM_PATH));
//
//        builder.withIPAddress("0.0.0.0").usingPort(4723);
//
//        HashMap<String, String> environment = new HashMap();
//        environment.put("PATH", "/usr/local/bin:" + System.getenv("PATH"));
//        builder.withEnvironment(environment);
//        service = AppiumDriverLocalService.buildService(builder);
//        service.start();
//
        CommandLine command = new CommandLine("C:\\Program Files\\nodejs\\node.exe");
        command.addArgument(
                "C:\\Users\\laiba.aftab\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js",
                false);
        command.addArgument("--address", false);
        command.addArgument("127.0.0.1");
        command.addArgument("--port", false);
        command.addArgument("4723");
        command.addArgument("--full-reset", false);

                DefaultExecuteResultHandler resultHandler = new DefaultExecuteResultHandler();
        DefaultExecutor executor = new DefaultExecutor();
        executor.setExitValue(1);
        try {
            executor.execute(command, resultHandler);
            Thread.sleep(5000);
            System.out.println("Appium server started.");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }




    public static void stopServer()
    {
        service.stop();
        MyLogger.log.info("Appium has stopped");
    }
}



