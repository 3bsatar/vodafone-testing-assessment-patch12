package eshop.drivers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

public class EdgeFactory extends AbstractDriver implements WebDriverOptionsAbstract<EdgeOptions> {

    @Override
    public EdgeOptions getOptions() {
        org.openqa.selenium.edge.EdgeOptions options = new org.openqa.selenium.edge.EdgeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--incognito");
        options.addArguments("--disable-infobars");
        options.addArguments("--disable-extensions");
        options.addArguments("--disable-gpu");
        options.addArguments("--no-sandbox");
        return options;
    }
    @Override
    public WebDriver startDriver() {
        return new EdgeDriver(getOptions());
    }
}
