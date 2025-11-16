import eshop.drivers.DriverManager;
import eshop.listeners.TestNGListeners;
import eshop.pages.LoginPage;
import eshop.utils.JsonUtils;
import eshop.utils.PropertiesUtils;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(TestNGListeners.class)
public class E2E {

    JsonUtils testData;
    WebDriver driver;

    @Test
    public void E2Eflow(){
        new LoginPage(DriverManager.getDriver())
                .closePopupsIfAny()
                .clickProfileIcon()
                .enterUsername(testData.getJsonData("login-credentials.username"))
                .enterPassword(testData.getJsonData("login-credentials.password"))
                .clickLoginButton()
                .assertSucessfulLogin()
                .clickLaptopCategory()
                .clickProductByKeyword(testData.getJsonData("product-names.item1.name"))
                .assertProductAddedToCart(testData.getJsonData("product-names.item1.name"))
                .searchAddAndAssert(testData.getJsonData("product-names.item2.name"))
                .clickLaptopCategory()
                .clickProductByKeyword(testData.getJsonData("product-names.item3.name"))
                .assertProductAddedToCart(testData.getJsonData("product-names.item3.name"));
    }


    @BeforeClass(alwaysRun = true)
    public void setup() {
        testData = new JsonUtils("test-data");
        String browserName = PropertiesUtils.getPropertyValue("browserType");
        DriverManager.createInstance(browserName);
        driver = DriverManager.getDriver();
        new LoginPage(driver).navigateToLoginPage();
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        DriverManager.getDriver().quit();
    }
}
