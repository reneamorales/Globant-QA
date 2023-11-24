package com.example.definitions;

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
import org.testng.Assert;

import java.util.Random;

public class getFilmsSWDefinitions {
    private WebDriver driver;
    private String filmName;

    private Response response;

    @Dado("que soy un usuario que solicita una película aleatoria a la API de Star Wars")
    public void obtencionNombreDePeliculaAleatoria() {

        Random random = new Random();
        int peliculaRandom = random.nextInt(6) + 1;

        response = RestAssured.get("https://swapi.dev/api/films//" + peliculaRandom);

    }

    @Cuando("obtengo la respuesta de la API")
    public void extraccionDato() {
        filmName = response.jsonPath().getString("title");
        System.out.println(filmName);
    }

    @Entonces("busco el título de la película en Wikipedia")
    @Y("navego hasta la página del artículo correspondiente")
    public void busquedaDePeliculaEnWikipedia() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://wikipedia.org");
        WebElement inputSearch = driver.findElement(By.name("search"));
        inputSearch.sendKeys(filmName);
        inputSearch.submit();

    }

    @Cuando("hago clic en el botón de edición")
    public void nuevaPagina() {
        WebElement botonEdicion = driver.findElement(By.xpath("/html[1]/body[1]/div[2]/div[1]/div[3]/main[1]/div[1]/div[1]/div[2]/nav[1]/div[1]/div[1]/ul[1]/li[2]/a[1]"));
        botonEdicion.click();

    }

    @Entonces("compruebo que en el título de la página de edición incluye el título de la película")
    public void compueboElTituloDeLaPaginaConcideConElTitulo() {
        WebElement tituloPagina = driver.findElement(By.id("firstHeading"));

        if (tituloPagina != null && tituloPagina.getText().contains("Edición de" + filmName)) {
            System.out.println("El título de la página de edición incluye el título de la película.");
        } else {
            System.out.println("El título de la página de edición no incluye el título de la película.");
        }

        // Cierra el navegador
        driver.quit();
    }


}
