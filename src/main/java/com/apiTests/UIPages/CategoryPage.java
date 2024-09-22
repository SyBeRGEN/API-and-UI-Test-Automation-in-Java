package com.apiTests.UIPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CategoryPage {
    WebDriver driver;

    // Список карточек товаров по классу card-title
    @FindBy(className = "card-title")
    private List<WebElement> productTitles;

    // Кнопка "Add to cart"
    @FindBy(xpath = "//a[contains(text(), 'Add to cart')]")
    WebElement addToCartButton;

    public CategoryPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Метод для выбора первого товара в категории
    public void selectFirstProduct() {
        if (!productTitles.isEmpty()) {
            productTitles.get(0).click();  // Клик по первому товару
        }
    }

    // Метод для добавления товара в корзину
    public void addToCart() {
        addToCartButton.click();
    }
}