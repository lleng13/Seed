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
package per.jacobsLin.seed;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.Augmenter;

/**
 * Log Tools
 * @author ChenKan
 */
public class LogTools {
	public static void log(String logText) {
		System.out.println("[" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(System.currentTimeMillis())) + "] " + logText);
	}

	public static String screenShot(Fruit fruit) {
		String dir = "screenshot"; 
		String time = new SimpleDateFormat("yyyyMMdd-HHmmss").format(new Date());
		String screenShotPath = dir + File.separator + time + ".png";

		WebDriver augmentedDriver = null;
		if (FruitSettings.BROWSER_CORE_TYPE == 1 || FruitSettings.BROWSER_CORE_TYPE == 3) {
			augmentedDriver = fruit.getBrowserCore();
			augmentedDriver.manage().window().setPosition(new Point(0, 0));
			augmentedDriver.manage().window().setSize(new Dimension(9999, 9999));
		} else if (FruitSettings.BROWSER_CORE_TYPE == 2) {
			augmentedDriver = new Augmenter().augment(fruit.getBrowserCore());
		} else {
			return "Incorrect browser type";
		}

		try {
			File sourceFile = ((TakesScreenshot) augmentedDriver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(sourceFile, new File(screenShotPath));
		} catch (Exception e) {
			e.printStackTrace();
			return "Failed to screenshot";
		}

		return screenShotPath;
	}

}