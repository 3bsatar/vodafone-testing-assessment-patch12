package eshop.utils;

import io.qameta.allure.Step;
import org.openqa.selenium.*;


public class ElementActions {
    private ElementActions() {
    }

    @Step("Click on element safely (handles click interception): {locator}")
    public static void clickElementSafely(WebDriver driver, By locator) {
        Waits.waitForElementClickable(driver, locator);
        Scrolling.scrollToElement(driver, locator);

        try {
            findElement(driver, locator).click();
        } catch (ElementClickInterceptedException e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", findElement(driver, locator));
            Logsutil.info("Clicked on element via JS due to interception: " + locator.toString());
        }

        Logsutil.info("Clicked on element safely: " + locator.toString());
    }

    // sendKeys
    @Step("Send data to element: {locator} with data: {data}")
    public static void sendData(WebDriver driver, By locator, String data) {

        Waits.waitForElementVisible(driver, locator);
        Scrolling.scrollToElement(driver, locator);
        findElement(driver, locator).sendKeys(data);
        Logsutil.info("Data entered: ", data, "in the field: ", locator.toString());
    }

    // Click
    @Step("Click on element: {locator}")
    public static void clickElement(WebDriver driver, By locator) {

        Waits.waitForElementClickable(driver, locator);
        Scrolling.scrollToElement(driver, locator);
        findElement(driver, locator).click();
        Logsutil.info("Clicked on element: ", locator.toString());
    }

    @Step("Get text from element: {locator}")
    public static String getText(WebDriver driver, By locator) {
        Waits.waitForElementVisible(driver, locator);
        Scrolling.scrollToElement(driver, locator);
        Logsutil.info("CGetting text from element: ", locator.toString(), "Text: ", findElement(driver, locator).getText());
        return findElement(driver, locator).getText();
    }

    public static WebElement findElement(WebDriver driver, By locator) {
        return driver.findElement(locator);
    }

    public static String getTextFromInput(WebDriver driver, By locator) {
        Waits.waitForElementVisible(driver, locator);
        Scrolling.scrollToElement(driver, locator);
        Logsutil.info("Getting text from input element: ", locator.toString(), "Text: ", findElement(driver, locator).getDomAttribute("value"));
        return findElement(driver, locator).getDomAttribute("value");
    }
}
