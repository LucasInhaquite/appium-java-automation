import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class MobileBrowserTest extends BrowserBaseTest{

    @Test
    public void browserTest(){
        driver.get("http://google.com");
        System.out.println(driver.getTitle());

        if(driver.findElement(By.xpath("//*[text()='Aceitar tudo']")).isDisplayed()){
            WebElement element = driver.findElement(By.xpath("//*[text()='Aceitar tudo']"));
            JavascriptExecutor executor = driver;
            executor.executeScript("arguments[0].click();", element);
        };

        driver.findElement(By.name("q")).sendKeys("rahul shetty academy");
        driver.findElement(By.name("q")).sendKeys(Keys.ENTER);
    }

    @Test
    public void browserTestAngularApp(){
        driver.get("https://rahulshettyacademy.com/angularAppdemo/");
        driver.findElement(By.xpath("//span[@class='navbar-toggler-icon']")).click();
        driver.findElement(By.cssSelector("a[routerlink*='products']")).click();
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,1000)", "");	//Scroll
        String text = driver.findElement(By.cssSelector("a[href*='products/3']")).getText();
        Assert.assertEquals(text, "Devops");


    }
}
