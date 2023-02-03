package StepDefinitions.Controls;

import com.appium.Main.Factory.DriverFactory;
import io.appium.java_client.TouchAction;

import static io.appium.java_client.touch.WaitOptions.waitOptions;
import static io.appium.java_client.touch.offset.PointOption.point;
import static java.time.Duration.ofMillis;

public class Gestures{

    public static Gestures Instance = null;

    public static Gestures Instance() {
        {
            if (Instance == null) {
                Instance = new Gestures();

            }
            return Instance;
        }
    }

    public void swipe(int startX, int startY, int endX, int endY, int millis) {
        TouchAction t = new TouchAction(DriverFactory.driver);
        t.press(point(startX, startY)).waitAction(waitOptions(ofMillis(millis))).moveTo(point(endX, endY)).release()
                .perform();
    }


}
