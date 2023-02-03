package StepDefinitions.ClassSpecificMethods;

import StepDefinitions.Controls.*;
import com.appium.Main.Common.ElementFinder;
import com.appium.Main.Factory.DriverFactory;
import com.appium.Main.Manager.TextFileHelper;
import com.appium.Main.JsonClasses.App;
import com.appium.Main.Logger.MyLogger;
import io.appium.java_client.MobileElement;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class Order
{
    public static Order Instance=null;
    public static Order Instance() {
        {
            if (Instance == null)
            {
                Instance = new Order();

            }
            return Instance;
        }
    }

    @When("Action on Order Successfully Created pop up {string}")
    public void ActionOnOrderCreatedPopUp(String Action)
    {
      if(App.key.equals("iOS"))
        {
            if(Action.equals("Ok"))
            {
                MobileAlerts.Instance().AcceptAlert();
            }

            else
            {
                MyLogger.log.error("Invalid Action input "+Action+ " Use \"OK\" to Close the order Pop- Up");
                Assert.fail();
            }
        }
    }
    @Given("Add Multiple Symbols of this file {string} and verify Company Info")
    public void AddMultipleSymbols(String Filename)
    {
        ArrayList<String> list=null;
        list= TextFileHelper.GetArrayListFromFile(Filename);
        for(String s: list){
            try
            {
                General.Instance().Wait(5000);
                Textbox.Instance().FillTextBox("SymbolField", s);
                Button.Instance().ClickButton("AddSymbolIcon");
                General.Instance().Wait(3000);
                String text = Textbox.Instance().GetTextOfTextbox("CompanyInfoLabel");

                if (text.equals("N/A")) {
                    MyLogger.log.error("Company name is not present for " + s);
                    TextFileHelper.WriteEachLineInFile(s);
                }
            }
            catch(Exception ex)
            {
                MyLogger.log.error(s+ "issue while testing this symbol retest them");
            }
        }
    }

    @Given("Create Order:{string}, side:{string} of Symbol {string}")
    public void CreateOrder(String Order, String side, String Symbol, DataTable table){
        General.Instance().Wait(4000);
        Textbox.Instance().FillTextBox("SymbolField",Symbol);
        Button.Instance().ClickButton("AddSymbolIcon");
        java.util.List<Map<String, String>> data = table.asMaps(String.class, String.class);
        if(!data.isEmpty()) {

            switch (side)
            {
                case "Buy": {
                    Button.Instance().ClickButton("Buy");
                    break;
                }
                case "Sell": {
                    Button.Instance().ClickButton("Sell");
                    break;
                }
                case "ShortSell": {
                    Button.Instance().ClickButton("Short Sell");
                    break;
                }
                default: {
                    MyLogger.log.error("Given order side is not correct");
                }
            }

            switch (Order) {
                case "Advanced": {
                    Button.Instance().ClickButton("clickAdvanceButton");
                    break;
                }
            }

            for (int i = 0; i < data.size(); i++) {
                switch (data.get(i).get("ControlType"))
                {
                    case "TextField":
                    {
                        Textbox.Instance().FillTextBox(data.get(i).get("Key"), data.get(i).get("Value"));
                        break;
                    }

                    case "ComboBox":
                    {
                        ComboBox.Instance().SelectComboBoxValue(data.get(i).get("Key"), data.get(i).get("Value"));
                    }
                }
            }
            Button.Instance().ClickButton("SendOrderButton");
            General.Instance.Wait(2000);
            Button.Instance().ClickButton("ConfirmOrderModal");

        }
        else
        {
            MyLogger.log.info("DataTable is empty");
            Assert.fail();
        }
    }

    @Given("Cancel Order at Index: {int}")
    public void cancelOrder(int index)
    {
        General.Instance().Wait(2000);
        List<MobileElement> elementList= ElementFinder.Instance.GetElementList("viewDetailButton", DriverFactory.driver);
        elementList.get(index).click();
        Button.Instance().ClickButton("cancelButtononOrderModal");
        General.Instance().Wait(2000);
        if(App.key.equals("Android"))
        {
            General.Instance().Wait(2000);
            Button.Instance().ClickButton("okButtonOnAlertPopup");
        }
        else
        {
            MobileAlerts.Instance().AcceptAlert();
        }
    }

    @And("Click on list:{string} row at index:{int}")
    public void modifyOrderAgainstIndex(String listkey, int index) {
        General.Instance().Wait(2000);
        List<MobileElement> element= ElementFinder.Instance.GetElementList(listkey, DriverFactory.driver);
        element.get(index).click();

    }


    @Given("After status validation perform the action {string} {string}")
    public void afterStatusValidationPerformTheAction (String listKey, String ElementKey){
        String PreviousStatusText= ListControl.Instance().GetValueFromList("OrderList","OrderStatus",0);
        switch (PreviousStatusText)
        {
            case "Open":
                cancelOrder(0);
                General.Instance().Wait(4000);
                List<MobileElement> orderList = ElementFinder.Instance.GetElementList(listKey, DriverFactory.driver);
                MobileElement element = ElementFinder.Instance.FindElement(ElementKey, orderList.get(0));
                String foundtext = element.getText();
                String ActualText="Cancelled";
                if(foundtext.equals(ActualText))
                {
                    MyLogger.log.info("Value is validated");
                }
                else
                {
                    MyLogger.log.error("Value not Validated correctly, label value is "+ foundtext+" not same as "+"Cancelled");
                    Assert.fail();
                }
                break;

            case "Pending Cxl":
                cancelOrder( 0);
                break;

            case "Filled":
                cancelOrder(0);
                break;

            default:
                System.out.println("Predefined values are not matched");
        }
    }


}
