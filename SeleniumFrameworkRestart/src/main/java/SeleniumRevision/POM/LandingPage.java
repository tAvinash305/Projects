package SeleniumRevision.POM;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import SeleniumRevision.AbstractComponents.AbstractComponent;

public class LandingPage extends AbstractComponent {
	WebDriver driver;
	public LandingPage(WebDriver driver){
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void navigateToLoginPage() {
		driver.get("https://rahulshettyacademy.com/client/");
	}
	
	//	WebElement userEmail = driver.findElement(By.id("userEmail"));

	// PAGE FACTORY
	@FindBy(id="userEmail")
	WebElement userEmail;
	
	@FindBy(id="userPassword")
	WebElement userPassword;
	
	@FindBy(id="login")
	WebElement loginBtn;
	
	@FindBy(css="[class*='flyInOut']")
	WebElement errorMSG;
	
	public ProductCatalogPage loginToApplication(String email, String password) {
		userEmail.sendKeys(email);
		userPassword.sendKeys(password);
		loginBtn.click();
		ProductCatalogPage catalogPage = new ProductCatalogPage(driver);
		return catalogPage;
	}
	
	public String getErrorMSG() {
		waitForWebElementVisibility(errorMSG);
		return errorMSG.getText();
	}
}