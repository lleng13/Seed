/*
 * Copyright (c) 2014 all contributors
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 */
package com.netease.seed.driver.proxy;

import java.io.FileInputStream;
import java.util.Properties;

/**
 * Global Settings
 * @author ChenKan
 */
public class ProxySettings {

	public static Properties prop = getProperties();

	public static final String CHROME_DRIVER_PATH = prop.getProperty(getPropDriverPath(), "res/chromedriver_for_win.exe");
	
	public static final String IE_DRIVER_PATH = prop.getProperty("IEDriverPath", "res/iedriver_32.exe");

	public static final String STEP_INTERVAL = prop.getProperty("StepInterval", "500");

	public static final String TIMEOUT = prop.getProperty("Timeout", "30000");
	
	public static final String DEFAULT_BROWSER = prop.getProperty("DefaultBrowser", "chrome");
	
	public static String getProperty(String property) {
		return prop.getProperty(property);
	}
	
	public static Properties getProperties() {
		Properties prop = new Properties();
		try {
			FileInputStream file = new FileInputStream("prop.properties");
			prop.load(file);
			file.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return prop;
	}
	private static String getOsName() {
		String os = System.getProperty("os.name");
		String osArray[] = os.split(" ");
		return osArray[0];
	}
	private static String getPropDriverPath() {
		String on = getOsName();
		String path = null;
		if(on.equalsIgnoreCase("Windows")) {
			path = "ChromeDriverPathForWindows";
		} else if(on.equalsIgnoreCase("Mac")) {
			path = "ChromeDriverPathForMac";
		}
		return path;
	}
}