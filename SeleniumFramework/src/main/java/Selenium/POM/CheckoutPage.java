package Selenium.POM;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Selenium.AbstractComponents.AbstractComponent;

public class CheckoutPage extends AbstractComponent {
	WebDriver driver;
	public CheckoutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".form-group input")
	WebElement countryInputField;
	
	@FindBy(css=".ta-results button")
	List<WebElement> countryValues;
	
	@FindBy(css="[name='coupon']")
	WebElement couponInputField;
	
	@FindBy(xpath="//button[text()='Apply Coupon']")
	WebElement applyCouponBtn;
	
	@FindBy(css=".action__submit")
	WebElement placeOrderBtn;
	
	By countryDropdown = By.cssSelector(".ta-results");
	By couponAppliedMSG = By.cssSelector(".small p");
	
	public void selectCountry(String countryPartialName, String countryName) {
		Actions act = new Actions(driver);
		act.sendKeys(countryInputField, countryPartialName).build().perform();
		waitForElementVisibility(countryDropdown);
		List<WebElement> countries = countryValues;
		countries.stream().filter(country-> country.getText().equalsIgnoreCase(countryName)).findFirst().orElse(null).click();
	}
	
	public void applyCoupon(String coupon) {
		couponInputField.sendKeys(coupon);
		applyCouponBtn.click();
		waitForElementVisibility(couponAppliedMSG);
	}
	
	public ThankYouPage goToThankYouPage() {
		placeOrderBtn.click();
		ThankYouPage tyPage = new ThankYouPage(driver);
		return tyPage;
	}
}
