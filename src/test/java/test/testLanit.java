package test;

import com.codeborne.selenide.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.codeborne.selenide.WebDriverRunner;
import static com.codeborne.selenide.Configuration.timeout;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class testLanit{
    public WebDriver driver;
    public WebDriverWait wait;

    @BeforeTest
    public void setUP () {
        System.setProperty("webdriver.chrome.driver", "src/resources/chromedriver.exe");
        Configuration.timeout = 3000;
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-save-password-bubble");
        WebDriver driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        WebDriverRunner.setWebDriver(driver);
    }

    @AfterTest
    public void quitBrowser () {
        driver.quit();
    }

    @Test
    public void A_loginTest() {
        open("https://dev.n7lanit.ru/");
        $(By.xpath("//button[contains(text(), 'Войти')]/..")).should(Condition.visible).click();
        $(By.xpath("//div[@class='control-input']/input[@id='id_username']")).val("Noxiver");
        $(By.xpath("//div[@class='control-input']/input[@id='id_password']")).val("Strekoza").pressEnter();
        $(By.xpath("//*[@id=\"user-menu-mount\"]/ul/li[3]/a/img")).should(Condition.visible);

    }
    @Test
    public void B_chooseRandom () {
        open("https://dev.n7lanit.ru/");
        ElementsCollection collection = $$(By.xpath("//span[@class='thread-detail-replies' and not(preceding-sibling::span)]/ancestor::div[3]/a"));
        collection.get((int) (collection.size()*Math.random())).click();
        Selenide.sleep(6000);
        $(By.xpath("//div[@class='col-sm-4 hidden-xs']/button[@class='btn btn-primary btn-block btn-outline']")).shouldHave(text("Ответить")).click();
        $(By.xpath("//textarea[@id='editor-textarea']")).shouldBe(Condition.visible).val("Привет мир!").submit();
        $(By.xpath("//div[@class='post-body']/article/p[contains(text(),'Привет мир!')]")).isDisplayed();
        $(By.xpath("//ul[@class='nav navbar-nav']//a[contains(text(),'Темы')]")).shouldBe(visible).click();
}

    @Test
    public void C_anotherOne () {
        open("https://dev.n7lanit.ru/");
        ElementsCollection collection = $$(By.xpath("//span[@class='thread-detail-replies' and not(preceding-sibling::span)]/ancestor::div[3]/a"));
        collection.get((int) (collection.size()*Math.random())).click();
        Selenide.sleep(6000);
        $(By.xpath("//div[@class='col-sm-4 hidden-xs']/button[@class='btn btn-primary btn-block btn-outline']")).shouldHave(text("Ответить")).click();
        $(By.xpath("//textarea[@id='editor-textarea']")).shouldBe(Condition.visible).val("Привет мир!").submit();
        $(By.xpath("//div[@class='post-body']/article/p[contains(text(),'Привет мир!')]")).isDisplayed();
        $(By.xpath("//ul[@class='nav navbar-nav']//a[contains(text(),'Темы')]")).shouldBe(visible).click();
    }

}
