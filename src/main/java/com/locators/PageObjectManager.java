package com.locators;

public class PageObjectManager {
Brokenlinks brokenlinks;

public Brokenlinks getBrokenlinks() {
	return brokenlinks==null ? brokenlinks=new Brokenlinks() : brokenlinks;
}

}
