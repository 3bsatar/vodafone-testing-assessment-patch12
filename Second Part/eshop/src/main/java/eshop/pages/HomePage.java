package eshop.pages;

import eshop.utils.*;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.locators.RelativeLocator;

public class HomePage {
    // Variables
    private WebDriver driver;

    // Constructor
    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    // Locators
    private final By profileIcon = By.id("sl-nav-bar-img=1");

    private final By cartIcon = RelativeLocator
            .with(By.cssSelector("img[alt='shopping trolly']"))
            .toRightOf(profileIcon);

    private final By laptopText = By.xpath("//span[normalize-space()='Laptops']");
    private final By searchBar = By.id("searchInput");

    // Actions

    @Step("Click on first product containing keyword: {keyword}")
    public HomePage clickProductByKeyword(String keyword) {
        By productLocator = By.xpath("//p[contains(., '" + keyword + "')]");
        ElementActions.clickElementSafely(driver, productLocator);

        By addToCartButton = RelativeLocator
                .with(By.cssSelector("button.add-to-cart"))
                .toRightOf(productLocator);

        Waits.waitForElementClickable(driver, addToCartButton);

        ElementActions.clickElementSafely(driver, addToCartButton);

        ElementActions.clickElement(driver, cartIcon);
        return this;
    }


    @Step("Search for product {keyword}, add to cart and assert")
    public HomePage searchAddAndAssert(String keyword) {
        ElementActions.sendData(driver, searchBar, keyword + Keys.ENTER);
        Logsutil.info("Searched for: " + keyword + " and pressed Enter");

        clickProductByKeyword(keyword);

        assertProductAddedToCart(keyword);

        return this;
    }


    @Step("Click on cart icon")
    public HomePage clickLaptopCategory() {
        Logsutil.info("Clicking on Laptop");
        ElementActions.clickElement(driver, laptopText);
        return new HomePage(driver);
    }

    // Validations

    @Step("Assert product added to cart")
    public HomePage assertProductAddedToCart(String productName) {

        By productLocator = By.xpath("//p[contains(., '" + productName + "')]");

        By removeText = RelativeLocator
                .with(By.xpath(".//p[contains(., 'Remove')]"))
                .below(productLocator);

        WebElement removeElement = Waits.waitForElementVisible(driver, removeText);

        String actualValue = removeElement.getText().trim();

        Logsutil.info("Asserting product added to cart: "
                + productName + ", Actual Value: " + actualValue);

        Validations.validateEquals(
                actualValue,
                "Remove",
                "Product was not added to cart successfully"
        );

        Logsutil.info("Product added to cart successfully: " + productName);

        return this;
    }

}
