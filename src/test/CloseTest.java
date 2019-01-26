package com.coheris.selenium.test;


import org.junit.Test;

import com.coheris.selenium.test.resources.BaseTest;

public class CloseTest extends BaseTest {

	@Test
	public void testClose() {
		getDriver().quit();
	}

}
