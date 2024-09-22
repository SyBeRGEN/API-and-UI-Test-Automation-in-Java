package com.apiTests;

import com.apiTests.UIPages.*;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class FullUITest extends BaseTest {

    private final Faker faker = new Faker();
    private final Duration waitTime = Duration.ofSeconds(10);
    private final String username = faker.name().username();
    private final String password = faker.internet().password();

    @Test
    public void fullTest() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, waitTime);

        // 1. Регистрация
        registerUser(wait);

        // 2. Логин
        loginUser(wait);

        // 3. Добавление товаров в корзину по категориям
        addItemToCart(wait, "Phones");
        addItemToCart(wait, "Laptops");
        addItemToCart(wait, "Monitors");

        // 4. Переход в корзину и проверка общей цены
        goToCartAndVerifyTotal(wait);

        // 5. Оформление заказа
        placeOrder();

        // 6. Проверка даты заказа
        verifyOrderDate();
    }

    // Метод регистрации
    private void registerUser(WebDriverWait wait) {
        HomePage homePage = new HomePage(driver);
        homePage.clickSignup();

        WebElement usernameField = wait.until(ExpectedConditions.elementToBeClickable(By.id("sign-username")));
        WebElement passwordField = wait.until(ExpectedConditions.elementToBeClickable(By.id("sign-password")));
        usernameField.sendKeys(username);
        passwordField.sendKeys(password);

        WebElement signupButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Sign up']")));
        signupButton.click();
        handleAlert(wait);
    }

    // Метод логина
    private void loginUser(WebDriverWait wait) throws InterruptedException {
        HomePage homePage = new HomePage(driver);
        homePage.clickLogin();

        WebElement loginUsernameField = wait.until(ExpectedConditions.elementToBeClickable(By.id("loginusername")));
        WebElement loginPasswordField = wait.until(ExpectedConditions.elementToBeClickable(By.id("loginpassword")));
        loginUsernameField.sendKeys(username);
        loginPasswordField.sendKeys(password);

        WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Log in']")));
        loginButton.click();
        Thread.sleep(3000); // Ждем логина
    }

    // Метод для добавления товаров в корзину
    private void addItemToCart(WebDriverWait wait, String category) throws InterruptedException {
        HomePage homePage = new HomePage(driver);
        homePage.selectCategory(category);
        Thread.sleep(3000);

        CategoryPage categoryPage = new CategoryPage(driver);
        categoryPage.selectFirstProduct();
        Thread.sleep(5000); // Ждем загрузки карточки товара

        categoryPage.addToCart();
        handleAlert(wait);

        driver.navigate().back();
        driver.navigate().back();
        Thread.sleep(3000); // Возвращаемся на страницу категорий
    }

    // Метод для перехода в корзину и проверки общей цены
    private void goToCartAndVerifyTotal(WebDriverWait wait) throws InterruptedException {
        WebElement cartButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("cartur")));
        cartButton.click();
        Thread.sleep(5000); // Ждем загрузки страницы корзины

        CartPage cartPage = new CartPage(driver);
        int calculatedTotal = cartPage.calculateTotal();
        int displayedTotal = cartPage.getTotalPrice();

        Assertions.assertEquals(calculatedTotal, displayedTotal, "Цены в корзине не совпадают!");
    }

    // Метод для оформления заказа
    private void placeOrder() throws InterruptedException {
        CartPage cartPage = new CartPage(driver);
        cartPage.clickPlaceOrder();
        Thread.sleep(2000);

        CheckoutPage checkoutPage = new CheckoutPage(driver);
        checkoutPage.fillOrderForm(
                faker.name().fullName(),           // Name
                faker.country().name(),            // Country
                faker.address().city(),            // City
                faker.finance().creditCard(),      // Credit Card
                String.valueOf(LocalDate.now().getMonthValue()),  // Month
                String.valueOf(LocalDate.now().getYear())         // Year
        );
        checkoutPage.clickPurchaseButton();
        Thread.sleep(2000); // Ждем завершения оформления
    }

    // Метод для проверки даты заказа
    private void verifyOrderDate() {
        CheckoutPage checkoutPage = new CheckoutPage(driver);
        String orderDate = checkoutPage.getOrderDate();

        // Получаем сегодняшнюю дату с вычитанием одного месяца
        String todayDate = LocalDate.now().minusMonths(1).format(DateTimeFormatter.ofPattern("d/M/yyyy"));
        Assertions.assertEquals(orderDate, todayDate, "Дата заказа не совпадает с текущей датой!");
    }

    // Метод для обработки всплывающих окон (alert)
    private void handleAlert(WebDriverWait wait) {
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        alert.accept();
    }
}
