package StepDefinitions.ClassSpecificMethods;

import com.appium.Main.Factory.ListFactory;
import com.appium.Main.Logger.MyLogger;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.testng.Assert;

import java.util.List;

public class Position
{
    public static Position Instance=null;
    public static Position Instance() {
        {
            if (Instance == null)
            {
                Instance = new Position();

            }
            return Instance;
        }
    }
    @Then("Get index of position of Symbol {string}, Position {string} and Account {string}")
    public void GetGridIndexValue(String Symbol,String Position, String AccountValue)
    {
        ListFactory.AppiumList.GetGridIndexValueOfPosition(Symbol,Position,AccountValue);
    }
    @Then("Validate Values of Position List Symbol {string}, Position {string} and Account {string}")
    public void ValidatePositionRowValues(String Symbol,String Position, String AccountValue, DataTable table)
    {
        ListFactory.AppiumList.ValidatePositionValues(Symbol,Position,AccountValue,table);
    }

    @Then("Click on menu icon on Position Screen of Symbol {string}, Position {string} and Account {string}")
    public void ClickOnPositionMenuIcon(String Symbol,String Position, String AccountValue){
       try
       {
           ListFactory.AppiumList.ClickOnPositionMenuIcon(Symbol,Position,AccountValue);
       }
       catch(Exception ex)
       {
           MyLogger.log.error(ex);
           Assert.fail();
       }
    }

    @And("Select Position Menu Option {string}")
    public void SelectPositionMenuOption(String Option)
    {
        ListFactory.AppiumList.SelectPositionMenuOption(Option);
    }
}
