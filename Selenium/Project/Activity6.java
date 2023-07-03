package activities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class Activity6 {

    WebDriver driver;
    WebDriverWait wait;

    Actions actions;

    @BeforeClass
    public void driverinititae() {
        // Set up the Firefox driver
        System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, "/dev/null");
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
        wait =new WebDriverWait(driver,Duration.ofSeconds(20));
        actions =new Actions(driver);
        //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        System.out.println("Driver initiated");
        //Open the browser
        driver.get("https://alchemy.hguy.co/jobs");
    }

    @Test
    public void applyForJob() throws InterruptedException {
        //find job link and click
        driver.findElement(By.xpath("//li[@id='menu-item-24']/a")).click();
        String pageTitle = driver.getTitle();
        Assert.assertEquals(pageTitle,"Jobs – Alchemy Jobs");
        WebElement searchText =driver.findElement(By.id("search_keywords"));
        //Enter Banks
        searchText.sendKeys("Banking", Keys.RETURN);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'Search completed')]")));
        List<WebElement> listofjobs =driver.findElements(By.xpath("//div[@class='position']/h3"));
        System.out.println("no of Jobs found is  :" + listofjobs.size());
        for(WebElement e:listofjobs){
            //Click the first job find in the list
            String jobText =e.getText().toLowerCase();
            if (jobText.contains("banking")){
                actions.moveToElement(e);
                e.click();
            }
        }
      //wait for apply button and click
         wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@class='application_button button']"))).click();
        //wait for email to appear and get the email text
        String email =wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@class='job_application_email']"))).getText();
        System.out.println("Mail your details to : " + email);

    }

    @AfterClass
    public void closedriver() {
        //Closet the driver
        driver.close();
        System.out.println("Close the browser");
    }


}
