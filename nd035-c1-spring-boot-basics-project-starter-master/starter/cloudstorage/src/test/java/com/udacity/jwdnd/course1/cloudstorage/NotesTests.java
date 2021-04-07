package com.udacity.jwdnd.course1.cloudstorage;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class NotesTests {

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
		String username = "pzastoup";
		String password = "whatabadpassword";
		String messageText = "Hello!";

		driver.get(baseURL + "/signup");

		SignupPage signupPage = new SignupPage(driver);
		signupPage.signup("Peter", "Zastoupil", username, password);

		driver.get(baseURL + "/login");

		LoginPage loginPage = new LoginPage(driver);
		loginPage.login(username, password);
	}

	@AfterEach
	public void afterEach() throws InterruptedException {
		HomePage homePage = new HomePage(driver);
		assertEquals(true, homePage.onHomePage(driver));

		homePage.clickLogout();
		Thread.sleep(2000);
	}

	@Test
	public void testNotesCreationEditDelete() throws InterruptedException {
		String username = "pzastoup";
		String password = "whatabadpassword";
		String messageText = "Hello!";

		LoginPage loginPage = new LoginPage(driver);
		HomePage homePage = new HomePage(driver);
		assertTrue(homePage.onHomePage(driver));

		assertTrue(homePage.addNotes(driver, "Test1", "This is Test1"));
		driver.get(baseURL + "/home");
		homePage.clickLogout();

		driver.get(baseURL + "/login");

		loginPage.login(username, password);

		assertTrue(homePage.editNotes(driver, "Test1", "This is Test1"));

		driver.get(baseURL + "/home");
		assertTrue(homePage.deleteNotesCreds(driver, 1));
		driver.get(baseURL + "/home");
		assertFalse(homePage.verifyEmptySets(driver, 1));
		driver.get(baseURL + "/home");
	}

}
