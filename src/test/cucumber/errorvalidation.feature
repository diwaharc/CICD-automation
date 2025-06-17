#tag is not mandatory , unless we want to run any specific tests we can name them with different names like regression or errorhandling
@tag
Feature: error validation
  I want to use this template for my feature file


#as we can see here line in given tag is line from background tag in submitorder.feature file
#also line in when tag here is from submitorder.feature file
#we are re using above 2 lines , so that browser initilization and sending id and passwords objects can be used here as well and we can simply add only error message method in setdefinition class,
# to check only incorrect id and password validation
  @errorhandling
  Scenario Outline: checking for error message
    Given i landed  on  the ecommerce page
    When  logged into to the page with <username> and <password>
    Then  "Incorrect email or password." message is displayed 

    Examples: 
      | username              | password    |            
      | diwahar506@gmail.com  | Paassword@22|               
                                                    
