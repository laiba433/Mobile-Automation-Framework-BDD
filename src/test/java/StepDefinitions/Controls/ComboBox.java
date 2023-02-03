package StepDefinitions.Controls;

import com.appium.Main.Factory.ComboBoxFactory;
import com.appium.Main.Logger.MyLogger;
import io.cucumber.java.en.Given;

public class ComboBox
{
    public static ComboBox Instance=null;
    public static ComboBox Instance() {
        {
            if (Instance == null) {
                Instance = new ComboBox();

            }
            return Instance;
        }
    }

    @Given("Select ComboBoxValue {string} {string}")
    public void SelectComboBoxValue(String ComboBoxType, String Value)
    {
        ComboBoxFactory.AppiumCombBox.SelectComboValue(ComboBoxType,Value);
    }

    @Given("Select Multiple CheckBoxValues {string} {string}")
    public void FillMultipleTextBox(String ComboBoxType, String Value){

        String[] ComboBoxTypeList = ComboBoxType.split(",");
        String[] ValueList = Value.split(",");

        if(ComboBoxTypeList.length==ValueList.length)
        {
            for(int i=0;i<ComboBoxTypeList.length;i++)
            {
                ComboBoxFactory.AppiumCombBox.SelectComboValue(ComboBoxType,Value);
            }
        }

        else
        {
            MyLogger.log.error("Input size of Keys "+ComboBoxTypeList+" are different from input size of Values" +ValueList+ "Give value against each key");
        }
    }
}
