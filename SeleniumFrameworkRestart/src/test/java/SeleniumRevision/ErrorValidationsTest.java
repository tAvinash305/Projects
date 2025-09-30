package SeleniumRevision;

import java.io.IOException;
import org.testng.Assert;
import org.testng.annotations.Test;
import SeleniumRevision.POM.CartPage;
import SeleniumRevision.POM.ProductCatalogPage;
import SeleniumRevision.TestComponents.BaseTest;

public class ErrorValidationsTest extends BaseTest {

	@Test(groups= {"ErrorHandling"})
	public void loginErrorValidations() {
		landingPage.loginToApplication("avinash123@gmail.com", "a12345@Avinash");
		Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMSG());
	}

	@Test
	public void ProductErrorValidations() throws IOException, InterruptedException {
		String productName = "ADIDAS ORIGINAL";

		ProductCatalogPage catalogPage = landingPage.loginToApplication("avinash123@gmail.com", "a12345@A");
		catalogPage.addProductToCart(productName);
		CartPage cartPage = catalogPage.goToCartPage();
		Boolean match = cartPage.verifyProductAddedIsPresentInCartPage(productName);
		Assert.assertTrue(match);
	}
}
