package steps;

import eshop.drivers.DriverManager;
import eshop.pages.LoginPage;
import eshop.pages.HomePage;
import eshop.utils.JsonUtils;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.openqa.selenium.WebDriver;

public class EshopSteps {

    private WebDriver driver;
    private JsonUtils testData;
    private LoginPage loginPage;
    private HomePage homePage;

    @Before
    public void setUp() {
        DriverManager.createInstance("chrome");
        driver = DriverManager.getDriver();
        testData = new JsonUtils("test-data");
        loginPage = new LoginPage(driver);
    }

    @After
    public void tearDown() {
        DriverManager.quitDriver();
    }

    @Given("the user navigates to the login page")
    public void the_user_navigates_to_login_page() {
        loginPage.navigateToLoginPage();
    }

    @When("the user logs in with valid credentials")
    public void the_user_logs_in_with_valid_credentials() {
        // بعد login، الـ LoginPage بترجع HomePage
        homePage = loginPage
                .closePopupsIfAny()
                .clickProfileIcon()
                .enterUsername(testData.getJsonData("login-credentials.username"))
                .enterPassword(testData.getJsonData("login-credentials.password"))
                .clickLoginButton()
                .assertSucessfulLogin(); // دلوقتي homePage محدث
    }

    @When("the user navigates to Laptop category")
    public void the_user_navigates_to_laptop_category() {
        homePage.clickLaptopCategory();
    }

    @When("the user adds {string} product to the cart")
    public void the_user_adds_product_to_the_cart(String productName) {
        // chain مع الـ homePage المحدث
        homePage.clickProductByKeyword(productName)
                .assertProductAddedToCart(productName);
    }

    @Then("the product {string} should be added to the cart")
    public void the_product_should_be_added_to_the_cart(String productName) {
        homePage.assertProductAddedToCart(productName);
    }
}
