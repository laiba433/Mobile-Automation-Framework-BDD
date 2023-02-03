import com.appium.Main.Factory.DriverFactory;
import com.appium.Main.JsonClasses.App;
import com.appium.Main.JsonClasses.GlobalVariables;

public class main
{
    public static void main(String args[]) throws Exception
    {
        GlobalVariables.Instance().SetGlobalVariables();
        App.ParseAppJson("C:\\Users\\laiba.aftab\\Desktop\\Mobile-Automation-Framework\\Mobile-Automation-Framework\\src\\test\\resources\\JsonFiles\\App.json");
        DriverFactory.getDriver();


    }
}