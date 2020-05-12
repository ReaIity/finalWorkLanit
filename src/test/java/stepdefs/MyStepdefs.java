package stepdefs;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideWait;
import cucumber.api.java.ru.И;
import cucumber.api.java.ru.Затем;
import cucumber.api.java.ru.Также;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.List;

import static pages.AbstractPage.getPageByTitle;
import static pages.AbstractPage.getUrlByTitle;
import static test.strings.userLogin;
import static test.strings.userPassword;
import static com.codeborne.selenide.Selenide.*;


import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class MyStepdefs {

    List<String> subscribes = new ArrayList<>();

    @И("Начинаем тест")
    public void начинаемТест() {
        
        System.out.println("Старт теста");
        
    }

    @И("Открываем страницу {string}")
    public void открываемСайт(String site) throws ClassNotFoundException, InterruptedException  {
        
        open(getUrlByTitle(site));
        
    }

    @И("На странице {string} находим кнопку {string}")
    public void находимКнопкуВойти(String str, String nameEL) throws ClassNotFoundException,InterruptedException {
        
        System.out.println("Находим кнопку Войти");
        getPageByTitle(str).getElementByName(nameEL).click();
        
    }

    @Затем("на странице {string} ввести логин {string}")
    public void вводимЛогин(String str, String login) throws ClassNotFoundException, InterruptedException {
        
        System.out.println("Проходим авторизацию");
        getPageByTitle(str).getElementByName(login).val(userLogin);

    }

    @Также("на странице {string} ввести пароль {string}")
    public void вводимПароль(String str, String pass) throws ClassNotFoundException, InterruptedException {
        
        getPageByTitle(str).getElementByName(pass).val(userPassword).pressEnter();
        
    }


    @И("на странице {string} проверить наличие иконки {string}")
    public void проверяемАвторизацию(String str, String icon) throws ClassNotFoundException, InterruptedException {
        
        System.out.println("Проверяем авторизацию");
        getPageByTitle(str).getElementByName(icon).should(Condition.image);
        
    }


    @И("на странице {string} нажать на кнопку статуса {string}")
    public void наСтраницеНажатьНаКнопкуСтатуса(String str, String inactive) throws InterruptedException, ClassNotFoundException {

        Thread.sleep(3000);
        getPageByTitle(str).getElementByName(inactive).click();

        subscribes.add(getPageByTitle(str).getElementByName("Выбранный топик").getAttribute("href").replaceAll("https:\\/\\/dev.n7lanit.ru",""));

    }

    @И("на странице {string} в выпадающем меню нажать кнопку подписки {string}")
    public void наСтраницеВВыпадающемМенюНажатьКнопкуПодписки(String str, String sub) throws InterruptedException, ClassNotFoundException {

        Thread.sleep(1000);
        getPageByTitle(str).getElementByName(sub).should(visible).click();

    }


    @И("на странице {string} нажать кнопку со списком всех подписок {string}")
    public void наСтраницеНажатьКнопкуСоСпискомВсехПодписок(String str, String subscribes) throws InterruptedException, ClassNotFoundException {

        Thread.sleep(1000);
        getPageByTitle(str).getElementByName(subscribes).should(visible).click();

    }

    @И("Проверить наличие подписок")
    public void проверитьНаличиеПодписок() throws InterruptedException {

        Thread.sleep(1000);

        for(int i =0; i < subscribes.size(); i++){
            String s = subscribes.get(i);
            $(By.xpath("//*[@href='"+ s +"']")).should(Condition.visible);
        }
        Thread.sleep(3000);

    }

    @И("на странице {string} отписаться {string} от новых тем")
    public void наСтраницеОтписатьсяОтНовыхТем(String str, String unsubscribe) throws InterruptedException, ClassNotFoundException {

        Thread.sleep(1000);

        for(int i =0; i < subscribes.size(); i++){
            Thread.sleep(2000);
            String s = subscribes.get(i);
            $(By.xpath("//*[@href='"+ s +"']/ancestor::div[4]//*[@class='col-sm-2 col-md-2 hidden-xs']//*[@class='col-xs-12 hidden-xs hidden-sm']" +
                    "//button[@type='button']")).click();
            getPageByTitle(str).getElementByName(unsubscribe).click();
        }

    }

    @Затем("повторить шаги")
    public void повторитьШаги() {
    }
}
