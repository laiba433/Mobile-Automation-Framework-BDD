package StepDefinitions.ClassSpecificMethods;

import StepDefinitions.Controls.Button;
import StepDefinitions.Controls.MobileAlerts;
import StepDefinitions.Controls.Textbox;
import com.appium.Main.Factory.DriverFactory;
import com.appium.Main.JsonClasses.App;
import com.appium.Main.JsonClasses.GlobalVariables;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class Login
{
    public static Login Instance=null;
    public static Login Instance() {
        {
            if (Instance == null) {
                Instance = new Login();

            }
            return Instance;
        }
    }

    @When("I add Verification code{string}")
    public void iAddVerificationCode(String Code)
    {
        String Result= DriverFactory.appium.SubmitVerificationCode(Code);
        Assert.assertEquals(Result,GlobalVariables.Success);
    }

    @Given("Login vBate with {string} {string}")
    public void loginvBate(String Username, String Password)
    {
        if(App.key.equals("iOS"))
        {
            MobileAlerts.Instance().AcceptAlert();
        }
        Textbox.Instance().FillMultipleTextBox("Username,Password" ,""+Username+","+Password+"");
        Button.Instance().ClickButton("LoginButton");
        iAddVerificationCode("123456");
        Button.Instance().ClickButton("VerifyButton");

    }
}
