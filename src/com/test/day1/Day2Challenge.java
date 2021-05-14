package com.test.day1;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Day2Challenge {

	static WebDriver driver;
	static int looptimes;
	static boolean forloopbreak = true;

	public static void scrollingPage(WebDriver driver, long initialheight, int looptimes) throws InterruptedException {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		long value = (Long) js.executeScript("return document.body.scrollHeight");
		System.out.println("Height of the dynamic page is: " + value);
		long quotient = (value / 200);
		System.out.println(
				"No of times loop should run: " + quotient + " and loop has ran " + looptimes + " times already");
		for (long y = initialheight; y <= value; y = y + 200) {

			System.out.println("Scrolled the length of page: " + y + " for: " + looptimes + " times");
			js.executeScript("window.scrollTo(0," + y + ")");
			driver.manage().timeouts().pageLoadTimeout(3000, TimeUnit.SECONDS);
			driver.manage().timeouts().setScriptTimeout(3000, TimeUnit.SECONDS);
			looptimes++;
			if (looptimes == quotient) {
				long new_height = (Long) js.executeScript("return document.body.scrollHeight");
				System.out.println(new_height);
				if (new_height <= value) {
					System.out.println(new_height);
					System.out.println(value);
					System.out.println("Breaking loop");
					break;
				} else {
					System.out.println("Calling Recurring Function again");
					scrollingPage(driver, y, (looptimes - 1));
				}
				break;
			}
		}
	}
	public static void captureScreenshot(WebDriver driver, WebElement element) throws IOException
	{
		String elementName= element.getText();
		String filepath="C://Users//flavi//Desktop//screenshots//"+elementName+".jpeg";
		TakesScreenshot screenshot = (TakesScreenshot) driver;
		File sourcescrshot =screenshot.getScreenshotAs(OutputType.FILE);
		File destscrshot= new File(filepath);
        FileUtils.copyFile(sourcescrshot, destscrshot);
	}
	
	public static void highlightElement(WebDriver driver, WebElement element)
	{
		JavascriptExecutor js = (JavascriptExecutor) driver;
		System.out.println("I am here");
		js.executeScript("arguments[0].scrollIntoView();", element);
		js.executeScript("arguments[0].setAttribute('style', 'border: 2px solid black;');", element);

	}
	public static void carouselScroll(WebDriver driver, String carouseltobesearched) throws IOException {
		System.out.println("In this method: " + carouseltobesearched);
		WebElement carouselname =driver.findElement(By.xpath("//*[contains(@class,'sc-dlfnbm') and contains(text(),'"+carouseltobesearched+"')]"));
		highlightElement(driver,carouselname);
		captureScreenshot(driver,carouselname);
		WebElement arrowswipe = driver.findElement(By.xpath("//*[contains(@class,'sc-dlfnbm') and contains(text(),'"+carouseltobesearched+"')]/ancestor::div[contains(@class,'sffn18')]/descendant::div[contains(@class,'swiper-button-next')]"));
		while(arrowswipe.isDisplayed())
		{
			JavascriptExecutor js = (JavascriptExecutor) driver;
	        js.executeScript("arguments[0].scrollIntoView();", arrowswipe);
	        highlightElement(driver,arrowswipe);
			captureScreenshot(driver,arrowswipe);
			arrowswipe.click();
		}
		if(arrowswipe.isDisplayed()==false)
		{
			List<WebElement> carouselitems =driver.findElements(By.xpath("//*[contains(@class,'sc-dlfnbm') and contains(text(),'"+carouseltobesearched+"')]/ancestor::div[contains(@class,'sffn18')]/descendant::div[contains(@class,'kcs0h5')]"));
			List<String> sortingcarouselitems=new ArrayList<String>();
			for ( WebElement carouselitem:carouselitems )
			{
				sortingcarouselitems.add(carouselitem.getAttribute("title"));
			}
			System.out.println("\n");
			Collections.sort(sortingcarouselitems);
			System.out.println("Sorted Carousel items for : "+ carouseltobesearched);
			System.out.println("---------------------------------------------------------------------------------");
			for (String sortedcarouselitem:sortingcarouselitems)
			{
				System.out.println(sortedcarouselitem);
			}
			System.out.println("-------------Ended the list--------------");
			System.out.println("\n");
		}
	}

	public static void main(String args[]) throws InterruptedException, IOException {
		// opening browser
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\flavi\\Desktop\\chromedriver.exe");
		driver = new ChromeDriver();

		// navigating to the URL and loading it fully
		driver.get("https://www.noon.com/uae-en/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(5000, TimeUnit.SECONDS);
		System.out.println("WebDriver was opened successfully");
		looptimes = 0;

		// scrolling the whole page
		scrollingPage(driver, 0, 0);

		// printing all the carousel elements
		System.out.println("\n");
		List<WebElement> containerclass = driver.findElements(By.xpath("//*[contains(@class,'sc-dlfnbm')]"));
		System.out.println(containerclass.size());
		for (WebElement ele : containerclass) {
			System.out.println(ele.getText());
		}
		
	
		// finding the elements in the carousel
		System.out.println("\n");
		if (containerclass.size() > 5) {
			for (int i = 0; i < 6; i++) {
				if(containerclass.get(i).getText().equalsIgnoreCase(containerclass.get((i+1)).getText())==false)
				{
					carouselScroll(driver,containerclass.get(i).getText());	
					
				}
			}
		} else {
			for (int i = 0; i < containerclass.size(); i++) {
				carouselScroll(driver,containerclass.get(i).getText());

			}
		}

	}
}
