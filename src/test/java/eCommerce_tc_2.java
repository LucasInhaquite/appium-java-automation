import io.appium.java_client.AppiumBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class eCommerce_tc_2 extends BaseTest{

    @Test
    public void addProductToCart() {

        driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/spinnerCountry")).click();
        scrollIntoView("Brazil");
        driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Brazil']")).click();

        driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/nameField")).sendKeys("Gigi Inhaquite");
        driver.hideKeyboard();
        driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/radioFemale")).click();

        driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/btnLetsShop")).click();

        //Products List
        scrollIntoView("Jordan 6 Rings");

        //Loop through the products available in the screen and click at Add to Cart Button
        int productCount = driver.findElements(AppiumBy.id("com.androidsample.generalstore:id/productName")).size();
        for(int i = 0; i < productCount; i++){
            String productName = driver.findElements(AppiumBy.id("com.androidsample.generalstore:id/productName")).get(i).getText();

            if(productName.equalsIgnoreCase("Jordan 6 Rings")){
                driver.findElements(AppiumBy.id("com.androidsample.generalstore:id/productAddCart")).get(i).click();
            }
        }

        //Go To Cart screen
        driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/appbar_btn_cart")).click();

        //Wait for cart page to load
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.attributeContains(driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/toolbar_title")), "text", "Cart"));

        //Assertion to confirm that the correct product was added to the cart
        String productAtCartName = driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/productName")).getText();
        Assert.assertEquals(productAtCartName, "Jordan 6 Rings");

    }
}
