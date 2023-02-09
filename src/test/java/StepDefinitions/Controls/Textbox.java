package StepDefinitions.Controls;

import com.appium.Main.Common.ElementFinder;
import com.appium.Main.Factory.DriverFactory;
import com.appium.Main.JsonClasses.App;
import com.appium.Main.Logger.MyLogger;
import io.appium.java_client.MobileElement;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.openqa.selenium.Keys;
import org.testng.Assert;

import java.util.Map;

public class Textbox {
    public static Textbox Instance=null;
    public static Textbox Instance()
    {
        {
            if (Instance == null) {
                Instance = new Textbox();

            }
            return Instance;
        }
    }

    @Given("Fill Values in Multiple Text Boxes as following table")
    public void FillMultipleTextBoxes(DataTable table)
    {
        java.util.List<Map<String, String>> data = table.asMaps(String.class, String.class);
        for (int i = 0; i < data.size(); i++)
        {
            General.Instance().Wait(5000);
            FillTextBox(data.get(i).get("Key"), data.get(i).get("Value"));
        }
    }
    @Given("Fill TextBox {string} {string}")
    public void FillTextBox(String Key, String Value){
        MobileElement element=null;
        try
        {
        General.Instance().Wait(2000);
            element = ElementFinder.FindElement(Key, DriverFactory.driver);
            element.sendKeys(Value);
            if (App.key.equals("iOS"))
            {
                element.sendKeys(Keys.ENTER);
            }
            else
            {
                Keyboard.Instance().HideKeyboard();
            }
        }
       catch(Exception ex)
        {
         if(element == null)
         {
             MyLogger.log.error(Key+" Element is null, Can not fill the value" +Value+ "in the textbox");

         }
         else
         {
             MyLogger.log.error(ex);
             MyLogger.log.error("Can not fill the value" +Value+ "in the textbox "+Key);

         }
            Assert.fail();
        }
    }

    @Given("Fill Multiple Textboxes {string} {string}")
    public void FillMultipleTextBox(String Keys, String Value){
        General.Instance().Wait(2000);
        String[] KeyList = Keys.split(",");
        String[] ValueList = Value.split(",");

       if(KeyList.length==ValueList.length)
       {
           for(int i=0;i<KeyList.length;i++)
           {
               FillTextBox(KeyList[i], ValueList[i]);
           }
       }

       else
       {
           MyLogger.log.error("Input size of Keys "+KeyList+" are different from input size of Values" +ValueList+ "Give value against each key");
           Assert.fail();
       }
    }
    @And("Get text of Textbox {string}")
    public String GetTextOfTextbox(String Key)
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
                MyLogger.log.error("Element is null, Unable to get the text of " +Key);

            }
            else
            {
                MyLogger.log.error(ex);
                MyLogger.log.error( "Unable to get the text of " +Key);

            }
            Assert.fail();
        }
        return FoundText;
    }

    @Then("Validate Textbox Value {string} {string}")
    public void ValidateTextboxValue(String Key, String value)
    {
       String TextBoxValue= GetTextOfTextbox(Key);
       if(TextBoxValue.equals(value))
       {
           MyLogger.log.info("Value is validated");
       }
       else
       {
           MyLogger.log.error("Value not Validated correctly, Textbox value is "+ TextBoxValue+" not same as "+value);
           Assert.fail();
       }
    }
    @Given("Clear textbox {string}")
    public void ClearTextBox(String Key)
    {
        try
        {
            MobileElement element = ElementFinder.FindElement(Key, DriverFactory.driver);
            element.click();
            element.clear();
        }
        catch(Exception ex)
        {
            MyLogger.log.error("Unable to clear the textbox due to following exception"+ ex);
            Assert.fail();
        }
    }
}
