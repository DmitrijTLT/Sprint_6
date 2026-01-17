package ru.yandex.practicum;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class MainPage extends BasePage {
    //-----------------------------Вопросы и ответы------------------------------------
    //---------------------------------------------------------------------------------
    // Вопрос: "Сколько это стоит? И как оплатить?"
    @FindBy(id = "accordion__heading-0")
    private WebElement buttonAccordionHeading0;


    // Ответ: "Сутки — 400 рублей. Оплата курьеру — наличными или картой."
    @FindBy(id = "accordion__panel-0")
    private WebElement regionAccordionPanel0;


    //---------------------------------------------------------------------------------
    // Вопрос: "Хочу сразу несколько самокатов! Так можно?"
    @FindBy(id = "accordion__heading-1")
    private WebElement buttonAccordionHeading1;


    // Ответ: "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим."
    @FindBy(id = "accordion__panel-1")
    private WebElement regionAccordionPanel1;


    //---------------------------------------------------------------------------------
    // Вопрос: "Как рассчитывается время аренды?"
    @FindBy(id = "accordion__heading-2")
    private WebElement buttonAccordionHeading2;


    // Ответ: "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30."
    @FindBy(id = "accordion__panel-2")
    private WebElement regionAccordionPanel2;


    //---------------------------------------------------------------------------------
    // Вопрос: "Можно ли заказать самокат прямо на сегодня?"
    @FindBy(id = "accordion__heading-3")
    private WebElement buttonAccordionHeading3;


    // Ответ: "Только начиная с завтрашнего дня. Но скоро станем расторопнее."
    @FindBy(id = "accordion__panel-3")
    private WebElement regionAccordionPanel3;


    //---------------------------------------------------------------------------------
    // Вопрос: "Можно ли продлить заказ или вернуть самокат раньше?"
    @FindBy(id = "accordion__heading-4")
    private WebElement buttonAccordionHeading4;


    // Ответ: "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010."
    @FindBy(id = "accordion__panel-4")
    private WebElement regionAccordionPanel4;


    //---------------------------------------------------------------------------------
    // Вопрос: "Вы привозите зарядку вместе с самокатом?"
    @FindBy(id = "accordion__heading-5")
    private WebElement buttonAccordionHeading5;


    // Ответ: "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится."
    @FindBy(id = "accordion__panel-5")
    private WebElement regionAccordionPanel5;


    //---------------------------------------------------------------------------------
    // Вопрос: "Можно ли отменить заказ?"
    @FindBy(id = "accordion__heading-6")
    private WebElement buttonAccordionHeading6;


    // Ответ: "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои."
    @FindBy(id = "accordion__panel-6")
    private WebElement regionAccordionPanel6;


    //---------------------------------------------------------------------------------
    // Вопрос: "Я живу за МКАДом, привезёте?"
    @FindBy(id = "accordion__heading-7")
    public WebElement buttonAccordionHeading7;


    // Ответ: "Да, обязательно. Всем самокатов! И Москве, и Московской области."
    @FindBy(id = "accordion__panel-7")
    private WebElement regionAccordionPanel7;


    //---------------------------------------------------------------------------------


    // Конструктор класса
    public MainPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }


    //-----------Методы имитирующие клик по стрелке-------------------------------------
    public void clickButtonAccordionHeading0(){
        buttonAccordionHeading0.click();
    }


    public void clickButtonAccordionHeading1(){
        buttonAccordionHeading1.click();
    }


    public void clickButtonAccordionHeading2(){
        buttonAccordionHeading2.click();
    }


    public void clickButtonAccordionHeading3(){
        buttonAccordionHeading3.click();
    }


    public void clickButtonAccordionHeading4(){
        buttonAccordionHeading4.click();
    }


    public void clickButtonAccordionHeading5(){
        buttonAccordionHeading5.click();
    }


    public void clickButtonAccordionHeading6(){
        buttonAccordionHeading6.click();
    }


    public void clickButtonAccordionHeading7(){
        buttonAccordionHeading7.click();
    }


    //-----------Методы возвращающие ответы на вопросы----------------------------------
    public String getRegionAccordionPanel0() {
        return regionAccordionPanel0.getText();
        //return driver.findElement(regionAccordionPanel0).getText();
    }


    public String getRegionAccordionPanel1() {
        return regionAccordionPanel1.getText();
    }


    public String getRegionAccordionPanel2() {
        return regionAccordionPanel2.getText();
    }


    public String getRegionAccordionPanel3() {
        return regionAccordionPanel3.getText();
    }


    public String getRegionAccordionPanel4() {
        return regionAccordionPanel4.getText();
    }


    public String getRegionAccordionPanel5() {
        return regionAccordionPanel5.getText();
    }


    public String getRegionAccordionPanel6() {
        return regionAccordionPanel6.getText();
    }


    public String getRegionAccordionPanel7() {
        return regionAccordionPanel7.getText();
    }
}

