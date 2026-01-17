import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;


import ru.yandex.practicum.Config;
import ru.yandex.practicum.OrderPage;




import java.util.stream.Stream;


import static org.junit.jupiter.api.Assertions.assertTrue;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class OrderPageTest {
    WebDriver driver;


    @BeforeEach
    public void setUp() {
        // Инициализация драйвера будет происходить в самом тесте
    }


    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }


    // Метод для инициализации драйвера по имени браузера
    private WebDriver getDriver(String browser) {
        switch (browser) {
            case "chrome":
                ChromeOptions chromeOptions = new ChromeOptions();
//                chromeOptions.addArguments("--start-maximized");
//                chromeOptions.addArguments("--disable-notifications");
//                chromeOptions.addArguments("--disable-popup-blocking");
                return new ChromeDriver(chromeOptions);
            case  "firefox":
                FirefoxOptions firefoxOptions = new FirefoxOptions();
//                firefoxOptions.addArguments("--start-maximized");
//                firefoxOptions.addArguments("--disable-notifications");
//                firefoxOptions.addArguments("--disable-popup-blocking");
                return new FirefoxDriver(firefoxOptions);
            default:
                throw new IllegalArgumentException("Unknown browser: " + browser);
        }
    }


    private static Stream<Arguments> userData() {
        return Stream.of(
//                Arguments.of("chrome", "Иван", "Иванов", "Ленина, 10", "Митино", "+79987654321", "29.01.2026", "сутки", "ЧЁРНЫЙ", "Комментарий"),
                Arguments.of("firefox", "Петр", "Петров", "Ленина, 20", "Черкизовская", "+79987654322", "30.01.2026", "двое суток", "серый", "Комментарий2")
        );
    }


    @ParameterizedTest
    @MethodSource("userData")
    public void orderScooterFromHeaderTest(String browser, String firstName, String lastName, String address, String metroStation, String phone, String datePicker, String rentalPeriod, String colour, String commentForCourier) {
        driver = getDriver(browser);
        driver.get(Config.baseUrl);
        OrderPage orderPage = new OrderPage(driver);


        // Нажимаем кнопку "Заказать" в хедере
        orderPage.clickButtonOrderInHeader();
        // Заполняем данные формы "Для кого самокат"
        orderPage.userData(firstName, lastName, address, metroStation, phone);
        // Заполняем данные формы "Про аренду"
        orderPage.forRent(datePicker, rentalPeriod, colour, commentForCourier);
        // Подтверждаем заказ
        orderPage.confirmationOrder();
        // Проверяем заголовок у всплывающего окна с сообщением об успешном создании заказа.
        assertTrue(orderPage.getTitleOrderCompleted().startsWith("Заказ оформлен"));
        // Проверяем текст с сообщением об успешном создании заказа у всплывающего окна.
        assertTrue(orderPage.getMessageAboutSuccessfulOrder().matches("Номер заказа: \\d+\\.  Запишите его: пригодится, чтобы отслеживать статус"), "Текст с номером заказа не найден или не соответствует шаблону");
    }


    @ParameterizedTest
    @MethodSource("userData")
    public void orderScooterFromPageTest(String browser, String firstName, String lastName, String address, String metroStation, String phone, String datePicker, String rentalPeriod, String colour, String commentForCourier) {
        driver = getDriver(browser);
        driver.get(Config.baseUrl);
        OrderPage orderPage = new OrderPage(driver);


        // Нажимаем кнопку "Заказать" в хедере
        orderPage.clickButtonOrderOnPage();
        // Заполняем данные формы "Для кого самокат"
        orderPage.userData(firstName, lastName, address, metroStation, phone);
        // Заполняем данные формы "Про аренду"
        orderPage.forRent(datePicker, rentalPeriod, colour, commentForCourier);
        // Подтверждаем заказ
        orderPage.confirmationOrder();
        // Проверяем заголовок у всплывающего окна с сообщением об успешном создании заказа.
        assertTrue(orderPage.getTitleOrderCompleted().startsWith("Заказ оформлен"));
        // Проверяем текст с сообщением об успешном создании заказа у всплывающего окна.
        assertTrue(orderPage.getMessageAboutSuccessfulOrder().matches("Номер заказа: \\d+\\.  Запишите его: пригодится, чтобы отслеживать статус"), "Текст с номером заказа не найден или не соответствует шаблону");
    }
}

