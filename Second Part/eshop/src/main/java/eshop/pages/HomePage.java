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
    @Step("Navigate to Home Page")
    public HomePage navigateToHomePage() {
        BrowserActions.navigateToURL(driver, PropertiesUtils.getPropertyValue("homePageURL"));
//        driver.get("https://www.saucedemo.com/inventory.html");
        return this;
    }

    @Step("Click on first product containing keyword: {keyword}")
    public HomePage clickProductByKeyword(String keyword) {
        By productLocator = By.xpath("//p[contains(., '" + keyword + "')]");
        ElementActions.clickElementSafely(driver, productLocator);

        By addToCartButton = RelativeLocator
                .with(By.cssSelector("button.add-to-cart"))
                .toRightOf(productLocator);

        Waits.waitForElementClickable(driver, addToCartButton);

        // Click on the Add To Cart button
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

    @Step("Add First product to cart")
    public HomePage addFirstElement() {
        // el Button elly below el productName as dynamic locator
        By addToCartButton = By.xpath("/html/body/vf-root/main/section[2]/vf-landing-page/section/vf-floating-section/section/div[2]/vf-products-container/section/div/vf-product-card[1]");
        ElementActions.clickElement(driver, addToCartButton);
        return new HomePage(driver);
    }

    // Validations

    @Step("Assert product added to cart")
    public HomePage assertProductAddedToCart(String productName) {

        // Locator للمنتج نفسه
        By productLocator = By.xpath("//p[contains(., '" + productName + "')]");

        // Locator لنص Remove اللي تحت المنتج
        By removeText = RelativeLocator
                .with(By.xpath(".//p[contains(., 'Remove')]"))
                .below(productLocator);

        // استنى لحد ما العنصر يكون ظاهر باستخدام Waits
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
