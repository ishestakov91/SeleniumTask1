import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Test1 {
    private WebDriver driver;
    private String baseUrl;

    @Before
    public void setUp() throws Exception {
        System.setProperty("webdriver.chrome.driver", "drv/chromedriver.exe");
        driver = new ChromeDriver();
        baseUrl = "http://www.sberbank.ru/ru/person";
        //driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
    }

    @Test
    public void testInsurance() throws Exception {

        driver.get(baseUrl + "/");

        driver.findElement(By.xpath("//a[@aria-label='Страхование']")).click();

        Thread.sleep(2000);
        
        driver.findElement(By.xpath("//a[text()='Все страховые программы']")).click();

        assertTrue(driver.findElement(By.xpath("//h3[text()='Страхование для путешественников']")).isDisplayed());

        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView(true)", driver.findElement(By.xpath("//h3[text()='Страхование для путешественников']")));

        driver.findElement(By.xpath("(//h3[text()='Страхование для путешественников']/../../..//a)[3]")).click();

        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView(true)", driver.findElement(By.xpath("//h3[text()='Минимальная']")));
        driver.findElement(By.xpath("//h3[text()='Минимальная']/..")).click();
        Thread.sleep(2000);

        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView(true)", driver.findElement(By.xpath("//button[text()='Оформить']")));
        driver.findElement(By.xpath("//button[text()='Оформить']")).click();

        Thread.sleep(1000);

        driver.findElement(By.xpath("//input[@id='surname_vzr_ins_0']")).clear();
        driver.findElement(By.xpath("//input[@id='surname_vzr_ins_0']")).sendKeys("Иванов");
        Thread.sleep(1000);

        driver.findElement(By.xpath("//input[@id='name_vzr_ins_0']")).clear();
        driver.findElement(By.xpath("//input[@id='name_vzr_ins_0']")).sendKeys("Иван");
        Thread.sleep(1000);

        driver.findElement(By.xpath("//input[@id='birthDate_vzr_ins_0']")).clear();
        driver.findElement(By.xpath("//input[@id='birthDate_vzr_ins_0']")).sendKeys("02061971");
        driver.findElement(By.xpath("//input[@id='birthDate_vzr_ins_0']")).sendKeys(Keys.TAB);
        Thread.sleep(2000);

        driver.findElement(By.xpath("//input[@id='person_lastName']")).clear();
        driver.findElement(By.xpath("//input[@id='person_lastName']")).sendKeys("Иванов");
        Thread.sleep(1000);

        driver.findElement(By.xpath("//input[@id='person_firstName']")).clear();
        driver.findElement(By.xpath("//input[@id='person_firstName']")).sendKeys("Иван");
        Thread.sleep(1000);

        driver.findElement(By.xpath("//input[@id='person_middleName']")).clear();
        driver.findElement(By.xpath("//input[@id='person_middleName']")).sendKeys("Иванович");

        driver.findElement(By.xpath("//input[@id='person_birthDate']")).clear();
        driver.findElement(By.xpath("//input[@id='person_birthDate']")).sendKeys("02061971");
        driver.findElement(By.xpath("//input[@id='person_birthDate']")).sendKeys(Keys.TAB);
        Thread.sleep(1000);

        driver.findElement(By.xpath("//label[text()='Мужской']")).click();

        driver.findElement(By.xpath("//input[@id='passportSeries']")).clear();
        driver.findElement(By.xpath("//input[@id='passportSeries']")).sendKeys("0316");
        Thread.sleep(1000);

        driver.findElement(By.xpath("//input[@id='passportNumber']")).clear();
        driver.findElement(By.xpath("//input[@id='passportNumber']")).sendKeys("770378");
        Thread.sleep(2000);

        driver.findElement(By.xpath("//input[@id='documentDate']")).click();
        driver.findElement(By.xpath("//input[@id='documentDate']")).sendKeys("15062016");
        driver.findElement(By.xpath("//input[@id='documentDate']")).sendKeys(Keys.TAB);
        Thread.sleep(1000);

        driver.findElement(By.xpath("//input[@id='documentIssue']")).clear();
        driver.findElement(By.xpath("//input[@id='documentIssue']")).sendKeys("ОВД г. Москва");
        Thread.sleep(1000);


        assertEquals("Иванов", driver.findElement(By.xpath("//input[@id='surname_vzr_ins_0']")).getAttribute("value"));
        assertEquals("Иван", driver.findElement(By.xpath("//input[@id='name_vzr_ins_0']")).getAttribute("value"));
        assertEquals("02.06.1971", driver.findElement(By.xpath("//input[@id='birthDate_vzr_ins_0']")).getAttribute("value"));

        assertEquals("Иванов", driver.findElement(By.xpath("//input[@id='person_lastName']")).getAttribute("value"));
        assertEquals("Иван", driver.findElement(By.xpath("//input[@id='person_firstName']")).getAttribute("value"));
        assertEquals("Иванович", driver.findElement(By.xpath("//input[@id='person_middleName']")).getAttribute("value"));
        assertEquals("02.06.1971", driver.findElement(By.xpath("//input[@id='person_birthDate']")).getAttribute("value"));

        assertEquals("0316", driver.findElement(By.xpath("//input[@id='passportSeries']")).getAttribute("value"));
        assertEquals("770378", driver.findElement(By.xpath("//input[@id='passportNumber']")).getAttribute("value"));
        assertEquals("15.06.2016", driver.findElement(By.xpath("//input[@id='documentDate']")).getAttribute("value"));
        assertEquals("ОВД г. Москва", driver.findElement(By.xpath("//input[@id='documentIssue']")).getAttribute("value"));

        Thread.sleep(1000);

        driver.findElement(By.xpath("//button[contains(text(),'Продолжить')]")).click();

        assertEquals("Поле не заполнено.",
                driver.findElement(By.xpath("(//legend[text()='Контакты']/..//span[contains(@class,'form-control__message')])[1]")).getText());
        assertEquals("Поле не заполнено.",
                driver.findElement(By.xpath("(//legend[text()='Контакты']/..//span[contains(@class,'form-control__message')])[2]")).getText());
        assertEquals("Поле не заполнено.",
                driver.findElement(By.xpath("(//legend[text()='Контакты']/..//span[contains(@class,'form-control__message')])[3]")).getText());
        assertEquals("При заполнении данных произошла ошибка",
                driver.findElement(By.xpath("//div[contains(@class,'alert-form-error')]")).getText());

    }

}



