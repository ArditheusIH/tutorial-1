package id.ac.ui.cs.advprog.eshop.functional;

import io.github.bonigarcia.seljup.SeleniumJupiter;
import org.junit.jupiter.api. BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest (webEnvironment = RANDOM_PORT)
@ExtendWith(SeleniumJupiter.class)
public class CreateProductFunctionalTest {
    /**
     * The port number assigned to the running application during test execution.
     * Set automatically during each test run by Spring Framework's test context.
     */
    @LocalServerPort private int serverPort;
    /**
     * The base URL for testing. Default to {@code http://localhost}.
     */
    @Value("${app.baseUrl:http://localhost}")
    private String testBaseUrl;
    private String baseUrl;
    @BeforeEach
    void setupTest() {
        baseUrl = String.format("%s:%d", testBaseUrl, serverPort);
    }

    @Test
    void createProductButtonAvailable(ChromeDriver driver) throws Exception {
        // Exercise
        String listUrl = baseUrl + "/product/list";
        driver.get(listUrl);

        //Verify
        WebElement createButton = driver.findElement(By.xpath("//*[text()='Create Product']"));
        assertDoesNotThrow(createButton::click);
    }
    @Test
    void createProductPageAvailable(ChromeDriver driver) throws Exception {
        String urlCreateProduct = baseUrl + "/product/create";

        //Exercise
        driver.get(urlCreateProduct);
        String pageTitle = driver.getTitle();

        //Verify
        assertEquals("Create New Product", pageTitle);

        WebElement inputProductName = driver.findElement(By.id("nameInput"));
        inputProductName.clear();
        String testName = "Susu Dancow";
        inputProductName.sendKeys(testName);

        WebElement inputProductQuantity = driver.findElement(By.id("quantityInput"));
        inputProductQuantity.clear();
        String testQuantity = "10";
        inputProductQuantity.sendKeys(testQuantity);

        WebElement submitButton = driver.findElement(By.xpath("//*[text()='Submit']"));
        assertDoesNotThrow(submitButton::click);
    }
    @Test
    void createdItemIsAdded(ChromeDriver driver) throws Exception {
        String listUrl = baseUrl + "/product/list";
        driver.get(listUrl);
        assertDoesNotThrow(() -> driver.findElement(By.xpath("//*[text()='Susu Dancow']")));
        assertDoesNotThrow(() -> driver.findElement(By.xpath("//*[text()='10']")));
    }

}