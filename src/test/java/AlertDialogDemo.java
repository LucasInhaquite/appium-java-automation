import io.appium.java_client.AppiumBy;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.time.Duration;


public class AlertDialogDemo extends BaseTest{

    @BeforeClass
    public void accessAppAlertDialogsScreen(){
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.findElement(AppiumBy.accessibilityId("App")).click();
        driver.findElement(AppiumBy.accessibilityId("Alert Dialogs")).click();
    }

    @Test
    public void okCancelDialogWithMessage() {
        driver.findElement(AppiumBy.id("io.appium.android.apis:id/two_buttons")).click();
        String dialogMessage = driver.findElement(AppiumBy.id("android:id/alertTitle")).getText();
        Assert.assertTrue(dialogMessage.contains("Lorem ipsum dolor sit aie consectetur adipiscing"));
        driver.findElement(AppiumBy.id("android:id/button1")).click();
    }

    @Test
    public void okCancelDialogWithLongMessage() {
        driver.findElement(AppiumBy.id("io.appium.android.apis:id/two_buttons2")).click();
        String dialogMessage = driver.findElement(AppiumBy.id("android:id/message")).getText();
        Assert.assertTrue(dialogMessage.contains("Plloaso mako nuto siwuf cakso dodtos anr koop a cupy uf cak vux noaw yerw phuno."));
        driver.findElement(AppiumBy.id("android:id/button3")).click();
    }

    @Test
    public void okCancelDialogWithUltraLongMessage() {
        driver.findElement(AppiumBy.id("io.appium.android.apis:id/two_buttons2ultra")).click();
        String dialogMessage = driver.findElement(AppiumBy.id("android:id/message")).getText();
        Assert.assertTrue(dialogMessage.contains("Cak pwico siructiun ruous nust apoply tyu cak Uhex sisulutiun munityuw"));
        driver.findElement(AppiumBy.id("android:id/button2")).click();
    }

    @Test
    public void listDialog() {
        driver.findElement(AppiumBy.id("io.appium.android.apis:id/select_button")).click();
        driver.findElement(AppiumBy.xpath("//android.widget.TextView[@resource-id='android:id/text1' and @text='Command three']")).click();
        String dialogMessage = driver.findElement(AppiumBy.id("android:id/message")).getText();
        Assert.assertTrue(dialogMessage.contains("You selected: 2 , Command three"));
        driver.navigate().back();
    }

    @Test(priority = -1)
    public void progressDialog() throws InterruptedException {
        driver.findElement(AppiumBy.accessibilityId("Progress dialog")).click();
        driver.findElement(AppiumBy.accessibilityId("Single choice list")).isDisplayed();

    }

    @Test
    public void singleChoiceList() {
        driver.findElement(AppiumBy.accessibilityId("Single choice list")).click();
        WebElement satelliteRadioButton = driver.findElement(AppiumBy.xpath("(//android.widget.CheckedTextView) [2]"));
        satelliteRadioButton.click();
        Assert.assertEquals(satelliteRadioButton.getAttribute("checkable"), "true");
        Assert.assertEquals(satelliteRadioButton.getAttribute("text"), "Satellite");
        driver.findElement(AppiumBy.id("android:id/button1")).click();
    }

    @Test
    public void repeatAlarm() {
        driver.findElement(AppiumBy.accessibilityId("Repeat alarm")).click();
        WebElement saturdayCheckbox = driver.findElement(AppiumBy.xpath("//android.widget.CheckedTextView[@text='Every Saturday']"));
        Assert.assertEquals(saturdayCheckbox.getAttribute("checked"), "false");
        saturdayCheckbox.click();
        Assert.assertEquals(saturdayCheckbox.getAttribute("checked"), "true");
        driver.findElement(AppiumBy.id("android:id/button1")).click();
    }

    @Test
    public void textEntryDialog() {
        driver.findElement(AppiumBy.accessibilityId("Text Entry dialog")).click();
        WebElement nameTextbox = driver.findElement(AppiumBy.id("io.appium.android.apis:id/username_edit"));
        nameTextbox.sendKeys("Lucas Inhaquite");
        WebElement passwordTextbox = driver.findElement(AppiumBy.id("io.appium.android.apis:id/password_edit"));
        passwordTextbox.sendKeys("MyPassword");
        Assert.assertEquals(nameTextbox.getText(), "Lucas Inhaquite");
        Assert.assertEquals(passwordTextbox.getText(), "••••••••••");
        driver.findElement(AppiumBy.id("android:id/button1")).click();
    }

    @Test
    public void okCancelDialogWithTraditionalTheme() {
        driver.findElement(AppiumBy.accessibilityId("OK Cancel dialog with traditional theme")).click();
        WebElement alertTitle = driver.findElement(AppiumBy.id("android:id/alertTitle"));
        Assert.assertTrue(alertTitle.getText().contains("ipsum dolor sit aie consectetur"));
        driver.findElement(AppiumBy.id("android:id/button2")).click();
    }

    @Test
    public void okCancelDialogWithHoloLightTheme() {
        driver.findElement(AppiumBy.accessibilityId("OK Cancel dialog with Holo Light theme")).click();
        WebElement alertTitle = driver.findElement(AppiumBy.id("android:id/alertTitle"));
        Assert.assertTrue(alertTitle.getText().contains("ipsum dolor sit aie consectetur"));
        driver.findElement(AppiumBy.id("android:id/button1")).click();
    }
}
