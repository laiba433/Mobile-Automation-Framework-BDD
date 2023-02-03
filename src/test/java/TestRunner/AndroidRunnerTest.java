package TestRunner;

import io.cucumber.testng.CucumberOptions;

//For different testcases in Android
@CucumberOptions(
        features = {"src/test/java/Features/1test.feature","src/test/java/Features/login.feature"},
        glue = {"StepDefinitions"},
        plugin = {"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"},
        tags = "@reg"
)
public class AndroidRunnerTest extends BaseRunner {

}

