import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.time.Duration;
import java.util.List;
import java.util.Set;

public class eCommerce_tc_4_Hybrid extends BaseTest{

    @Test
    public void addProductsToCartAndValidateTotalValue() throws InterruptedException {

        driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/spinnerCountry")).click();
        scrollIntoView("Brazil");
        driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Brazil']")).click();

        driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/nameField")).sendKeys("Gigi Inhaquite");
        driver.hideKeyboard();
        driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/radioFemale")).click();

        driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/btnLetsShop")).click();

        //Add to Cart
        driver.findElement(AppiumBy.xpath("(//android.widget.TextView[@text='ADD TO CART'])[1]")).click();
        driver.findElement(AppiumBy.xpath("(//android.widget.TextView[@text='ADD TO CART'])")).click();

        //Go to Cart Screen
        driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/appbar_btn_cart")).click();

        //Wait for cart page to load
        AppiumBy pageTitle = (AppiumBy) AppiumBy.id("com.androidsample.generalstore:id/toolbar_title");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.refreshed(ExpectedConditions.textToBePresentInElementLocated(pageTitle, "Cart")));

        //Sum total amount of products added to the cart
        List<WebElement> productPrices = driver.findElements(AppiumBy.id("com.androidsample.generalstore:id/productPrice"));
        double totalSum = 0;
        for (WebElement productPrice : productPrices) {
            String amountString = productPrice.getText();
            double price = Double.parseDouble(amountString.substring(1));
            totalSum = totalSum + price;
        }

        //Assert sum vs the total amount displayed
        String displaySum = driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/totalAmountLbl")).getText();
        Assert.assertEquals(displaySum, "$ "+ String.valueOf(totalSum));

        //Terms and Conditions
        WebElement termsAndConditions = driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/termsButton"));
        longPressAction(termsAndConditions);
        String dialogMessage = driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/alertTitle")).getText();
        Assert.assertEquals(dialogMessage, "Terms Of Conditions");
        driver.findElement(AppiumBy.id("android:id/button1")).click();

        //Finish the purchase
        driver.findElement(AppiumBy.className("android.widget.CheckBox")).click();
        driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/btnProceed")).click();

        Thread.sleep(2000);

        //Get All Contexts existent within the app
        Set<String> contexts =  driver.getContextHandles();
        for(String contextName: contexts){
            System.out.println(contextName);
        }
        //Set the driver context to webview
        driver.context("WEBVIEW_com.androidsample.generalstore");

        //Hybrid - Google page
        if(driver.findElement(By.xpath("//*[text()='Aceitar tudo']")).isDisplayed()){
            WebElement element = driver.findElement(By.xpath("//*[text()='Aceitar tudo']"));
            JavascriptExecutor executor = driver;
            executor.executeScript("arguments[0].click();", element);
        };
        driver.findElement(By.name("q")).sendKeys("rahul shetty academy");
        driver.findElement(By.name("q")).sendKeys(Keys.ENTER);
        driver.pressKey(new KeyEvent(AndroidKey.BACK));

        //Set the driver context to the APP
        driver.context("NATIVE_APP");

        AppiumBy homepageTitle = (AppiumBy) AppiumBy.id("com.androidsample.generalstore:id/toolbar_title");
        wait.until(ExpectedConditions.refreshed(ExpectedConditions.textToBePresentInElementLocated(homepageTitle, "General Store")));

    }
}
