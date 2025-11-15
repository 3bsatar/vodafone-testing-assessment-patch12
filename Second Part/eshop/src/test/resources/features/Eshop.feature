Feature: Eshop End-to-End Flow

  Scenario: User logs in and adds all products from test data to cart
    Given the user navigates to the login page
    When the user logs in with valid credentials
    And the user navigates to Laptop category
    And the user adds all products from test data to the cart
    Then all products should be added to the cart successfully
