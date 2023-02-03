package TestRunner;

import io.cucumber.testng.CucumberOptions;


//For different testcases in iOS

@CucumberOptions(
      features = {"src/test/java/Features/1test.feature"},
        glue = {"StepDefinitions"},
        plugin = {"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"},
        tags = "@reg"
)
public class iOSRunnerTest extends BaseRunner {

}

