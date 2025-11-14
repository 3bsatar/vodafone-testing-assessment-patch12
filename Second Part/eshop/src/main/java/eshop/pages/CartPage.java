package eshop.pages;

import eshop.utils.CustomSoftAssertion;
import eshop.utils.ElementActions;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage {
    // locators
    private final By productName = By.cssSelector(".inventory_item_name");
    private final By productPrice = By.cssSelector(".inventory_item_price");
    private final By checkoutButton = By.cssSelector(".checkout_button");

    // Variables
    private WebDriver driver;

    // constructor
    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    // actions
    @Step("Get product name from cart")
    private String getProductName() {

        return ElementActions.getText(driver, productName);
    }

    @Step("Get product price from cart")
    private String getProductPrice() {
        return ElementActions.getText(driver, productPrice);
    }

    // validations
    @Step("Assert product details in cart")
    public CartPage assertProductDetails(String expectedProductName, String expectedProductPrice) {
        String actualProductName = getProductName();
        String actualProductPrice = getProductPrice();
        CustomSoftAssertion.softAssertion.assertEquals(actualProductName, expectedProductName, "Product name does not match");
        CustomSoftAssertion.softAssertion.assertEquals(actualProductPrice, expectedProductPrice, "Product price does not match");
        return this;
    }
}
