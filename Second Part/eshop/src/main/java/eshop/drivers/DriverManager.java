package eshop.drivers;

import org.openqa.selenium.WebDriver;

public class DriverManager {
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static void createInstance(String browserName) {
        if (driver.get() == null) { // لو ما فيش instance موجودة
            if (browserName.equalsIgnoreCase("chrome")) {
                ChromeFactory chromeFactory = new ChromeFactory();
                driver.set(chromeFactory.startDriver());
            }
            // ممكن تضيف Firefox, Edge هنا بعدين
        }
    }

    public static WebDriver getDriver() {
        return driver.get();
    }

    public static void quitDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }
}
