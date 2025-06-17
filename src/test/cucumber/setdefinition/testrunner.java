package test.cucumber.setdefinition;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

//this testrunner java file  is similar to xml file , which we can use to run tests 
//we have added location of feature file and gluing setdefinition file 
//monochrome is used formatting of console output during test execution make it more readable
//setting up tag to regression means it runs only that feature file , like grouping concepts
//finally we are providing report file in a location 


@CucumberOptions(features="src/test/cucumber" , glue = "test.cucumber.setdefinition" ,
monochrome = true , tags = "@regression" ,plugin = {"html:target/cucumber.html"})
public class testrunner extends AbstractTestNGCucumberTests {

}
