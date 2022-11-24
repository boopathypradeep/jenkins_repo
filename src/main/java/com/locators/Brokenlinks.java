package com.locators;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.reusablecodes.BaseClass;

public class Brokenlinks extends BaseClass{
	public Brokenlinks() {
		PageFactory.initElements(driver, this);
	}
@FindBy(xpath="//a")
private List<WebElement> lnkTag;

public List<WebElement> getLnkTag() {
	return lnkTag;
}
}
