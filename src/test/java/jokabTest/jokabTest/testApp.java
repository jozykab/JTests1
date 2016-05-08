package jokabTest.jokabTest;

import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Reporter;
import org.testng.annotations.Test;

// TODO: Auto-generated Javadoc
/**
 * The Class testApp.
 */
public class testApp {

    /** The driver. */
    private WebDriver driver;

    /**
     * Sets the up.
     *
     * @throws Exception the exception
     */
    @Test(enabled=false)
    public final void setUp() throws Exception {

        // Created object of DesiredCapabilities class.
        DesiredCapabilities capabilities = new DesiredCapabilities();

        // Set android deviceName desired capability. Set it Android Emulator.
        capabilities.setCapability("deviceName", "Android Emulator");

        // Set browserName desired capability. It's Android in our case here.
        capabilities.setCapability("browserName", "Android");

        // Set android platformVersion desired capability. Set your emulator's android version.
        capabilities.setCapability("platformVersion", "6.0");

        // Set android platformName desired capability. It's Android in our case here.
        capabilities.setCapability("platformName", "Android");

        // Set android appPackage desired capability. It is com.android.calculator2 for calculator application.
        // Set your application's appPackage if you are using any other app.

        capabilities.setCapability("appPackage", "info.infocongo.infocongo");

        // Set android appActivity desired capability. It is com.android.calculator2.Calculator for calculator
        // application.
        // Set your application's appPackage if you are using any other app.
        capabilities.setCapability("appActivity", "info.infocongo.infocongo.Bienvenue");

        // Created object of RemoteWebDriver will all set capabilities.
        // Set appium server address and port number in URL string.
        // It will launch calculator app in emulator.

        driver = new RemoteWebDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

        // Click on CLR button.
        driver.findElement(By.id("mps_Admin")).click();

        // OR you can use bellow given syntax to click on CLR/DEL button.
        // driver.findElements(By.className("android.view.View")).get(1).findElements(By.className("android.widget.Button")).get(0).click();
        // Click on number 2 button.
        // driver.findElement(By.id("openPage")).click();

        String result = driver.findElement(By.id("url")).getText();
        System.out.println("Number sum result is : " + result);
        Reporter.log("Number sum result is : " + result);
        driver.quit();
    }
}
