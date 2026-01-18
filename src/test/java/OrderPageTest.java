import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.yandex.practicum.Config;
import ru.yandex.practicum.OrderPage;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class OrderPageTest {
    WebDriver driver;
    OrderPage orderPage;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        driver.get(Config.baseUrl);
        orderPage = new OrderPage(driver);
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    private static Stream<Arguments> userData() {
        return Stream.of(
                Arguments.of("Иван", "Иванов", "Ленина, 10", "Митино", "+79987654321", "29.01.2026", "сутки", "ЧЁРНЫЙ", "Комментарий")
        );
    }

    @ParameterizedTest
    @MethodSource("userData")
    public void orderScooterFromHeaderTest(String firstName, String lastName, String address, String metroStation, String phone, String datePicker, String rentalPeriod, String colour, String commentForCourier) {
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
    public void orderScooterFromPageTest(String firstName, String lastName, String address, String metroStation, String phone, String datePicker, String rentalPeriod, String colour, String commentForCourier) {
        // Нажимаем кнопку "Заказать" на странице
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

