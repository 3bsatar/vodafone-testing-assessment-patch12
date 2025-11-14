# 📌 Interview Task

## 📍 Second Part

![Java](https://img.shields.io/badge/Language-Java-blue?logo=java)
![Maven](https://img.shields.io/badge/Build-Maven-orange?logo=apache-maven)
![TestNG](https://img.shields.io/badge/TestNG-Framework-yellow?logo=testng)
![Selenium](https://img.shields.io/badge/Selenium-WebDriver-brightgreen?logo=selenium)
![Allure](https://img.shields.io/badge/Allure-Reports-purple?logo=allure)
![Cucumber](https://img.shields.io/badge/Cucumber-BDD-green?logo=cucumber)


A **Web Automation Framework** developed using **Java**, **Selenium**, **TestNG**, and **Maven**.  
Implemented as part of the **Vodafone Testing Position – Assessment Task (Patch 12)** to automate end-to-end testing for the **Vodafone eShop application**, with a focus on **clean, maintainable code** and **integrated Allure Reports** for comprehensive test insights.


## 🛠 Tech Stack
- **Java 11+**
- **Maven**
- **TestNG**
- **Selenium WebDriver**
- **Allure Report**
- **Log4j2**
- **Cucumber**

## 📁 Project Structure
```bash
eshop/
├── src/
│   ├── main/java/eshop/
│   │   ├── drivers/          # WebDriver setup & factories
│   │   │   ├── AbstractDriver
│   │   │   ├── ChromeFactory
│   │   │   ├── DriverManager
│   │   │   ├── EdgeFactory
│   │   │   ├── FirefoxFactory
│   │   │   └── WebDriverOptionsAbstract
│   │   │
│   │   ├── listeners/        # TestNG listeners
│   │   │   └── TestNGListeners
│   │   │   └── WebManagerListener
│   │   │
│   │   ├── pages/            # Page Objects (POM)
│   │   │   ├── CartPage
│   │   │   ├── HomePage
│   │   │   ├── LoginPage
│   │   │
│   │   └── utils/            # Helper & utility classes
│   │       ├── AllureUtils
│   │       ├── BrowserActions
│   │       ├── CustomSoftAssertion
│   │       ├── ElementActions
│   │       ├── FileUtils
│   │       ├── JsonUtils
│   │       └── LogUtils
│   │       └── PopupHandler
│   │       └── PropertiesUtils
│   │       └── ScreenshotsUtils
│   │       └── Scrolling
│   │       └── TimestampUtils
│   │       └── Validations
│   │       └── Waits
│   │
│   └── resources/            # Config files
│       ├── allure.properties
│       ├── environment.properties
│       ├── log4j2.properties
│       └── web.properties
│
├── test/
│   ├── java/ # TestNG classes
│   │   ├── runners/
│   │   └── TestRunner
│   │
│   └── steps/               
│   │    └── EshopSteps
│   │
│   └── E2E
│   │           
│   └── resources/                
│       └── features/               
│       │   └── Eshop.feature
│       │
│       └── test-data.json  # Test data
│
├── test-outputs/
│   ├── allure-results/           # Allure raw data
│   ├── Logs/                     # Log4j2 logs
│   ├── screenshots/              # Test screenshots
│   └── target/                   # Compiled output & reports
│
├── pom.xml                       # Maven config & dependencies
```

   ## ▶️ Running Tests
  You can execute the tests using Maven:

  ```bash
  # Run all tests
  mvn clean test

  # Run a specific TestNG class (single test case)
  mvn clean test -Dtest=ClassName
  ```

  ## 📊 Generating & Viewing Allure Reports
  The framework integrates **Allure Reports** for rich test insights.

  ```bash
  # Generate Allure report after test execution
  allure generate test-outputs/allure-results/ -o test-outputs/allure-report/ --clean

  # Open Allure report in browser
  allure open test-outputs/allure-report/

  # OR serve Allure report directly
  allure serve test-outputs/allure-results/
  ```
  ## 📝 Notes
  - Ensure **Java 11+**, **Maven**, and **Allure CLI** are installed and added to your system PATH.
  - Chrome, Edge, or Firefox browsers must be installed for WebDriver tests.
  - Allure results are saved in `test-outputs/allure-results/`.
  - Screenshots of failed tests are stored in `test-outputs/screenshots/`.
  - Logs are available in `test-outputs/Logs/`.

  ## 👤 Author
  **Mahmoud Mesalem**

  - [LinkedIn](https://www.linkedin.com/in/mahmoud--mesalem)
  - [GitHub](https://github.com/3bsatar)

  ## 🤝 About This Submission
  This repository was prepared specifically for the 
  Vodafone Testing Position – Assessment Task (Patch 12).
  All work has been organized to make the review process
  clear and smooth. Thank you for taking the time to 
  review my submission.

  #### If any clarification is needed, I will be happy to provide it.

