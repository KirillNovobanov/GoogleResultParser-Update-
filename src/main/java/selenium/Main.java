package selenium;

/*
Завдання: Користувач вводить (з терміналу) пошуковий запит. Програма відкриває вікно
Firefox, завантажує сторінку Google, вводить пошуковий запит і натискає “Пошук”. Після
цього в циклі, для кожного результату з першої сторінки: перейти на сторінку, отримати
заголовок поточного вікна (він залежить від сторінки), вивести його в консоль, перейти до
іншого результату.
В кінці маємо отримати список заголовків сторінок в терміналі.
*/

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;
import java.util.Scanner;

public class Main {

    public void startQueryTest(String query) {

        // Создаем новый экземпляр firefox драйвера
        // Драйвер firefox поддерживает javascript
        WebDriver driver = new FirefoxDriver();

        // Используем драйвер для соединения с указанной страницей
        driver.get("http://www.google.com");

        // Находим елемент ввода текста по его имени
        WebElement element = driver.findElement(By.name("q"));

        // Вводим что-то для поиска
        element.sendKeys(query + "\n");

        // Теперь подаем форму. WebDriver найдет нашу форму по елементу
        element.submit();

        // Ожидаем пока страница google покажет результат
        WebElement myDynamicElement = (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.presenceOfElementLocated(By.id("resultStats")));
        List<WebElement> findElements = driver.findElements(By.xpath("//*[@id='rso']//h3/a"));

        // все ссылки которые посетим
        for (WebElement webElement : findElements) {
            System.out.println(webElement.getAttribute("href"));
            WebDriver lDrv = new FirefoxDriver();
            lDrv.get(webElement.getAttribute("href"));
            System.out.println(lDrv.getTitle());
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter something to search for : ");
        String userQuery = sc.nextLine();
        new Main().startQueryTest(userQuery);
    }
}

