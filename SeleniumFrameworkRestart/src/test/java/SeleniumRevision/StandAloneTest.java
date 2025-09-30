package SeleniumRevision;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import io.github.bonigarcia.wdm.WebDriverManager;

public class StandAloneTest {
	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		String productName = "ADIDAS ORIGINAL";
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		
		driver.get("https://rahulshettyacademy.com/client/");
		driver.manage().window().maximize();
	
		// LOGIN PAGE
		driver.findElement(By.id("userEmail")).sendKeys("avinash123@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("a12345@A");
		driver.findElement(By.id("login")).click();
		
		// DASHBOARD PAGE
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
		WebElement prod = products.stream().filter(product-> product.findElement(By.cssSelector("b")).getText().equalsIgnoreCase(productName)).findFirst().orElse(null);
		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();		
		
//		for(int i=0; i<products.size(); i++) {
//			WebElement title = products.get(i).findElement(By.cssSelector("b"));
//			if(title.getText().contains(productName)) {
//				products.get(i).findElement(By.cssSelector(".card-body button:last-of-type")).click();
//				break;
//			}
//		}
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("toast-container")));
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		
		// CART PAGE
		driver.findElement(By.cssSelector("button[routerlink*='cart']")).click();
		List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));
		Boolean match = cartProducts.stream().anyMatch(cartProd -> cartProd.getText().equalsIgnoreCase(productName));
		Assert.assertTrue(match);
		driver.findElement(By.cssSelector(".totalRow button")).click();
		
		// CHECKOUT PAGE
		Actions act = new Actions(driver);
		act.sendKeys(driver.findElement(By.cssSelector(".form-group input")), "ind").build().perform();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
		
		List<WebElement> countries = driver.findElements(By.cssSelector(".ta-results button"));
		WebElement cnty = countries.stream().filter(country-> country.getText().equalsIgnoreCase("India")).findFirst().orElse(null);
		cnty.click();
		
		driver.findElement(By.cssSelector("[name='coupon']")).sendKeys("rahulshettyacademy");
		driver.findElement(By.xpath("//button[text()='Apply Coupon']")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".small p")));
		driver.findElement(By.cssSelector(".action__submit")).click();
		
		// THANK YOU PAGE
		String thankyouMSG = driver.findElement(By.cssSelector("td h1")).getText();
		Assert.assertTrue(thankyouMSG.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		
		driver.quit();
	}

}
