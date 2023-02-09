package StepDefinitions.Controls;

import com.appium.Main.Common.ElementFinder;
import com.appium.Main.Factory.DriverFactory;
import com.appium.Main.Logger.MyLogger;
import io.appium.java_client.MobileElement;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.testng.Assert;

public class Labels
{
    public static Labels Instance=null;
    public static Labels Instance()
    {
        {
            if (Instance == null) {
                Instance = new Labels();

            }
            return Instance;
        }
    }

    @And("Get text of label {string}")
    public String GetText(String Key)
    {
        MobileElement element =null;
        String FoundText="";
        try
        {
            element = ElementFinder.FindElement(Key, DriverFactory.driver);
            FoundText=element.getText();
            MyLogger.log.info("Text set for "+Key+" element is "+FoundText);
        }
        catch(Exception ex)
        {
            if(element == null)
            {
                MyLogger.log.error("Element is null, Unable to get the text of label " +Key);

            }
            else
            {
                MyLogger.log.error(ex);
                MyLogger.log.error( "Unable to get the text of label " +Key);

            }
            Assert.fail();
        }
        return FoundText;
    }

    @Then("Validate text of label key {string} value {string}")
    public void ValidateValue(String Key, String value)
    {
        String LabelValue= GetText(Key);
        if(LabelValue.equals(value))
        {
            MyLogger.log.info("Value is validated");
        }
        else
        {
            MyLogger.log.error("Value not Validated correctly, label value is "+ LabelValue+" not same as "+value);
            Assert.fail();
        }
    }
}
