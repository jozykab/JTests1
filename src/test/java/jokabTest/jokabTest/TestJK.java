package jokabTest.jokabTest;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import org.json.JSONArray;
import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class TestJK {
    @Test
    public void OpenVirtualConsult() {

        WebDriver driver = new FirefoxDriver();
        driver.get("http://www.virtualconsultafrica.com/");
        String title = driver.getTitle();
        Reporter.log("Page Title is " + title);
        Assert.assertEquals("Virtual Consult - Acceuil", title);
        driver.quit();

    }

    @Test(enabled=true)
    public final void mobileAndroidAppInfoCongo() throws Exception {
        WebDriver driver;
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("deviceName", "Android Emulator");
        capabilities.setCapability("browserName", "Android");
        capabilities.setCapability("platformVersion", "6.0");
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("appPackage", "info.infocongo.infocongo");
        capabilities.setCapability("appActivity", "info.infocongo.infocongo.Bienvenue");
        driver = new RemoteWebDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.findElement(By.id("mps_Admin")).click();
        String result = driver.findElement(By.id("url")).getText();
        Reporter.log("Generated URL is : " + result);
        driver.quit();
    }

    @Test
    public void apiTesting() throws Exception {
        try {
            URL url = new URL("http://maps.googleapis.com/maps/api/geocode/json?address=chicago&sensor=false&#8221");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != 200) {
                Reporter.log(" HTTP error code : " + conn.getResponseCode());
                throw new RuntimeException(" HTTP error code : " + conn.getResponseCode());
            }

            Scanner scan = new Scanner(url.openStream());
            String entireResponse = new String();
            while (scan.hasNext())
                entireResponse += scan.nextLine();

            Reporter.log("Response : " + entireResponse);

            scan.close();

            JSONObject obj = new JSONObject(entireResponse);
            String responseCode = obj.getString("status");
            Reporter.log("status : " + responseCode);

            JSONArray arr = obj.getJSONArray("results");
            for (int i = 0; i < arr.length(); i++) {
                String placeid = arr.getJSONObject(i).getString("place_id");
                Reporter.log("Place id : " + placeid);
                String formatAddress = arr.getJSONObject(i).getString("formatted_address");
                Reporter.log("Address : " + formatAddress);

                // validating Address as per the requirement
                if (formatAddress.equalsIgnoreCase("Chicago, IL, USA")) {
                    Reporter.log("Address is as Expected");
                } else {
                    Reporter.log("Address is not as Expected");
                }
            }

            conn.disconnect();
        } catch (MalformedURLException e) {
            e.printStackTrace();

        } catch (IOException e) {

            e.printStackTrace();

        }

    }
    @Test
    public final void appiumSauceLabsTest() throws Exception {
        WebDriver driver;
     DesiredCapabilities caps = DesiredCapabilities.android();
        String url = "http://206467959:0facb6fd-5ad1-4b97-8fab-5f083e3761bc@ondemand.saucelabs.com:80/wd/hub";
        String app = "sauce-storage:mps.apk";
        caps.setCapability("appiumVersion", "1.5.1");
        caps.setCapability("deviceName", "Samsung Galaxy S4 Emulator");
        caps.setCapability("deviceOrientation", "portrait");
        caps.setCapability("browserName", "");
        caps.setCapability("platformVersion", "4.4");
        caps.setCapability("platformName", "Android");
        caps.setCapability("app", app);

        driver = new RemoteWebDriver(new URL(url), caps);

        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.findElement(By.id("mps_Admin")).click();
        String result = driver.findElement(By.id("url")).getText();
        Reporter.log("Generated URL : " + result);
        driver.quit();
    }
}
