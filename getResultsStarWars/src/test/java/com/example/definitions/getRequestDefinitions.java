package com.example.definitions;

import java.time.Duration;

import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import io.cucumber.java.es.Y;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;

import static io.restassured.RestAssured.given;


public class getRequestDefinitions {
    private WebDriver driver;
    private String characterName;

    @Dado("que soy usuario solicitando el personaje SW {int} de la API de Star Wars")
    public void que_soy_usuario_solicitando_el_personaje_sw_de_la_api_de_star_wars(Integer numero) {

        Response response = RestAssured.get("https://swapi.dev/api/people/" + numero);
        characterName = response.jsonPath().getString("name");
    }

    @Y("busco el nombre del personaje en la página de búsqueda de Wikipedia")
    public void nombreDePersonajeYBusquedaEnWikipedia() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://www.wikipedia.org");
        WebElement searchBox = driver.findElement(By.name("search"));
        searchBox.sendKeys(characterName);
        searchBox.submit();

    }

    @Entonces("^debería ser capaz de ver la página del artículo correctamente mostrada para el personaje$")
    public void comprobacionDeArticuloCorrecto() {
        WebElement pageTitle = driver.findElement(By.className("mw-page-title-main"));

        Assert.assertEquals(pageTitle.getText(), characterName);
        driver.close();
    }

    @AfterTest
    public void close() {
        driver.quit();

    }

}


