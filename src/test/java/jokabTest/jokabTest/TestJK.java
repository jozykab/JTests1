package jokabTest.jokabTest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class TestJK {
  @Test
  public void simpleTest() {
      

      WebDriver driver = new FirefoxDriver();     
      driver.get("http://www.virtualconsultafrica.com/");
      String title = driver.getTitle();
      Reporter.log("Page Title is " + title);
      Assert.assertEquals("Virtual Consult - Accueil", title);
      driver.quit();
      
  }
}
