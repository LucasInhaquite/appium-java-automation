import io.appium.java_client.AppiumBy;
import org.testng.annotations.Test;

public class AppiumBasics extends BaseTest {

    @Test
    public void wifiSettingsName() {

        //Actual automation
        //Xpath, id, acessibilityId, className, androidUiAutomator
        driver.findElement(AppiumBy.accessibilityId("Preference")).click();

    }
}