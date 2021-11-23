package step_definitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.github.bonigarcia.wdm.WebDriverManager;
import managers.DriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CucumberHooks {
    public static WebDriver driver;

    @Before
    public void beforeScenario() {
        driver = DriverManager.initializeDriver(System.getProperty("browser", "chrome"));
        driver.manage().window().maximize();
    }

    @After
    public void afterScenario()
    {
//        driver.close();
        driver.manage().deleteAllCookies();
        driver.quit();
    }
}
