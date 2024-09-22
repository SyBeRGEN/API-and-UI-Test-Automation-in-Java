package com.apiTests.UIPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class CartPage {
    WebDriver driver;

    @FindBy(xpath = "//td[3]")
    List<WebElement> productPrices;

    @FindBy(xpath = "//h3[@id='totalp']")
    WebElement totalPrice;

    @FindBy(css = "button.btn.btn-success[data-target='#orderModal']")
    WebElement placeOrderButton;

    public CartPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public int calculateTotal() {
        int sum = 0;
        for (WebElement price : productPrices) {
            sum += Integer.parseInt(price.getText());
        }
        return sum;
    }

    public int getTotalPrice() {
        return Integer.parseInt(totalPrice.getText());
    }

    public void clickPlaceOrder() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Нажимаем на кнопку "Place Order"
        WebElement placeOrderBtn = wait.until(ExpectedConditions.elementToBeClickable(placeOrderButton));
        placeOrderBtn.click();

        // Ожидаем появления модального окна (форма должна появиться после нажатия)
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("orderModal")));
    }
}
