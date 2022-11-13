package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SearchBox extends pages.BasePage {

    By searchBoxLocator = By.id("twotabsearchtextbox");
    By submitButtonLocator = By.id("nav-search-submit-button");


    public SearchBox(WebDriver driver) {

        super(driver);
    }

    public void search(String text) {
        type(searchBoxLocator, text);
        click(submitButtonLocator);
    }
}