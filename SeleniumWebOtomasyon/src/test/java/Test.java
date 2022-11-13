import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.openqa.selenium.*;
import pages.CartPage;
import pages.HomePage;
import pages.ProductDetailPage;
import pages.ProductsPage;
import java.io.FileReader;
import java.io.IOException;


public class Test extends BaseTest {

    HomePage homePage ;
    ProductsPage productsPage ;
    ProductDetailPage productDetailPage ;
    CartPage cartPage ;

    @org.junit.jupiter.api.Test
    @Order(1)
    public void productSearch(){
        homePage = new HomePage(driver);
        homePage.acceptCookies();
        productsPage = new ProductsPage(driver);
        homePage.searchBox().search("Ceket");
        Assertions.assertTrue(productsPage.isOnProductPage(),"Not on products page!");
    }

    @org.junit.jupiter.api.Test
    @Order(2)
    public void productSelection() throws InterruptedException {
        productDetailPage = new ProductDetailPage(driver);
        driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL, Keys.END);
        String firstPageUrl = driver.getCurrentUrl();
        driver.findElement(By.linkText("Sonraki")).click();
        Thread.sleep(2000);
        String secondPageUrl = driver.getCurrentUrl();
        if (firstPageUrl == secondPageUrl){
            System.out.println("Url didin't changed so page remains same.");
            driver.quit();
        }
        productsPage.selectProduct(0);
        Assertions.assertTrue(productDetailPage.isOnProductDetailPage(),"Not on product detail page!");
    }

    @org.junit.jupiter.api.Test
    @Order(3)
    public void addToCart() throws InterruptedException {
        productDetailPage.selectSize(1);
        Thread.sleep(2000);
        productDetailPage.addToCart();
        Assertions.assertTrue(homePage.isProductCountUp(),"Product count did not increase!");
    }

    @org.junit.jupiter.api.Test
    @Order(4)
    public void goToCart(){
        cartPage = new CartPage(driver);
        homePage.goToCart();
    }

    @org.junit.jupiter.api.Test
    @Order(5)
    public void goToSignIn() {
        homePage.proceedToCheckout();
    }

    @org.junit.jupiter.api.Test
    @Order(6)
    public void signIn() throws IOException, CsvValidationException {
        csvReader = new CSVReader(new FileReader(CSV_PATH));

        while ((csvCell = csvReader.readNext()) != null){
            String customerEmail = csvCell[0];
            String customerPassword = csvCell[1];
            driver.findElement(By.id("ap_email")).sendKeys(customerEmail);
            driver.findElement(By.id("continue")).click();
            driver.findElement(By.id("ap_password")).sendKeys(customerPassword);

            try {
                driver.findElement(By.id("auth-signin-button"));
                present = true;
            } catch (NoSuchElementException e) {
                present = false;
            }
            if (present==true) {
                driver.findElement(By.id("auth-signin-button")).click();
            }
            else{
                System.out.println("Button didin't appear");
                driver.quit();
            }
        }
    }

    @org.junit.jupiter.api.Test
    @Order(7)
    public void clickMainLogo(){
        driver.findElement(By.id("nav-logo-sprites")).click();
    }

    @org.junit.jupiter.api.Test
    @Order(8)
    public void clickCartLogo() throws InterruptedException {
        driver.findElement(By.id("nav-cart-count-container")).click();
        Thread.sleep(1000);
        driver.findElement(By.className("a-color-link")).click();
        Thread.sleep(2000);
        try {
            driver.findElement(By.className("a-color-link"));
            present = true;
        } catch (NoSuchElementException e) {
            present = false;
        }
        if (present!=true) {
            System.out.println("card is not empty");
            driver.quit();
        }
    }
}