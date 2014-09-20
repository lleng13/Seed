package per.jacobs.driver.test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import com.thoughtworks.selenium.webdriven.WebDriverBackedSelenium;

import per.jacobs.seed.Fruit2;

public class NewTest {

	public static void main(String[] args) {
		WebDriver driver = new FirefoxDriver();
		driver.get("http://www.baidu.com");
		//WebDriverBackedSelenium selenium = new WebDriverBackedSelenium(driver, "http://www.163.com");
		
		//selenium.open("http://study.163.com");
	}

}
