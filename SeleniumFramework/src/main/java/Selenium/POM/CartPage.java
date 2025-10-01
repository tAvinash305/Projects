package Selenium.POM;

import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CartPage {
	WebDriver driver;
	public CartPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".cartSection h3")
	List<WebElement> productsOnCartPage;
	
	@FindBy(css=".totalRow button")
	WebElement checkoutBtn;
	
	public List<WebElement> cartProducts() {
		List<WebElement> cartProducts = productsOnCartPage;
		return cartProducts;
	}
	
	public Boolean verifyProductAddedIsPresentInCartPage(String productName) {
		Boolean match = cartProducts().stream().anyMatch(cartProd -> cartProd.getText().equalsIgnoreCase(productName));
		return match;
	}
	
	public CheckoutPage goToCheckoutPage() {
		checkoutBtn.click();
		CheckoutPage checkoutPage = new CheckoutPage(driver);
		return checkoutPage;
	}
}
