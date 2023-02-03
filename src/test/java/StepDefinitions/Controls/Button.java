package StepDefinitions.Controls;

import com.appium.Main.Common.ElementFinder;
import com.appium.Main.Factory.DriverFactory;
import com.appium.Main.Logger.MyLogger;
import io.appium.java_client.MobileElement;
import io.cucumber.java.en.And;
import org.testng.Assert;

public class Button
{
    public static Button Instance=null;
    public static Button Instance()
    {
        {
            if(Instance== null)
            {
                Instance = new Button();
            }
            return Instance;
        }
    }
    @And("Get Button Text {string}")
    public String GetButtonText(String Key)
    {   String FoundText="";
        MobileElement element= ElementFinder.Instance().FindElement(Key, DriverFactory.driver);
        if(element == null)
        {
            Assert.fail();
        }
        else
        {
             FoundText= element.getText();
        }

       return FoundText;
    }

    @And("Click Button {string}")
    public void ClickButton(String Key)
    {
        MobileElement element= ElementFinder.Instance().FindElement(Key, DriverFactory.driver);
        element.click();
        MyLogger.log.info(Key+ "Button has been clicked");
    }

}
