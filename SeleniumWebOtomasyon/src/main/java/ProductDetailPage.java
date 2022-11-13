package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class ProductDetailPage extends pages.BasePage {

    By addToCartButtonLocator = By.id("add-to-cart-button");


    public ProductDetailPage(WebDriver driver) {

        super(driver);
    }

    public boolean isOnProductDetailPage() {

        return isDisplayed(addToCartButtonLocator);
    }

    public void addToCart() {
        click(addToCartButtonLocator);
    }

    public void selectSize(int i) {
        Select dropDown = new Select(driver.findElement(By.id("native_dropdown_selected_size_name")));
        dropDown.selectByIndex(i);
    }
}