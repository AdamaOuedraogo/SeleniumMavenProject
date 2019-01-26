package com.coheris.selenium.test;


import org.junit.Test;

import com.coheris.selenium.po.BureauSocialPage;
import com.coheris.selenium.test.constants.CSTUsers;
import com.coheris.selenium.test.resources.BaseTest;

/**
 * @author patrice NYAMY
 */
public class TestAccessSocialPage extends BaseTest {

  @Test
  public void TestAccessSocialPage() {

    this.monBureauCSS = this.getCrmLoginPage().loginWithPassword(this.getLoginame(CSTUsers.CSS), this.getPassword(CSTUsers.CSS));

    this.getCrmLoginPage().manageAlert();
    try {
      Thread.sleep(3000);
    } catch (final InterruptedException e) {
      e.printStackTrace();
    }
    final BureauSocialPage monBureauSocialPage = this.monBureauCSS.accesSocial();
    try {
      Thread.sleep(3000);
    } catch (final InterruptedException e) {
      e.printStackTrace();
    }
    monBureauSocialPage.changerFenetre();
    monBureauSocialPage.openSocialMenu();

    try {
      Thread.sleep(3000);
    } catch (final InterruptedException e) {
      e.printStackTrace();
    }

    this.getDriver().quit();

  }

}
