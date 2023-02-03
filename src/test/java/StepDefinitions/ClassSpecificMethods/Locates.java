package StepDefinitions.ClassSpecificMethods;

import com.appium.Main.DriverClasses.AndroidList;
import com.appium.Main.Common.ElementFinder;
import com.appium.Main.Factory.DriverFactory;
import com.appium.Main.JsonClasses.App;
import io.appium.java_client.MobileElement;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;

import java.util.List;

public class Locates
{
    public static Locates Instance=null;
    public static Locates Instance() {
        {
            if (Instance == null) {
                Instance = new Locates();

            }
            return Instance;
        }
    }
@Then("Validate following values of Locate Watch at index {int}")
    public void ValidateLocateRowValues(int index, DataTable table)
    {

        if(App.key.equals("Android"))
        {
            AndroidList.Instance().ValidateLocateAtIndex(index,table);
        }

    }

    @Then("Aquire Locate at index {int}")
    public void AquireLocate(int index)
    {
        MobileElement element = ElementFinder.Instance().FindElement("LocateList", DriverFactory.driver);
        List<MobileElement> List1 = ElementFinder.Instance().GetElementList("LocateView", element);
        index = (index+1)+index;
        List1.get(index).click();

    }
}
