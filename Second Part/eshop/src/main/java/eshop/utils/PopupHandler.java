package eshop.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PopupHandler {
    public static void closePopups(WebDriver driver) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

            WebElement spinClose = wait.until(ExpectedConditions.elementToBeClickable(By.className("close-modal-desktop")));
            spinClose.click();
        } catch (Exception e) {
            System.out.println("No spin popup found.");
        }

        try {
            WebElement cookieReject = driver.findElement(By.id("onetrust-reject-all-handler"));

            // scroll للعنصر لو محتاج
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", cookieReject);

            // click بواسطة JS عشان ما يتعطلش بسبب overlay
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", cookieReject);

            System.out.println("Cookies popup closed.");
        } catch (Exception e) {
            System.out.println("No cookies popup found.");
        }
    }

}
