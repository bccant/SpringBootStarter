package com.udacity.jwdnd.course1.cloudstorage;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class LoginTests {

	@LocalServerPort
	private int port;
	public String baseURL;

	public static WebDriver driver;

	@BeforeAll
	public static void beforeAll() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
	}

	@AfterAll
	public static void afterAll() {
		driver.quit();
		driver = null;
	}

	@BeforeEach
	public void beforeEach() {
		baseURL = baseURL = "http://localhost:" + port;
	}

	@Test
	public void testLoginPage01() {
		String username = "pzastoup";
		String password = "whatabadpassword";
		String messageText = "Hello!";

//		driver.get(baseURL + "/signup");
//
//		SignupPage signupPage = new SignupPage(driver);
//		signupPage.signup("Peter", "Zastoupil", username, password);

		driver.get(baseURL + "/home");

		LoginPage loginPage = new LoginPage(driver);
		assertEquals(true, loginPage.onLoginPage(driver));

		HomePage homePage = new HomePage(driver);
		assertFalse(homePage.onHomePage(driver));

		loginPage.login(username, password);
		assertTrue(loginPage.onLoginError());
	}

	@Test
	public void testLoginPage02() throws InterruptedException {
		String username = "pzastoup";
		String password = "whatabadpassword";
		String messageText = "Hello!";

		driver.get(baseURL + "/signup");

		SignupPage signupPage = new SignupPage(driver);
		signupPage.signup("Peter", "Zastoupil", username, password);

		driver.get(baseURL + "/login");

		LoginPage loginPage = new LoginPage(driver);
		loginPage.login(username, password);

		HomePage homePage = new HomePage(driver);
		assertEquals(true, homePage.onHomePage(driver));

		homePage.clickLogout();
		Thread.sleep(2000);

		assertFalse(homePage.onHomePage(driver));
		assertTrue(loginPage.onLoginPage(driver));
	}

}
