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
        homePage = loginPage
                .closePopupsIfAny()
                .clickProfileIcon()
                .enterUsername(testData.getJsonData("login-credentials.username"))
                .enterPassword(testData.getJsonData("login-credentials.password"))
                .clickLoginButton()
                .assertSucessfulLogin();
    }

    @When("the user navigates to Laptop category")
    public void the_user_navigates_to_laptop_category() {
        homePage.clickLaptopCategory();
    }

    @When("the user adds all products from test data to the cart")
    public void the_user_adds_all_products_from_test_data_to_the_cart() {
        // List of product keys in JSON
        String[] productKeys = {"item1", "item2", "item3"};

        for (String key : productKeys) {
            String productName = testData.getJsonData("product-names." + key + ".name");
            homePage.clickProductByKeyword(productName)
                    .assertProductAddedToCart(productName);
        }
    }

    @Then("all products should be added to the cart successfully")
    public void all_products_should_be_added_to_the_cart_successfully() {
        String[] productKeys = {"item1", "item2", "item3"};

        for (String key : productKeys) {
            String productName = testData.getJsonData("product-names." + key + ".name");
            homePage.assertProductAddedToCart(productName);
        }
    }
}
