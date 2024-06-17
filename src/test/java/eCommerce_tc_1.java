import io.appium.java_client.AppiumBy;
import org.testng.Assert;
import org.testng.annotations.Test;

public class eCommerce_tc_1 extends BaseTest{

    @Test
    public void fillForm(){

        driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/spinnerCountry")).click();
        scrollIntoView("Brazil");
        driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Brazil']")).click();

        driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/nameField")).sendKeys("Gigi Inhaquite");
        driver.hideKeyboard();
        driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/radioFemale")).click();

        driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/btnLetsShop")).click();
    }

    @Test
    public void errorToastMessage(){

        driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/btnLetsShop")).click();

        //Toast Message validation  'android.widget.Toast'
        String toastMessage = driver.findElement(AppiumBy.xpath("(//android.widget.Toast)[1]")).getAttribute("name");
        Assert.assertEquals(toastMessage, "Please enter your name");
    }
}
