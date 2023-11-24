package runner;

import io.cucumber.java.AfterAll;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

import org.openqa.selenium.WebDriver;


@CucumberOptions(tags = "", features = {"src/test/java/resources/features/getRequest.feature"}, glue = {"com.example.definitions"})

public class cucumberRunnerTest extends AbstractTestNGCucumberTests {

}

/*getFilmsSW.feature*/
/*getRequest.feature*/
/*getMovieAndSearchOnWikipedia*/
