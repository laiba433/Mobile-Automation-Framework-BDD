package TestRunner;

import StepDefinitions.ClassSpecificMethods.Login;
import StepDefinitions.Controls.General;
import com.appium.Main.Common.Configuration;
import com.appium.Main.Factory.ComboBoxFactory;
import com.appium.Main.Factory.ListFactory;
import com.appium.Main.Factory.DriverFactory;
import com.appium.Main.JsonClasses.App;
import com.appium.Main.JsonClasses.GlobalVariables;
import com.appium.Main.Manager.PropertyManager;
import com.appium.Main.Setup.Capabilities;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import org.testng.annotations.*;

//For Same testcases in iOS and Android devices

public class BaseRunner extends AbstractTestNGCucumberTests
{
        @Parameters({"Username","Password","Key","DeviceName","PlatformVersion","PlatformName","Udid"})
        @BeforeSuite(alwaysRun = true)
        public void setup(String Username, String Password,String Key, String DeviceName, String PlatformVersion,String PlatformName,String Udid)
        {
            Configuration.InitializeAppDetails(Key, DeviceName, PlatformVersion, PlatformName);
            Configuration.InitializeGlobalVariables();
            Configuration.InitializeProperties(Udid);
            Login.Instance().loginvBate(Username,Password);

        }



    @AfterSuite(alwaysRun = true)
        public void quit()
        {
           General.Instance().Wait(5000);
            DriverFactory.driver.quit();
            Capabilities.caps=null;
        }
    }

