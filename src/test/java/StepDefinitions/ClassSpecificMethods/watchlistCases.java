package StepDefinitions.ClassSpecificMethods;

import com.appium.Main.Common.ElementFinder;
import com.appium.Main.Factory.DriverFactory;
import com.appium.Main.Logger.MyLogger;
import io.appium.java_client.MobileElement;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;

import java.util.List;
import java.util.Map;

public class watchlistCases {

    public static watchlistCases Instance = null;

    public static watchlistCases Instance() {
        {
            if (Instance == null) {
                Instance = new watchlistCases();

            }
            return Instance;
        }
    }

//Changes Required
    @And("Validate symbolORWatchlist Existance {string}")
    public void validateSymbolOrwatchlistExistance(String Value, DataTable table) {
        int fail = 0;
        java.util.List<Map<String, String>> data = table.asMaps(String.class, String.class);
        List<MobileElement> list1 = ElementFinder.Instance.GetElementList(Value, DriverFactory.driver);
        for (int d = 0; d < data.size(); d++)
        {
            String DataTableValue = data.get(d).get("key");
            for (int i = 0; i < list1.size(); i++) {
                MobileElement element = list1.get(i);
                String listValues = element.findElementByClassName("android.widget.TextView").getText();
                if (DataTableValue.equals(listValues))
                {
                    MyLogger.log.info(DataTableValue + " is exist  ");
                    break;
                }
                else if (i == list1.size() - 1)
                {
                    MyLogger.log.info(DataTableValue + " Symbol doesn't exist ");
                    fail++;
                }
            }
        }
    }
}