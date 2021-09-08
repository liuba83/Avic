package avic;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.assertEquals;

public class AvicHomeWork {

    private WebDriver driver;

   @BeforeTest
    public void profileSetUp() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver 2");
    }

    @BeforeMethod
    public void testSetUp() {
       driver = new ChromeDriver();
       driver.manage().window().maximize();
       driver.get("https://avic.ua/");
    }

    @Test(priority = 1)
    public void verifyThatUrlContainsSelectedTab() {
       driver.findElement(By.xpath("//div[@class='top-links__left flex-wrap']//a[contains(@href, '/back-to-school')]")).click();
       assertTrue(driver.getCurrentUrl().contains("back-to-school"));
    }

    @Test(priority = 2)
    public void verifyThatPdpOpen() {
       driver.findElement(By.xpath("//div[@class='top-links__left flex-wrap']//a[contains(@href, '/back-to-school')]")).click();
       driver.findElement(By.xpath("//span[contains(text(), '16 235507')]")).click();
       assertTrue(driver.getCurrentUrl().contains("iphone-12-128gb"));
    }

    @Test(priority = 3)
    public void verifyThat12mWarrantySelected() {

       driver.findElement(By.xpath("//div[@class='top-links__left flex-wrap']//a[contains(@href, '/back-to-school')]")).click();
       driver.findElement(By.xpath("//span[contains(text(), '16 235507')]")).click();

       Select dropdown = new Select (driver.findElement(By.xpath("//select[@name='warranty']")));
       dropdown.selectByVisibleText("12 месяцев");

       String actualText = dropdown.getFirstSelectedOption().getText();
       assertEquals(actualText, "12 месяцев");
    }

    @Test(priority = 4)
    public void verifyPopUpWithBuyIn1Click() {

        driver.findElement(By.xpath("//div[@class='top-links__left flex-wrap']//a[contains(@href, '/back-to-school')]")).click();
        driver.findElement(By.xpath("//span[contains(text(), '16 235507')]")).click();

        Select dropdown = new Select (driver.findElement(By.xpath("//select[@name='warranty']")));
        dropdown.selectByVisibleText("12 месяцев");

        WebDriverWait wait = new WebDriverWait(driver, 10);

       driver.findElement(By.xpath("//a[text()='Купить в 1 клик']")).click();
       String actualText = driver.findElement(By.xpath("//div[@class='modal-top']//div[text()='Купить в 1 клик']")).getText();
       assertEquals(actualText, "Купить в 1 клик");
    }

    @AfterMethod
    public void tearDown() {
       driver.close();
    }

}
