package TestRunner;

import StepDefinitions.ClassSpecificMethods.Login;
import StepDefinitions.Controls.General;
import com.appium.Main.Common.Configuration;
import com.appium.Main.Factory.ListFactory;
import com.appium.Main.Factory.DriverFactory;
import com.appium.Main.Interface.IDriverGeneralMethods;
import com.appium.Main.JsonClasses.App;
import com.appium.Main.JsonClasses.GlobalVariables;
import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Hooks {

  @Before
   public void setup()
  {
       App.ParseAppJson("src/test/resources/JsonFiles/App.json");
       Configuration.InitializeGlobalVariables();
       Login.Instance().loginvBate(GlobalVariables.Username,GlobalVariables.Password);
  }

  @After
    public void quit()
  {
      General.Instance().Wait(5000);
      DriverFactory.driver.quit();
  }
}
