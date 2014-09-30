package com.netease.seed.test;

import java.util.Properties;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.netease.seed.driver.proxy.SeedProxy;
import com.thoughtworks.selenium.webdriven.WebDriverBackedSelenium;

public class SeedTest {
	
	@Test
	@Parameters("browser")
	public void f(String browser){
		SeedProxy f = new SeedProxy(browser);
		f.open("http://study.163.com");
		f.click("//hadf");
	}
}
