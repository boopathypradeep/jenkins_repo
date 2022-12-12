package com.reusablecodes;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import javax.net.ssl.HttpsURLConnection;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import com.locators.PageObjectManager;
import com.opencsv.CSVWriter;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {
	public static WebDriver driver;
	public static File linksPath;
	public static void launchBrowser() {
WebDriverManager.chromedriver().setup();
 driver=new ChromeDriver();

}
public static void launchHeadlessBrowser() {
	WebDriverManager.chromedriver().setup();
ChromeOptions options=new ChromeOptions();
options.setHeadless(true);
 driver=new ChromeDriver(options);

}
public static void maximize() {
	driver.manage().window().maximize();

}
public static void launchURL(String url) {
	driver.get(url);

}
public static String readPropertyFile(String key) throws IOException {
	FileInputStream stream=new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resource\\BrokenLinks.properties");
Properties properties=new Properties();
properties.load(stream);
return properties.get(key).toString();
}
public static String getAttributeValue(WebElement element, String attribute) {
	return element.getAttribute(attribute);

}
public static void print(Object value) {
	System.out.println(value);

}
public static int getStatusCode(String links) throws MalformedURLException, IOException {
	return ((HttpsURLConnection)new URL(null, links, new sun.net.www.protocol.https.Handler()).openConnection()).getResponseCode();

}
public static List<WebElement> findElements(String locator, String value) {
	List<WebElement> findElements =null;
	switch (locator) {
	case "tagName":
		 findElements = driver.findElements(By.tagName(value));
		break;
	case "xPath":
		 findElements = driver.findElements(By.xpath(value));
		break;
	default:
		break;
	}
	return findElements;

}

public static List<String>  getLinks(List<WebElement> findElements) throws MalformedURLException, IOException {
	 

	 List<String> targets = new ArrayList<String>();
for (int i = 0; i < findElements.size(); i++) {
	targets.add(findElements.get(i).getAttribute("href"));
	
	}
List<String> li=new ArrayList<>();
	for (String string : targets) {
		if (string == null || string.isEmpty()||string.startsWith("javascript:void(0);")) {
		continue;	
	}
		li.add(string);
}
return li;

}
public static void CSVWritter(List<String[]> li, String url) throws IOException{ 
	
    String SLASH_CHAR = "."; 
    
    if (url != null) {
      int slashPosition = url.indexOf(SLASH_CHAR);
   	      if (slashPosition >= 0) {
        url = url.substring(slashPosition+1);
        int nxtDot = url.indexOf(SLASH_CHAR);
        url= url.substring(0,nxtDot);
      
      }
    }
    Date date = new Date();
    SimpleDateFormat format=new SimpleDateFormat("yyyy-MMM-dd HH_mm_ss");
    String timeStamp = format.format(date);
       CSVWriter writer = new CSVWriter(new FileWriter(new File(linksPath+"\\"+url+"&"+timeStamp+".csv")));
            writer.writeAll(li); 
         writer.close();
   
}
public static void createFolder() throws IOException {
	 linksPath = new File(readPropertyFile("folderPath")+ File.separator+"temp");

	if (!linksPath.exists() && linksPath.mkdirs()) {
	
}
}
public static void exitBrowser(String exit) {
	switch (exit) {
	case "QUIT":
		driver.quit();
		break;
	case "CLOSE":
		driver.close();
		break;
	default:
		break;
	}

}
}