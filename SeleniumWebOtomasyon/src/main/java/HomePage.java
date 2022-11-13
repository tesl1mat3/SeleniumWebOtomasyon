package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
public class HomePage extends pages.BasePage {

    pages.SearchBox searchBox ;
    By cartCountLocator = By.id("nav-cart-count");
    By cartContainerLocator = By.id("nav-cart-count-container");
    By acceptCookiesLocator = By.id("sp-cc-accept");
    By checkOutLocater = By.id("nav-link-accountList-nav-line-1");

    public HomePage(WebDriver driver) {
        super(driver);
        searchBox = new pages.SearchBox(driver);
    }

    public pages.SearchBox searchBox(){
        return this.searchBox;
    }

    public boolean isProductCountUp() {
        return getCartCount() > 0 ;
    }

    public void goToCart() {
        click(cartContainerLocator);
    }

    public void proceedToCheckout() { click(checkOutLocater); }

    private int getCartCount(){
        String count = find(cartCountLocator).getText();
        return Integer.parseInt(count);
    }

    public void acceptCookies(){
        if (isDisplayed(acceptCookiesLocator)){
            click(acceptCookiesLocator);
        }
    }
}