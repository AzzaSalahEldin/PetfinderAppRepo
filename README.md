# Selenium TestNG Automation Framework

This project is a TestNG-based Selenium automation framework designed to test the **Login** and **User Registration** functionalities of a web application. The test data is provided using CSV files through a custom DataProvider.
---

## ✅ Features

- **Selenium WebDriver** for browser automation
- **TestNG** framework for single/parallel test execution and annotations
- **CSV Data-Driven Testing** via custom `CSVDataProviders` utility
- Page Object Model (POM) design pattern for maintainable and scalable tests
- Modular test structure for Login and Registration flows
- Random email generation to avoid test collisions during registration
- Allure Reporting
- following best practices like Page Object Model, Singleton Pattern, Config Management, and Parallel Execution.

---

## 🧪 Test Cases

### 🔐 `LoginTest`

- `testLogin(email, password, expectedResult)`
  - Performs login using data from `loginData.csv`
  - Validates:
    - Successful login
    - Failed login

### 📝 `RegistrationTest`

- `testSuccessfulRegistration(...)`
  - Tests multiple valid and invalid registration flows:
    - Success
    - Invalid password format
    - Confirm password mismatch

- `testPasswordValidation()`
  - Checks:
    - Required field validations
    - Mismatched passwords

- `testEmailValidation()`
  - Validates:
    - Already registered emails
    - Invalid email format

- `testPersonalValidation()`
  - Validates:
    - Invalid zip code
    - First/Last name missing

---

## 🗂️ Data Files

Place your test data files in the following location:

/resources/test-data/
├── loginData.csv
└── registrationData.csv
**How to Run
Prerequisites:**
Java 8+
Maven
Chrome or Firefox installed
Internet connection

# Run via Maven

mvn clean test
#  Parallel Test Execution

**  <suite name="ParallelSuite" parallel="classes" thread-count="5">**

# Run via TestNG Suite 

mvn test -DsuiteXmlFile=testng.xml

# Generate Allure Report
	allure generate allure-results --clean -o allure-report
  allure open allure-report

