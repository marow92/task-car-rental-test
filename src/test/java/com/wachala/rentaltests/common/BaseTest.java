package com.wachala.rentaltests.common;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.concurrent.TimeUnit;

public class BaseTest {
    protected static WebDriver driver;

    private static final String GECKODRIVER_PATH = "src/test/resources/geckodriver";
    private static final String CHROMEDRIVER_PATH = "src/test/resources/chromedriver";

    @BeforeAll
    public static void setUpChromeDriver() {
        System.setProperty("webdriver.chrome.driver", CHROMEDRIVER_PATH);
        ChromeOptions options = new ChromeOptions()
                .setHeadless(false);

        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    public static void setUpGeckoDriver() {
        System.setProperty("webdriver.gecko.driver", GECKODRIVER_PATH);
        FirefoxOptions options = new FirefoxOptions()
                .setHeadless(true);

        driver = new FirefoxDriver(options);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @AfterEach
    public void cleanUp() {
        driver.manage().deleteAllCookies();
    }

    @AfterAll
    public static void tearDown() {
        //todo: clear cache and cookies
        driver.close();
    }
}
