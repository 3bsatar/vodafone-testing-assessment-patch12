Feature: Eshop End-to-End Flow

  Scenario: User logs in and adds ASUS laptop to cart
    Given the user navigates to the login page
    When the user logs in with valid credentials
    And the user navigates to Laptop category
    And the user adds "ASUS" product to the cart
    Then the product "ASUS" should be added to the cart
