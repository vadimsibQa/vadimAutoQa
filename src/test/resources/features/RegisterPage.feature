Feature: Testing of Register Page

  Background:
    Given Set up driver

  Scenario Outline: Test register with valid data
    Given User is on Register Page
    When I enter first name "<firstName>"
    And I enter last name "<lastName>"
    And I enter birthdate "<birthdate>"
    And I enter email "<email>"
    And I enter password "<password>"
    And I enter confirm password "<confirmPassword>"
    And Submit the form
    Then I was redirected on main page
    Examples:
      | firstName | lastName | birthdate  | email                    | password  | confirmPassword |
      | Vadim111  | Sib111   | 06/06/2000 | vadimsib.qa113@gmail.com | 123456789 | 123456789       |


  Scenario Outline: Test register with invalid email
    Given User is on Register Page
    When I enter first name "<firstName>"
    And I enter last name "<lastName>"
    And I enter birthdate "<birthdate>"
    And I enter email "<email>"
    And I enter password "<password>"
    And I enter confirm password "<confirmPassword>"
    And Submit the form
    Then I can see email field error
    Examples:
      | firstName | lastName | birthdate  | email    | password  | confirmPassword |
      | Vadim111  | Sib111   | 06/06/2000 | vadimsib | 123456789 | 123456789       |

  Scenario Outline: Test register with invalid password
    Given User is on Register Page
    When I enter first name "<firstName>"
    And I enter last name "<lastName>"
    And I enter birthdate "<birthdate>"
    And I enter email "<email>"
    And I enter password "<password>"
    And I enter confirm password "<confirmPassword>"
    And Submit the form
    Then I can see password field error
    Examples:
      | firstName | lastName | birthdate  | email                    | password | confirmPassword |
      | Vadim111  | Sib111   | 06/06/2000 | vadimsib.qa113@gmail.com | 123      | 123             |
