package StepDefinitions.Controls;

import com.appium.Main.Common.ElementFinder;
import com.appium.Main.Factory.ListFactory;
import com.appium.Main.Factory.DriverFactory;
import com.appium.Main.JsonClasses.App;
import com.appium.Main.Logger.MyLogger;
import io.appium.java_client.MobileElement;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import org.testng.Assert;

public class ListControl
{
    public static ListControl Instance=null;
    public static ListControl Instance() {
        {
            if (Instance == null) {
                Instance = new ListControl();

            }
            return Instance;
        }
    }

    @Then("Validate the following values of {string} at index {int}")
    public void ValidateListValues (String ListName, int index, DataTable table)
    {
        ListFactory.AppiumList.ValidateListValues(ListName,index,table);
    }

    public String GetValueFromList(String List , String ElementKey,int index) {

           String FoundText = "";
            MobileElement element = null;
            java.util.List<MobileElement> ElementList = null;
            try {
                if (App.key.equals("Android"))
                {
                    ElementList = ElementFinder.GetElementList(List, DriverFactory.driver);
                    element = ElementFinder.FindElement(ElementKey, ElementList.get(index));
                    FoundText = element.getText();
                } else
                {
                    ElementList = ElementFinder.GetElementList(ElementKey, DriverFactory.driver);
                    FoundText = ElementList.get(index).getText();
                }

                MyLogger.log.info("Text set for " + ElementKey + " element is " + FoundText);
            }
            catch (Exception ex)
            {
                if (element == null)
                {
                    MyLogger.log.error("Element is null, Unable to get the text of " + ElementKey);
                }
                else if (ElementList.isEmpty())
                {
                    MyLogger.log.error("List is empty, Unable to get the text of " + ElementKey);
                }
                else
                {
                    MyLogger.log.error(ex);
                    MyLogger.log.error("Unable to get the text of " + ElementKey);

                }
                Assert.fail();
            }
        return FoundText;
    }
}
