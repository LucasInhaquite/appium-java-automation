import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.openqa.selenium.By;
import org.openqa.selenium.DeviceRotation;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.Test;

public class MiscellaneousAppiumActions extends BaseTest {

    @Test
    public void Miscellaneous() {

        // adb shell dumpsys window | grep -E 'mCurrentFocus'  - MAC
        // adb shell dumpsys window | find "mCurrentFocus"  - Windows

        //App Package and App Activity
        ((JavascriptExecutor) driver).executeScript("mobile: startActivity",
                ImmutableMap.of("intent", "io.appium.android.apis/io.appium.android.apis.preference.PreferenceDependencies"));

        driver.findElement(By.id("android:id/checkbox")).click();

        //Rotate the device
        DeviceRotation landscape = new DeviceRotation(0, 0, 90);
        driver.rotate(landscape);

        driver.findElement(AppiumBy.xpath("//android.widget.TextView[@resource-id='android:id/title' and @text='WiFi settings']")).click();
        String alertTitle = driver.findElement(AppiumBy.id("android:id/alertTitle")).getText();
        Assert.assertEquals(alertTitle, "WiFi settings");

        //copy to clipboard/paste from clipboard
        driver.setClipboardText("Lucas Wifi");
        driver.findElement(By.id("android:id/edit")).sendKeys(driver.getClipboardText());
        driver.pressKey(new KeyEvent(AndroidKey.ENTER));
        driver.findElements(AppiumBy.className("android.widget.Button")).get(1).click();

        //Android Key events
        driver.pressKey(new KeyEvent(AndroidKey.BACK));
        driver.pressKey(new KeyEvent(AndroidKey.HOME));

    }

}