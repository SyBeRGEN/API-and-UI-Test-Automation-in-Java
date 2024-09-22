package com.apiTests.UIPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class HomePage {
    WebDriver driver;

    @FindBy(xpath = "//a[text()='Sign up']")
    WebElement signupButton;

    @FindBy(xpath = "//a[text()='Log in']")
    WebElement loginButton;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void clickSignup() {
        signupButton.click();
    }

    public void clickLogin() {
        loginButton.click();
    }

    // Элементы категорий по классу list-group-item
    @FindBy(className = "list-group-item")
    private List<WebElement> categories;

    // Метод для выбора категории по её названию (например, "Phones", "Laptops", "Monitors")
    public void selectCategory(String categoryName) {
        for (WebElement category : categories) {
            if (category.getText().equalsIgnoreCase(categoryName)) {
                category.click();
                break;
            }
        }
    }
}