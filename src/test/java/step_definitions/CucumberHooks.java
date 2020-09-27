package step_definitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CucumberHooks {
    public static WebDriver driver;
    public static WebDriverWait driverWait;

    @Before
    public void beforeScenario() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driverWait = new WebDriverWait(driver,  Duration.ofSeconds(10));
    }

    @After
    public void afterScenario()
    {
//        driver.close();
        driver.manage().deleteAllCookies();
        driver.quit();
    }
}
