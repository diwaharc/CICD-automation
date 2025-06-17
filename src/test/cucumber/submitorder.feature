@tag
Feature: purchase the order form the ecommerce website
  I want to use this template for my feature file


#background keyword is used which is like before method in baseclass , which we used to initialize only browser and can be used by all the .feature file
Background:
Given i landed  on  the ecommerce page


#@regression is to mark the below to run specifially from tesrunner file(like grouping)
#we could have given scenario , reason for scenario outline is driven bt dynamic datas from below examples section
  @regression
  Scenario Outline: positive test of submitting the order 
    Given logged into to the page with <username> and <password>
    When  the product <product> is added to the cart
    And  Added <product> is checkedout and submitted
    Then  "THANKYOU FOR THE ORDER." message is displayed on confirmation page

    Examples: 
      | username              | password    | product           |
      | diwahar506@gmail.com  | Password@22 | ZARA COAT 3       |
                                                     
