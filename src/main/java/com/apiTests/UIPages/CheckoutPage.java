package com.apiTests.UIPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutPage {

    WebDriver driver;

    // Локаторы для полей на странице оформления заказа
    @FindBy(id = "name")
    private WebElement nameField;

    @FindBy(id = "country")
    private WebElement countryField;

    @FindBy(id = "city")
    private WebElement cityField;

    @FindBy(id = "card")
    private WebElement creditCardField;

    @FindBy(id = "month")
    private WebElement monthField;

    @FindBy(id = "year")
    private WebElement yearField;

    @FindBy(xpath = "//button[text()='Purchase']")
    private WebElement purchaseButton;

    // Локатор для получения информации о заказе после оформления
    @FindBy(xpath = "//p[@class='lead text-muted ']")
    private WebElement orderConfirmationDetails;

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Заполнение формы заказа
    public void fillOrderForm(String name, String country, String city, String creditCard, String month, String year) {
        nameField.sendKeys(name);
        countryField.sendKeys(country);
        cityField.sendKeys(city);
        creditCardField.sendKeys(creditCard);
        monthField.sendKeys(month);
        yearField.sendKeys(year);
    }

    // Клик по кнопке "Purchase" для подтверждения заказа
    public void clickPurchaseButton() {
        purchaseButton.click();
    }

    // Метод для получения информации о заказе после оформления
    public String getOrderDate() {
        String confirmationText = orderConfirmationDetails.getText();
        // Проверяем содержимое текста, чтобы убедиться, что данные корректны
        System.out.println(confirmationText);  // Добавьте это для отладки, чтобы увидеть полный текст

        // Ищем строку с датой
        if (confirmationText.contains("Date:")) {
            return confirmationText.split("Date: ")[1].trim();
        }
        return null;  // Если "Date:" не найдено
    }
}