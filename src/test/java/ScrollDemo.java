import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ScrollDemo extends BaseTest{

    @Test
    public void scrollDemoTest() throws InterruptedException {

        driver.findElement(AppiumBy.accessibilityId("Views")).click();

        //Use this if you know where to scroll
        scrollIntoView("Layouts");
        Thread.sleep(2000);

        scrollToEndAction();
        Thread.sleep(2000);
    }
}
