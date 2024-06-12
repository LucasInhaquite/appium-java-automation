import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LongPress extends BaseTest{

    @Test
    public void longPressGesture() {

        driver.findElement(AppiumBy.accessibilityId("Views")).click();
        driver.findElement(By.xpath("//android.widget.TextView[@content-desc='Expandable Lists']")).click();
        driver.findElement(AppiumBy.accessibilityId("1. Custom Adapter")).click();

        //Create a WebElement and add the desired element where you want to perform the action
        WebElement peopleNamesElement = driver.findElement(By.xpath("//android.widget.TextView[@text='People Names']"));

        //Create the script to perform the action using the element created and setting a duration for the Long Press
        longPressAction(peopleNamesElement);

        //Assertions after the Long Press
        String sampleMenu = driver.findElement(By.id("android:id/title")).getText();
        Assert.assertEquals(sampleMenu, "Sample menu");
        Assert.assertTrue(driver.findElement(By.id("android:id/title")).isDisplayed());

    }
}
