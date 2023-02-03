package StepDefinitions.Controls;

import StepDefinitions.ClassSpecificMethods.Login;
import com.appium.Main.Common.ElementFinder;
import com.appium.Main.Common.GeneralHelper;
import com.appium.Main.Factory.DriverFactory;
import com.appium.Main.JsonClasses.GlobalVariables;
import com.appium.Main.Logger.MyLogger;
import io.appium.java_client.MobileElement;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.testng.Assert;

import java.util.Map;

public class General {
public static General Instance=null;
public static General Instance(){
    {
        if(Instance== null)
        {
            Instance = new General();

        }
        return Instance;
    }
}
    @And("Wait for {int}")
    public void Wait(int TimeInMilliseconds)
    {
        GeneralHelper.Instance().Wait(TimeInMilliseconds);
    }

    @And("Wait for Visibilty till {int}")
    public  void WaitForVisibility(int TimeInSeconds, String ElementKey)
    {
        GeneralHelper.Instance().WaitForVisibility(TimeInSeconds,ElementKey);
    }

    @And("Wait Until Alert is present")
    public  void WaitUntilAlertIsPresent(){
       GeneralHelper.Instance().WaitUntilAlertIsPresent();
    }

    @And("Log Message {string} {string}")
    public void LogMessage(String LoggingType, String Message)
    {
      GeneralHelper.Instance().LogMessage(LoggingType,Message);
    }

    @Then("Check the Presence of {string}")
    public void CheckPresenceOfElement(String Key)
    {
      GeneralHelper.Instance().CheckPresenceOfElement(Key);
    }
    @And("Close App")
    public void closeApp()
    {
       GeneralHelper.Instance().closeApp();
    }
    @And("Launch App")
    public void launchApp()
    {
      GeneralHelper.Instance().launchApp();
    }

    //Need Changes in -> CheckIfTheUserIsLogin()
    @Given("Check if the user is login")
    public void CheckIfTheUserIsLogin()
    {

        if(DriverFactory.driver !=null)
        {
            try
            {

                MobileElement element = ElementFinder.Instance().FindElement("NavBar", DriverFactory.driver);


                if (element.isDisplayed())
                {
                  MyLogger.log.info("User is logged in");

                }
            }
            catch(Exception ex)
            {
                try
                {

                    MobileElement element = ElementFinder.Instance().FindElement("SessionTimeoutMessage", DriverFactory.driver);
                    String text = element.getText();
                    if (text.equals("Your Session has timed out. Please login again."))
                    {
                        Button.Instance().ClickButton("SessionTimeoutOkButton");
                        Login.Instance().loginvBate(GlobalVariables.Username,GlobalVariables.Password);

                    }

                }

                catch(Exception e)
                {
                    MyLogger.log.info("You are logged out due to some reason, logging in app again ");
                    launchApp();
                    Login.Instance().loginvBate(GlobalVariables.Username,GlobalVariables.Password);
                }
            }
        }

        else
        {
            MyLogger.log.info("Driver is null, need to initalize driver and logging app again");
            //factory.getDriver();
            Login.Instance().loginvBate(GlobalVariables.Username,GlobalVariables.Password);
        }
    }
    @Then("Is Control {string} enabled")
    public boolean IsControlEnabled(String Key)
    {
      boolean IsEnabled = IsControlEnabled(Key);
      return IsEnabled;

    }

    @And("Tap On Control {string} ")
    public void TapOnElement(String Key)
    {
        MobileElement element = ElementFinder.Instance().FindElement(Key, DriverFactory.driver);
        GeneralHelper.Instance().TapOnElement(element);
    }

    @And("Validate Toast Message: {string}")
    public void ValidateToastMessage(String value)
    {

        MobileElement message = ElementFinder.Instance().FindElement("ToastMessage", DriverFactory.driver);
        System.out.println("Toast message : " + message.getText());
        if(message.getText().equals(value))
        {
            MyLogger.log.info("Toast message is validated");
        }
        else
        {
            MyLogger.log.error("Message not Validated, message is "+ message.getText()+" not same as "+value);
            Assert.fail();
        }

    }

    @Given("Add following entries in TextBox: {string} and Click Button: {string}")
    public void FillMultipleSymbols(String TextBoxKey, String ButtonKey, DataTable table) {
        java.util.List<Map<String, String>> data = table.asMaps(String.class, String.class);
        if (data.isEmpty()) {
            MyLogger.log.info("symbols list not found");
        }
        else
        {
            for (int i = 0; i < data.size(); i++) {
                General.Instance().Wait(1000);
                Textbox.Instance().FillTextBox(TextBoxKey, data.get(i).get("Value"));
                Button.Instance().ClickButton(ButtonKey);
                MyLogger.log.info("Entry : "+data.get(i).get("Value")+" Added");
            }
        }
    }

}
