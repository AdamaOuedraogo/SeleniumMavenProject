package com.coheris.selenium.test;


import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.coheris.selenium.test.resources.BaseTest;

/**
 * @author Patrice NYAMY
 */
@RunWith(Suite.class)
@SuiteClasses({
    TestLogin.class, //
    CloseTest.class
})
public class AllTestCoherisCRM extends BaseTest {

}
