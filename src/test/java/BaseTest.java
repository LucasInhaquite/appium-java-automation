import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.annotations.*;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Duration;

public class BaseTest {

    public AndroidDriver driver;
    public AppiumDriverLocalService service;

    @BeforeClass
    public void configureAppium() throws URISyntaxException, MalformedURLException {
        //Start Appium Server
        service = new AppiumServiceBuilder().withAppiumJS(new File("C:\\Users\\lucas\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js"))
                .withIPAddress("127.0.0.1").usingPort(4723).build();
        service.start();

        //AndroidDriver
        //Appium code -> Appium Server -> Mobile
        UiAutomator2Options options = new UiAutomator2Options();
        options.setDeviceName("Pixel_7_API_30");
        options.setApp("C:\\Dev\\appium-java-automation\\src\\test\\java\\resources\\ApiDemos-debug.apk");

        driver = new AndroidDriver(new URI("http://127.0.0.1:4723").toURL(), options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @AfterClass
    public void tearDown(){
        // Quit Driver and stop the service
        driver.quit();
        service.stop();
    }

    public void longPressAction(WebElement element){
        ((JavascriptExecutor) driver).executeScript("mobile: longClickGesture",
                ImmutableMap.of("elementId" , ((RemoteWebElement) element).getId(),
                        "duration", 2000));
    }

    public void scrollIntoView(String elementName){
        driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"" + elementName + "\"))"));
    }

    public void scrollToEndAction(){
        boolean canScrollMore;
        do {
            canScrollMore = (Boolean) ((JavascriptExecutor) driver).executeScript("mobile: scrollGesture", ImmutableMap.of(
                    "left", 100, "top", 100, "width", 200, "height", 200,
                    "direction", "down",
                    "percent", 10.0
            ));
        }while (canScrollMore);
    }

    public void swipeAction(WebElement element, String direction){
        ((JavascriptExecutor) driver).executeScript("mobile: swipeGesture",
                ImmutableMap.of("elementId" , ((RemoteWebElement) element).getId(),
                        "direction", direction,
                        "percent", 0.75,
                        "speed", 1500));
    }

    public void dragAndDropAction(WebElement source, Integer dropX, Integer dropY){
        ((JavascriptExecutor) driver).executeScript("mobile: dragGesture", ImmutableMap.of(
                "elementId", ((RemoteWebElement) source).getId(),
                "endX", dropX,
                "endY", dropY
        ));
    }

}
