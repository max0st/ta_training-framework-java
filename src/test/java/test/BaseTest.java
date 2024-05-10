package test;

import driver.DriverSingleton;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import util.TestListener;

@Listeners({TestListener.class})
public class BaseTest {

    protected static WebDriver driver;


    @BeforeClass
    public static void setUp() {
        driver = DriverSingleton.getDriver();
    }

    @AfterClass
    public static void stopBrowser() {
        DriverSingleton.closeDriver();
    }
}
