package StepDefinitions.ClassSpecificMethods;
import StepDefinitions.Controls.Button;
import StepDefinitions.Controls.General;
import StepDefinitions.Controls.Labels;
import com.appium.Main.Factory.DriverFactory;
import com.appium.Main.Logger.MyLogger;
import io.appium.java_client.MobileElement;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.openqa.selenium.By;
import org.testng.Assert;

public class Trade
{
    public static Trade Instance=null;
    public static Trade Instance()
    {
        {
            if (Instance == null)
            {
                Instance = new Trade();
            }
            return Instance;
        }
    }

    @Then("Validate Bid, Ask, and Last Price")
    public void validatebidaskvalueLastBidAsk()
    {
        int failure=0;
        String lastPrice= Labels.Instance().GetText("LastPrice").replaceAll("[$,]", "");
        String bidPrice=Labels.Instance().GetText("BidPrice").replaceAll("[$,]", "");
        String askPrice=Labels.Instance().GetText("AskPrice").replaceAll("[$,]", "");

        if(lastPrice.trim().matches("^\\d*$")|| !lastPrice.equals("$0.00"))
        {
            MyLogger.log.info("Last Price is Present");
        }
        else
        {
            MyLogger.log.error("Last Price is not showing, Last price field contain : "+lastPrice);
            failure++;
        }

        if(bidPrice.trim().matches("^\\d*$")|| !lastPrice.equals("$0.00"))
        {
            MyLogger.log.info("Bid Price is Present");

        }
        else
        {
            MyLogger.log.error("Bid Price is not showing, Bid price field contain : "+bidPrice);
            failure++;
        }
        if(askPrice.trim().matches("^\\d*$")|| !lastPrice.equals("$0.00"))
        {
            MyLogger.log.info("Ask Price Price is Present");
        }
        else
        {
            MyLogger.log.error("Ssk Price Price is not showing, Ask price field contain : "+askPrice);
            failure++;
        }
        if(failure != 0)
        {
            Assert.fail();
        }
    }

//Required Changes
    @And("validatemarketDataagreementacceptedornot {string} buttonVal {string} regularPrice {string}")
    public void validatemarketdataagreementacceptedornot(String marketagree, String buttonVal, String regularPrice) {

        MobileElement  marketDataNotAvaliable= (MobileElement) DriverFactory.driver.findElement(By.xpath(marketagree));
        String MDNA =marketDataNotAvaliable.getText();
        if (MDNA.equals("Retry")) {
            Button.Instance().ClickButton(buttonVal);
            General.Instance().Wait(5000);

            if (MDNA.equals("Retry"))
            {
                MyLogger.log.info("The market data agreement is not subscribed");
            }

        }
        else
        {
            MyLogger.log.info("Market data is already subscribed");
        }
    }
}



