import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SwipeDemo extends BaseTest{

    @Test
    public void scrollDemoTest() {

        driver.findElement(AppiumBy.accessibilityId("Views")).click();
        driver.findElement(AppiumBy.accessibilityId("Gallery")).click();
        driver.findElement(AppiumBy.xpath("//android.widget.TextView[@content-desc='1. Photos']")).click();

        //Assert that first picture is focusable = true
        WebElement firstImage = driver.findElement(By.xpath("(//android.widget.ImageView) [1]"));
        Assert.assertEquals(firstImage.getAttribute("focusable"), "true");

        //Swipe to second picture
        swipeAction(firstImage, "left");

        //Assert that first picture is focusable = false
        Assert.assertEquals(firstImage.getAttribute("focusable"), "false");
    }
}
