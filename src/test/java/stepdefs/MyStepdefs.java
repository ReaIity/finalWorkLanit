package stepdefs;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideWait;
import cucumber.api.java.ru.И;
import cucumber.api.java.ru.Затем;
import cucumber.api.java.ru.Также;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class MyStepdefs {

    @И("Начинаем тест")
    public void начинаемТест() {
        System.out.println("Тест начат");
    }

    @И("Открываем сайт")
    public void открываемСайт() {
        open("https://dev.n7lanit.ru/");
    }

    @И("Находим кнопку Войти")
    public void находимКнопкуВойти() {
        System.out.println("Находим кнопку Войти");
        $(By.xpath("//button[contains(text(), 'Войти')]/..")).should(Condition.visible).click();
    }

    @Затем("Проходим авторизацию")
    public void вводимЛогин() {
        System.out.println("Проходим авторизацию");
        $(By.xpath("//div[@class='control-input']/input[@id='id_username']")).val("Noxiver");
        $(By.xpath("//div[@class='control-input']/input[@id='id_password']")).val("Strekoza").pressEnter();
    }

    @И("Проверяем авторизацию")
    public void проверяемАвторизацию() {
        System.out.println("Проверяем авторизацию");
        $(By.xpath("//*[@id=\"user-menu-mount\"]/ul/li[3]/a/img")).should(Condition.visible);
    }

    @Затем("Выбираем случайную тему, не являющуюся опросом")
    public void выбираемСлучайнуюТему() {
        System.out.println("Выбираем случайную тему, не являющуюся опросом");
        open("https://dev.n7lanit.ru/");
        ElementsCollection collection = $$(By.xpath("//span[@class='thread-detail-replies' and not(preceding-sibling::span)]/ancestor::div[3]/a"));
        collection.get((int) (collection.size()*Math.random())).click();
    }


    @И("Находим кнопку ответить и жмем на неё")
    public void находимИЖмемКнопкуОтветить() {
        System.out.println("Находим кнопку ответить и жмем на неё");
        Selenide.sleep(1000);
        $(By.xpath("//div[@class='col-sm-4 hidden-xs']/button[@class='btn btn-primary btn-block btn-outline']")).shouldHave(text("Ответить")).click();
    }

    @И("Оставлем комментарий")
    public void оставляемКомментарий() {
        System.out.println("Оставлем комментарий");
        $(By.xpath("//textarea[@id='editor-textarea']")).shouldBe(Condition.visible).val("Привет мир!").submit();
    }

    @И("Проверяем наличие коментария")
    public void проверяемНаличиеКомментария() {
        System.out.println("Проверяем наличие коментария");
        $(By.xpath("//div[@class='post-body']/article/p[contains(text(),'Привет мир!')]")).isDisplayed();
    }

    @Затем("Переходим во вкладку Темы")
    public void переходимВоВкладкуТемы() {
        System.out.println("Переходим во вкладку Темы");
        $(By.xpath("//ul[@class='nav navbar-nav']//a[contains(text(),'Темы')]")).shouldBe(visible).click();
    }

    @Также("Повторяем шаги с коментарием")
    public void повторяемШагиСКоментарием() {
        System.out.println("Повторяем шаги с коментарием");
        ElementsCollection collection = $$(By.xpath("//span[@class='thread-detail-replies' and not(preceding-sibling::span)]/ancestor::div[3]/a"));
        collection.get((int) (collection.size()*Math.random())).click();
        Selenide.sleep(6000);
        $(By.xpath("//div[@class='col-sm-4 hidden-xs']/button[@class='btn btn-primary btn-block btn-outline']")).shouldHave(text("Ответить")).click();
        $(By.xpath("//textarea[@id='editor-textarea']")).shouldBe(Condition.visible).val("Привет мир!").submit();
        $(By.xpath("//div[@class='post-body']/article/p[contains(text(),'Привет мир!')]")).isDisplayed();
        $(By.xpath("//ul[@class='nav navbar-nav']//a[contains(text(),'Темы')]")).shouldBe(visible).click();
    }

}
