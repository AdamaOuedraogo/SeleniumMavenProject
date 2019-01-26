package com.coheris.selenium.test;


import org.junit.Test;

import com.coheris.selenium.test.constants.CSTUsers;
import com.coheris.selenium.test.resources.BaseTest;

public class TestLogin extends BaseTest {

  @Test
  public void TestLogin() {
    /** 1 ere Connexion Avec l'utilisateur Administrateur */
    this.monBureauCSS = this.getCrmLoginPage().loginWithPassword(this.getLoginame(CSTUsers.CSS), this.getPassword(CSTUsers.CSS));

    try {
      Thread.sleep(4000);
    } catch (final InterruptedException e) {
      e.printStackTrace();
    }

    /** Gestion du login si contexte deja ouvert */
    this.getCrmLoginPage().manageAlert();

  }

}
