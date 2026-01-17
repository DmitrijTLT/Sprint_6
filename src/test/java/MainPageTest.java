import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.yandex.practicum.Config;
import ru.yandex.practicum.MainPage;


import java.util.stream.Stream;


import static org.junit.jupiter.api.Assertions.assertEquals;


public class MainPageTest {
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
                return new ChromeDriver();
            case "firefox":
                return new FirefoxDriver();
            default:
                throw new IllegalArgumentException("Unknown browser: " + browser);
        }
    }


    // Метод-источник для параметров: браузер, номер вопроса, ожидаемый ответ
    private static Stream<Arguments> faqData() {
        return Stream.of(
                Arguments.of("chrome", "Сколько это стоит? И как оплатить?", "Сутки — 400 рублей. Оплата курьеру — наличными или картой."),
                Arguments.of("chrome", "Хочу сразу несколько самокатов! Так можно?", "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим."),
                Arguments.of("chrome", "Как рассчитывается время аренды?", "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30."),
                Arguments.of("chrome", "Можно ли заказать самокат прямо на сегодня?", "Только начиная с завтрашнего дня. Но скоро станем расторопнее."),
                Arguments.of("chrome", "Можно ли продлить заказ или вернуть самокат раньше?", "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010."),
                Arguments.of("chrome", "Вы привозите зарядку вместе с самокатом?", "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится."),
                Arguments.of("chrome", "Можно ли отменить заказ?", "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои."),
                Arguments.of("chrome", "Я живу за МКАДом, привезёте?", "Да, обязательно. Всем самокатов! И Москве, и Московской области."),
                Arguments.of("firefox", "Сколько это стоит? И как оплатить?", "Сутки — 400 рублей. Оплата курьеру — наличными или картой."),
                Arguments.of("firefox", "Хочу сразу несколько самокатов! Так можно?", "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим."),
                Arguments.of("firefox", "Как рассчитывается время аренды?", "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30."),
                Arguments.of("firefox", "Можно ли заказать самокат прямо на сегодня?", "Только начиная с завтрашнего дня. Но скоро станем расторопнее."),
                Arguments.of("firefox", "Можно ли продлить заказ или вернуть самокат раньше?", "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010."),
                Arguments.of("firefox", "Вы привозите зарядку вместе с самокатом?", "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится."),
                Arguments.of("firefox", "Можно ли отменить заказ?", "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои."),
                Arguments.of("firefox", "Я живу за МКАДом, привезёте?", "Да, обязательно. Всем самокатов! И Москве, и Московской области.")
        );
    }




    @ParameterizedTest
    @MethodSource("faqData")
    public void faqQuestionAnswerTest(String browser, String question, String expectedAnswer) {
        driver = getDriver(browser);
        driver.get(Config.baseUrl);
        MainPage mainPage = new MainPage(driver);


        // Скроллим к нужному вопросу
        String questionXpath = "//div[@class='accordion__button' and text()='" + question + "']";


        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView();",
                driver.findElement(org.openqa.selenium.By.xpath(questionXpath))
        );


        // Кликаем по вопросу и получаем ответ
        clickAccordion(mainPage, question);
        String actualAnswer = getAccordionAnswer(mainPage, question);


        assertEquals(expectedAnswer, actualAnswer, "Ответ не совпадает для вопроса #" + question + " в браузере " + browser);
    }


    // Метод для клика по вопросу
    private void clickAccordion(MainPage mainPage, String question) {
        switch (question) {
            case "Сколько это стоит? И как оплатить?": mainPage.clickButtonAccordionHeading0(); break;
            case "Хочу сразу несколько самокатов! Так можно?": mainPage.clickButtonAccordionHeading1(); break;
            case "Как рассчитывается время аренды?": mainPage.clickButtonAccordionHeading2(); break;
            case "Можно ли заказать самокат прямо на сегодня?": mainPage.clickButtonAccordionHeading3(); break;
            case "Можно ли продлить заказ или вернуть самокат раньше?": mainPage.clickButtonAccordionHeading4(); break;
            case "Вы привозите зарядку вместе с самокатом?": mainPage.clickButtonAccordionHeading5(); break;
            case "Можно ли отменить заказ?": mainPage.clickButtonAccordionHeading6(); break;
            case "Я живу за МКАДом, привезёте?": mainPage.clickButtonAccordionHeading7(); break;
            default: throw new IllegalArgumentException("Unknown question: " + question);
        }
    }


    // Метод для получения ответа
    private String getAccordionAnswer(MainPage mainPage, String question) {
        switch (question) {
            case "Сколько это стоит? И как оплатить?": return mainPage.getRegionAccordionPanel0();
            case "Хочу сразу несколько самокатов! Так можно?": return mainPage.getRegionAccordionPanel1();
            case "Как рассчитывается время аренды?": return mainPage.getRegionAccordionPanel2();
            case "Можно ли заказать самокат прямо на сегодня?": return mainPage.getRegionAccordionPanel3();
            case "Можно ли продлить заказ или вернуть самокат раньше?": return mainPage.getRegionAccordionPanel4();
            case "Вы привозите зарядку вместе с самокатом?": return mainPage.getRegionAccordionPanel5();
            case "Можно ли отменить заказ?": return mainPage.getRegionAccordionPanel6();
            case "Я живу за МКАДом, привезёте?": return mainPage.getRegionAccordionPanel7();
            default: throw new IllegalArgumentException("Unknown question: " + question);
        }
    }
}

