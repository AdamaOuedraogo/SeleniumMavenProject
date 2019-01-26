package po;


import java.io.File;
import java.util.List;
import java.util.NoSuchElementException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


/**
 * @author patrice NYAMY
 */
public abstract class BasePage {

  boolean acceptNextAlert = true;

  @FindBy(id = "Se286827p261")
  WebElement logoutElement;

  private final SeleniumTestResources seleniumTestResources;

  public BasePage() throws Throwable {
    this.seleniumTestResources = SeleniumTestResources.getInstance();
  }

  public String getTitle() {
    return this.getDriver().getTitle();
  }

  /**
   * Methode qui gere le chargement d'un webelement dans la page
   *
   * @param element
   */
  public WebElement waitForElementVisible(final WebElement element) {
    this.getWait().until(ExpectedConditions.visibilityOf(element));
    // Attendre le chargement des pages
    return element;

  }

  public WebElement waitForElementVisible2(final String by, final Object val) {
    final WebElement elt;

    switch (by) {
      case "id":
        elt = this.getWait().until(presenceOfElementLocated(By.id(val.toString())));
        break;
      case "link":
        elt = this.getWait().until(presenceOfElementLocated(By.linkText(val.toString())));
        break;
      case "css":
        elt = this.getWait().until(presenceOfElementLocated(By.cssSelector(val.toString())));
        break;
      case "name":
        elt = this.getWait().until(presenceOfElementLocated(By.name(val.toString())));
        break;

      case "element":

        if (val instanceof WebElement) {
          elt = this.getWait().until(presenceOfElementLocatedByObjectElement((WebElement) val));
        } else {
          elt = null;
        }
        break;

      default:
        elt = this.getWait().until(presenceOfElementLocated(By.xpath(val.toString())));
        break;
    }

    return elt;

  }

  public void waitforAllElementVisible(final By by) {
    this.getWait().until(ExpectedConditions.visibilityOfElementLocated(by));

  }

  public WebElement waitforAElementVisible(final By by) {
    final WebElement elt = this.getWait().until(ExpectedConditions.visibilityOfElementLocated(by));
    return elt;

  }

  protected WebDriverWait getWait() {
    return this.seleniumTestResources.getWait();
  }

  /**
   * Methode qui gere la presence d'un linktext dans la page
   *
   * @param linkText
   */
  public void clickAnElementByLinkText(final String linkText) {

    try {
      Thread.sleep(2000); // retardement d'une seconde
    } catch (final InterruptedException e) {
    }

    final WebElement w = this.getWait().until(ExpectedConditions.presenceOfElementLocated(By.linkText(linkText)));

    try {
      Thread.sleep(2000); // retardement d'une seconde
    } catch (final InterruptedException e) {
    }

    // w.click();
    this.doubleClicElement(w);
    // doubleClick(w);
  }

  public void clickAndWaitWebelementByXpath(final String xpath) {
    final WebElement elt = this.getWait().until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
    // neverStaleWebElement(elt, By.xpath(xpath));
    elt.click();

  }

  public void clickAndWaitWebelementByName(final String name) {
    final WebElement elt = this.getWait().until(ExpectedConditions.presenceOfElementLocated(By.name(name)));
    elt.click();

  }

  public void clickAndWaitWebelementByid(final String id) {
    final WebElement elt = this.getWait().until(ExpectedConditions.elementToBeClickable(By.id(id)));
    elt.click();

  }

  public void clickAndWaitWebelementByCssSelectors(final String CssSelector) {
    final WebElement elt = this.getWait().until(ExpectedConditions.elementToBeClickable(By.cssSelector(CssSelector)));
    elt.click();

  }

  public void clickAndWaitWebelementByLinkText(final String LinkText) {

    final WebElement elt = this.getWait().until(ExpectedConditions.elementToBeClickable(By.linkText(LinkText)));
    elt.click();
  }

  /**
   * Methode qui gere la presence d'un xpath dans la page
   *
   * @param xpath
   */
  public void clickAnElementByXpath(final String xpath) {

    final WebElement elt = this.getWait().until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
    this.getWait().until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
    elt.click();

  }

  /**
   * Methode qui gere la presence d'un css dans la page
   *
   * @param cssSelector
   */
  public void clickAnElementCssSelector(final String cssSelector) {
    final WebElement elt =
        this.getWait().until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(cssSelector)));
    elt.click();
  }

  /**
   * Methode gere la presenced'un id dans la page
   *
   * @param id
   */
  public void clickAnElementById(final String id) {
    final WebElement elt = this.getWait().until(ExpectedConditions.presenceOfElementLocated(By.id(id)));
    elt.click();

  }

  /**
   * recuperation de la popup si l'utilisation est deja connecte
   */
  protected boolean isAlertPresent() {
    try {
      this.getDriver().switchTo().alert();
      return true;
    } catch (final NoAlertPresentException e) {
      return false;
    }
  }

  /**
   * recuperation du message de la popup et clique sur le bouton ok
   */
  public String closeAlertAndGetItsText() {
    try {
      final Alert alert = this.getDriver().switchTo().alert();
      final String alertText = alert.getText();
      if (this.acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      this.acceptNextAlert = true;
    }
  }

  // fermerture de la popup sans recuperation du message
  public void closeAlert() {
    try {
      final Alert alert = this.getDriver().switchTo().alert();
      alert.accept();

    } catch (final Exception e) {

    }

  }

  /**
   * Methode generic qui return un initElement
   */
  protected <G> G initElements(final Class<G> clazz) {
    return PageFactory.initElements(this.getDriver(), clazz);
  }

  protected boolean isElementPresent(final By by) {
    try {
      this.getDriver().findElement(by);
      return true;
    } catch (final NoSuchElementException e) {
      return false;
    }
  }

  public LoginPage logout() {

    try {
      Thread.sleep(1000); // retardement d'une seconde
    } catch (final InterruptedException e) {
    }
    this.logoutElement.click();
    if (this.isAlertPresent()) {
      this.closeAlert();
    }
    return this.initElements(LoginPage.class);

  }

  public MyProperties getMyProperties() {
    return this.seleniumTestResources.getMyProperties();
  }

  public WebDriver getDriver() {
    return this.seleniumTestResources.getDriver();
  }

  public StringBuffer getVerificationErrors() {
    return this.seleniumTestResources.getVerificationErrors();
  }

  public WebElement getStaleElemById(final String id) {
    try {
      return this.getWait().until(ExpectedConditions.presenceOfElementLocated(By.id(id)));
    } catch (final StaleElementReferenceException e) {
      System.out.println("Attempting to recover from StaleElementReferenceException ...");
      return this.getStaleElemById(id);
    }
  }

  public WebElement getStaleElemBycssSelector(final String cssSectors) {
    try {
      return this.getWait().until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(cssSectors)));
    } catch (final StaleElementReferenceException e) {
      System.out.println("Attempting to recover from StaleElementReferenceException ...");
      return this.getStaleElemBycssSelector(cssSectors);
    }
  }

  public WebElement getStaleElemByXpath(final String xpath) {
    try {
      return this.getWait().until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
    } catch (final StaleElementReferenceException e) {
      System.out.println("Attempting to recover from StaleElementReferenceException ...");
      return this.getStaleElemByXpath(xpath);
    }
  }

  public WebElement getStaleElemByName(final String name) {
    try {
      return this.getWait().until(ExpectedConditions.presenceOfElementLocated(By.name(name)));

    } catch (final StaleElementReferenceException e) {
      System.out.println("Attempting to recover from StaleElementReferenceException ...");
      return this.getStaleElemByName(name);
    }
  }

  public void doubleClic(final String eltTodoubleclic) {
    final WebElement onElement = this.getStaleWebElementByXpath(eltTodoubleclic);
    final Actions builder = new Actions(this.getDriver());
    final Action doubleclic = builder.doubleClick(onElement).release().build();
    doubleclic.perform();
    if (this.isAlertPresent()) {
      this.closeAlert();
    }

  }

  public void doubleClicElement(final WebElement eltTodoubleclic) {
    final Actions builder = new Actions(this.getDriver());
    final Action doubleclic = builder.doubleClick(eltTodoubleclic).release().build();
    doubleclic.perform();
    if (this.isAlertPresent()) {
      this.closeAlert();
    }

  }

  public WebElement getStaleWebElementByName(final String name) {
    WebElement theElement = null;
    do {
      try {

        try {
          Thread.sleep(1000); // retardement d'une seconde
        } catch (final InterruptedException e) {
          e.printStackTrace();
        }

        theElement = this.getDriver().findElement(By.name(name));
      } catch (final StaleElementReferenceException e) {
        e.printStackTrace();
      }
    } while (theElement == null);

    this.getWait().until(ExpectedConditions.visibilityOfElementLocated(By.name(name)));
    return theElement;
  }

  public WebElement getStaleWebElementById(final String id) {
    WebElement theElement = null;
    do {
      try {
        theElement = this.getDriver().findElement(By.id(id));
      } catch (final StaleElementReferenceException e) {
        e.printStackTrace();
      }
    } while (theElement == null);

    this.getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id(id)));
    return theElement;
  }

  public WebElement getStaleWebElementByCss(final String CssSelectors) {
    WebElement theElement = null;
    do {
      try {
        theElement = this.getDriver().findElement(By.cssSelector(CssSelectors));
      } catch (final StaleElementReferenceException e) {
        e.printStackTrace();
      }
    } while (theElement == null);

    this.getWait().until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(CssSelectors)));
    return theElement;
  }

  public WebElement getStaleWebElementByXpath(final String Xpath) {
    WebElement theElement = null;
    do {
      try {

        theElement = this.getDriver().findElement(By.xpath(Xpath));
      } catch (final StaleElementReferenceException e) {
        e.printStackTrace();
      }
    } while (theElement == null);

    this.getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Xpath)));
    return theElement;
  }

  public void takeScreenShot(final String fname) throws Exception {
    final File scrFile = ((TakesScreenshot) this.getDriver()).getScreenshotAs(OutputType.FILE);
    String imageFileDir = System.getProperty("selenium.screenshot.dir");
    if (imageFileDir == null) {
      imageFileDir = System.getProperty("java.io.tmpdir");
    }
    FileUtils.copyFile(scrFile, new File(imageFileDir, fname));
  }

  public void manageAlert() {
    if (this.isAlertPresent()) {
      this.closeAlert();
    }
  }

  private static Function<WebDriver, WebElement> presenceOfElementLocated(final By locator) {
    return new Function<WebDriver, WebElement>() {

      @Override
      public WebElement apply(final WebDriver driver) {
        return driver.findElement(locator);
      }
    };
  }

  private static Function<WebDriver, WebElement> presenceOfElementLocatedByObjectElement(final WebElement webElement) {
    return new Function<WebDriver, WebElement>() {

      @Override
      public WebElement apply(final WebDriver driver) {
        return webElement;
      }
    };
  }

  public void ouvrirElementEndPlus(final String item) {
    final List<WebElement> flechesADeployer =
        this.getDriver().findElements(By.cssSelector("img.x-tree-ec-icon.x-tree-elbow-end-plus"));

    for (final WebElement element : flechesADeployer) {
      try {
        this.waitforAllElementVisible(By.className("TH_Tree"));
        this.waitforAllElementVisible(By.tagName("ul"));
      } catch (final TimeoutException e) {

      }

      this.waitForElementVisible2("css", "img.x-tree-ec-icon.x-tree-elbow-end-plus").click();
    }

    this.getStaleWebElementByXpath("//span[contains(.,'" + item + "')]").click();

  }

  public void ouvrirElementSansParamEndPlus() {
    final List<WebElement> flechesADeployer =
        this.getDriver().findElements(By.cssSelector("img.x-tree-ec-icon.x-tree-elbow-end-plus"));

    for (final WebElement element : flechesADeployer) {
      try {
        this.waitforAllElementVisible(By.className("TH_Tree"));
        this.waitforAllElementVisible(By.tagName("ul"));
      } catch (final TimeoutException e) {

      }

      this.waitForElementVisible2("css", "img.x-tree-ec-icon.x-tree-elbow-end-plus").click();
    }

  }

  public void ouvrirElementSansParamPlus() {
    final List<WebElement> flechesADeployer =
        this.getDriver().findElements(By.cssSelector("img.x-tree-ec-icon.x-tree-elbow-end-plus"));

    for (final WebElement element : flechesADeployer) {
      try {
        this.waitforAllElementVisible(By.className("TH_Tree"));
        this.waitforAllElementVisible(By.tagName("ul"));
      } catch (final TimeoutException e) {

      }

      this.waitForElementVisible2("css", "img.x-tree-ec-icon.x-tree-elbow-plus").click();
    }

  }

  /**
   * Lors d'un chargement, une div se lance. Cette m�thode permet de fermer cette div en appuyant sur la croix
   */
  public void manageDivChargementCoheris() {
    try {

      try {
        Thread.sleep(2000); // retardement d'une seconde
      } catch (final InterruptedException e) {
      }

      final List<WebElement> tous = this.getDriver().findElements(By.xpath("//div[@id='curtainText']/img"));

      try {
        Thread.sleep(2000); // retardement d'une seconde
      } catch (final InterruptedException e) {
      }

      for (final WebElement w : tous) {
        w.click();
      }
    } catch (final TimeoutException t) {

    } catch (final Exception e) {

    }
  }

  /**
   * Va sur la fenetre indiqu�e par le param�tre
   *
   * @param nom nom du conteneur
   */
  public void changerConteneurViaNom(final String nom) {
    try {
      Thread.sleep(3000); // retardement d'une seconde
    } catch (final InterruptedException e) {
    }

    this.getDriver().switchTo().frame(nom);
  }

  /**
   * @param id identifiant du conteneur
   */
  public void changerConteneurViaId(final String id) {

    try {
      Thread.sleep(3000); // retardement d'une seconde
    } catch (final InterruptedException e) {
    }

    this.getDriver().switchTo().frame(id);
  }

  /**
   * @param id identifiant du conteneur
   */
  public void changerConteneurViaNumero(final int num) {

    try {
      Thread.sleep(3000); // retardement d'une seconde
    } catch (final InterruptedException e) {
    }

    this.getDriver().switchTo().frame(num);
  }

  /**
   * Retourne au conteneur principal
   *
   * @param texte texte de la balise repr�sentant la fenetre
   */
  public void changerConteneurVersPrincipale() {
    this.getDriver().switchTo().defaultContent();
  }

  /**
   * Permet de changer de fenetre
   */
  public void changerFenetre() {
    for (final String winHandle : this.getDriver().getWindowHandles()) {
      this.getDriver().switchTo().window(winHandle); // switch focus of WebDriver to the next found window handle
                                                     // (that's your newly opened window)
    }
  }

  /**
   * retourne a la fenetre dont le nom est donn� en param�tre
   *
   * @param nom
   */
  public void retournerSurFenetrePrincipaleSansFermer(final String nom) {
    this.getDriver().switchTo().window(nom);
  }

  /**
   * Retourne sur la fenetre principale
   *
   * @param nom nom de la fenetre principale qu'on avait stock� pr�cedemment
   */
  public void retournerSurFenetrePrincipaleEnFermant(final String nom) {
    this.getDriver().close(); // close newly opened window when done with it
    this.getDriver().switchTo().window(nom); // switch back to the original window
  }

}
