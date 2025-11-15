package eshop.drivers;

import org.openqa.selenium.WebDriver;

public class DriverManager {
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static void createInstance(String browserName) {
        if (driver.get() == null) {
            if (browserName.equalsIgnoreCase("chrome")) {
                ChromeFactory chromeFactory = new ChromeFactory();
                driver.set(chromeFactory.startDriver());
            }
            else if (browserName.equalsIgnoreCase("firefox")) {
                FirefoxFactory firefoxFactory = new FirefoxFactory();
                 driver.set(firefoxFactory.startDriver());
             }
             else if (browserName.equalsIgnoreCase("edge")) {
                 EdgeFactory edgeFactory = new EdgeFactory();
                 driver.set(edgeFactory.startDriver());
             }
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
