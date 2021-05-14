package com.test.day1;

import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;

public class Day1Challenge {

	public static void main(String[] args) {

		//opening browser
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\flavi\\Desktop\\chromedriver.exe");
		WebDriver driver=new ChromeDriver();
		
		//navigating to the URL and loading it fully
		driver.get("https://www.worldometers.info/world-population/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(1000, TimeUnit.SECONDS);
		System.out.println("WebDriver was opened successfully");

		//accepting the website cookie
		try
		{
		WebElement websitecookies= driver.findElement(By.className("cc-compliance"));
		if(websitecookies.isDisplayed())
		{
		websitecookies.click();
		System.out.println("WebSite Cookies Accepted");
		}
		}
		catch(Exception e){
			System.out.println("Element Not Found Exception");
		}
		//Declare all the to be used variables here
		
		//Current World Population
		WebElement currentworldpopulation;
		String countofcurrentworldpopulation;
		
		//Births Today
		WebElement birthstoday;
		String countofbirthstoday;
		
		//Deaths Today
		WebElement deathstoday;
		String countofdeathstoday;
		
		//Population Growth Today
		WebElement populationgrowthtoday;
		String countofpopulationgrowthtoday;
		
		//Births This Year
		WebElement birthsthisyear;
		String countofbirthsthisyear;
		
		//Deaths This Year
		WebElement deathsthisyear;
		String countofdeathsthisyear;
		
		//Population Growth This Year
		WebElement populationgrowththisyear;
		String countofpopulationgrowththisyear;
		
		
		//initialize a counter to verify how many times the loop runs
		int i=1;
		
		//declaring and initializing the datemilliseconds to complete execution
		long datetimemilliseconds=System.currentTimeMillis();
		System.out.println(datetimemilliseconds);
		
		//declaring datemilliseconds for each loops
		long starttimeloop;
		long endtimeloop;
		
		
		//Formatting date
		SimpleDateFormat formatteddated = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
	while(true)		
	{	
		if((System.currentTimeMillis()-datetimemilliseconds)>=20000)
		{
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println();
			
			System.out.println("Execution Start time of the Counting Series started at: "+formatteddated.format(datetimemilliseconds));
			System.out.println("Execution Ended time of the Counting Series ended at: "+ formatteddated.format(System.currentTimeMillis()));
			System.out.println("Loop has been printed: "+ (i-1));
			break;
		}
		else
		{
		System.out.println();
		System.out.println();		
		System.out.println("Printing this loop for the "+i+" time/times");	
		System.out.println("-------------------------------------------------------------------");
		//recording the timing when the loop starts;
		starttimeloop=System.currentTimeMillis();
		//Recording the Current World Population
		currentworldpopulation= driver.findElement(By.className("maincounter-number"));
		countofcurrentworldpopulation= currentworldpopulation.getAttribute("innerText");
		System.out.println("Current World Population: "+ countofcurrentworldpopulation);
		
	
		//Recording the Total Births Today
		birthstoday=driver.findElement(By.xpath("//*[@class='sec-text' and contains(text(),'Births today')]/following-sibling::div"));
		countofbirthstoday=birthstoday.getAttribute("innerText");
		System.out.println("Total Births Today: "+ countofbirthstoday);
		
	
		//Recording Total Deaths Today
		
		deathstoday=driver.findElement(By.xpath("//*[@class='sec-text' and contains(text(),'Deaths today')]/following-sibling::div"));
		countofdeathstoday=deathstoday.getAttribute("innerText");
		System.out.println("Total Deaths Today: "+ countofdeathstoday);
		
	
		//Recording Total Population Growth Today
		
		populationgrowthtoday=driver.findElement(By.xpath("//*[@class='sec-text' and contains(text(),'Population Growth today')]/following-sibling::div"));
		countofpopulationgrowthtoday=populationgrowthtoday.getAttribute("innerText");
		System.out.println("Population Growth Today: "+ countofpopulationgrowthtoday);
		
		
	
		//Recording Total Births this year
		
		birthsthisyear=driver.findElement(By.xpath("//*[@class='sec-text' and contains(text(),'Births this year')]/following-sibling::div"));
		countofbirthsthisyear=birthsthisyear.getAttribute("innerText");
		System.out.println("Total Births this year: "+ countofbirthsthisyear);	
		
		
	
		//Recording Total Deaths this year
		
		deathsthisyear=driver.findElement(By.xpath("//*[@class='sec-text' and contains(text(),'Deaths this year')]/following-sibling::div"));
		countofdeathsthisyear=deathsthisyear.getAttribute("innerText");
		System.out.println("Total Deaths this year: "+ countofdeathsthisyear);	
		
	
		//Recording Total Population Growth this year
		
		populationgrowththisyear=driver.findElement(By.xpath("//*[@class='sec-text' and contains(text(),'Population Growth this year')]/following-sibling::div"));
		countofpopulationgrowththisyear=populationgrowththisyear.getAttribute("innerText");
		System.out.println("Total Population Growth this year: "+ countofpopulationgrowththisyear);	

		System.out.println();
		System.out.println("-------------------------------------------------------------------");
		endtimeloop=System.currentTimeMillis();
		i++;		
		System.out.println("This loop started at: "+ formatteddated.format(starttimeloop));
		System.out.println("This loop ended at: "+formatteddated.format(endtimeloop));
		System.out.println();
		System.out.println();
		}
	}

	}

}
