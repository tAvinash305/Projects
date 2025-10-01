package Selenium.POM;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Selenium.AbstractComponents.AbstractComponent;

public class ProductCatalogPage extends AbstractComponent {
	WebDriver driver;
	public ProductCatalogPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".mb-3")
	List<WebElement> products;
	
	@FindBy(css=".ng-animating")
	WebElement spinner;
	
	
	By productsBy = By.cssSelector(".mb-3");
	By addToCartBtn = By.cssSelector(".card-body button:last-of-type");
	By toastContainer = By.id("toast-container");
	
	public List<WebElement> getProductList() {
		waitForElementVisibility(productsBy);
		return products;
	}

	public WebElement getProductByName(String productName) {
		return getProductList().stream().filter(product-> product.findElement(By.cssSelector("b")).getText().equalsIgnoreCase(productName)).findFirst().orElse(null);
	}
	
	public void addProductToCart(String productName) {
		getProductByName(productName).findElement(addToCartBtn).click();
		waitForElementVisibility(toastContainer);
		waitForElementToDisappear(spinner);
	}
}
