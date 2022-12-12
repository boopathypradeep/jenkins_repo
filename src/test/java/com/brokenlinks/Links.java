package com.brokenlinks;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import javax.net.ssl.HttpsURLConnection;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.devtools.idealized.Javascript;

import com.locators.PageObjectManager;
import com.reusablecodes.BaseClass;

public class Links extends BaseClass {
	public static int brokenlinks = 0;
	public static int successlinks = 0;
	public static String url=null;
	public static void main(String[] args) throws IOException, InterruptedException {
		Scanner s = new Scanner(System.in);
		while (true) {
			print("Enter valid URL");
			 url = s.next();
			if (url != null) {
			int statusCode = getStatusCode(url);
				if (statusCode == 200) {
				launchHeadlessBrowser();
					launchURL(url);
					maximize();					
					PageObjectManager p = new PageObjectManager();
					int statusCode1 = 0;
					String[] st = null;
					List<String> validLinks2 = getLinks(p.getBrokenlinks().getLnkTag());
					st = new String[p.getBrokenlinks().getLnkTag().size()];
					List<String[]> li = new ArrayList<String[]>();
					li.add(new String[] { "Response code", "Status", "Links" });
					for (int i = 0; i < validLinks2.size(); i++) {
						statusCode1 = getStatusCode(validLinks2.get(i));
						if (statusCode1 == 200) {
							successlinks++;

							li.add(new String[] { String.valueOf(statusCode1), "Success", validLinks2.get(i) });

						} else {
							brokenlinks++;
							li.add(new String[] { String.valueOf(statusCode1), "Broken", validLinks2.get(i) });

						}

					}
					li.add(new String[] { "number of broken links:", String.valueOf(Links.brokenlinks) });
					li.add(new String[] { "number of success links:", String.valueOf(Links.successlinks) });
					li.add(new String[] { "Total number of links:", String.valueOf(validLinks2.size()) });
					createFolder();
					CSVWritter(li, url);
					print("Details of links in given URL is stored in location: C:\\temp");
					exitBrowser("QUIT");
				} else {
					print("Please enter valid link");
				}
			}
		}

	}
}
