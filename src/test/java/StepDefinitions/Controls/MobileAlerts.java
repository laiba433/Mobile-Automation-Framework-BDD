package StepDefinitions.Controls;

import com.appium.Main.Factory.DriverFactory;
import com.appium.Main.Logger.MyLogger;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.openqa.selenium.Alert;
import org.testng.Assert;

public class MobileAlerts {
    public static MobileAlerts Instance=null;
    public static MobileAlerts Instance()
    {
        {
            if (Instance == null)
            {
                Instance = new MobileAlerts();
            }
            return Instance;
        }
    }
    @Then("Accept Alert")
    public void AcceptAlert()
    {
        try
        {
            General.Instance().WaitUntilAlertIsPresent();
            Alert alert = DriverFactory.driver.switchTo().alert();
            alert.accept();
        }
        catch(Exception ex)
        {
            MyLogger.log.error("Unable to perform accept action on alert due to following exception");
            MyLogger.log.error(ex);
            Assert.fail();
        }
    }

    @Then("Get Alert Text")
    public String GetAlertText()
    {
        try
        {
            General.Instance().WaitUntilAlertIsPresent();
            Alert alert = DriverFactory.driver.switchTo().alert();
            String text = alert.getText();
            return text;
        }
        catch(Exception ex)
        {
            MyLogger.log.error("Unable to get the text of alert box due to following exception");
            MyLogger.log.error(ex);
            Assert.fail();
        }
        return "";
    }

    @Given("Validate Alert Text {string}")
    public boolean ValidateAlertText(String text)
    {
        String GetText= GetAlertText();
        if(GetText.equals(text))
        {
            return true;
        }
        return false;
    }

    @Then("Dismiss Alert")
    public void DismissAlert()
    {
        try
        {
            General.Instance().WaitUntilAlertIsPresent();
            Alert alert = DriverFactory.driver.switchTo().alert();
            alert.dismiss();
        }
        catch(Exception ex)
        {
            MyLogger.log.error("Unable to perform dismiss action on alert due to following exception");
            MyLogger.log.error(ex);
            Assert.fail();
        }

    }

}
