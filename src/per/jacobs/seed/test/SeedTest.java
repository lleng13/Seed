package per.jacobs.seed.test;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

import com.thoughtworks.selenium.webdriven.WebDriverBackedSelenium;

import per.jacobs.seed.Fruit;

public class SeedTest {
	
	
	public static void main(String[] args) {
		Fruit f = new Fruit();
		f.open("http://study.163.com");
		
	}
}
