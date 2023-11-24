package com.example.definitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;

import java.sql.Driver;
import java.util.Random;

public class getMovieAndSearchOnWikipediaDefinitions {
    private WebDriver driver;
    private String filmName;
    private Response response;

    @Given("I am a user requesting a random movie from the Star Wars API")
    public void RequestARandomMovieName() {

        Random random = new Random();
        int filmRandom = random.nextInt(6) + 1;
        response = RestAssured.get("https://swapi.dev/api/films//" + filmRandom);

    }

    @When("I get the API response")
    public void getTheAPIResponse(){
        filmName= response.jsonPath().getString("title");

    }

    @Then("I look up the title of the film on Wikipedia")
    @And("I navigate to the corresponding article page")
    public void SearchForTheMovieTitleOnWikipedia(){
        WebDriverManager.chromedriver().setup();
        ChromeOptions options =  new ChromeOptions();
        options.addArguments("--start-maximized");
        driver = new ChromeDriver();
        driver.get("https://en.wikipedia.org/");

        WebElement searchInput = driver.findElement(By.name("search"));
        searchInput.sendKeys(filmName);
        searchInput.submit();
    }

    @When("I click on the view history button")
    public void clickOnViewHistory(){
        WebElement ancorViewHistory= driver.findElement(By.cssSelector("a[accesskey='h']"));
        ancorViewHistory.click();
    }

    @Then("I check that the title of the view story page includes the name of the film")
    public void checkArticleviewHistoryIncludeNameFilm(){
        System.out.println(filmName);
        System.out.println(driver.getTitle());
        Assert.assertTrue(driver.getTitle().contains(filmName));
    }

}
