import static org.junit.Assert.fail;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;

import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TestName;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.coheris.selenium.po.BureauPage;
import com.coheris.selenium.po.LoginPage;
import com.coheris.selenium.report.Rapport;
import com.coheris.selenium.test.constants.CSTUsers;

public class BaseTest {

  private SeleniumTestResources seleniumTestResources;

  private LoginPage loginPage;

  protected BureauPage monBureauCSS;

  protected static Rapport rapport;

  @Before
  public void setUp() throws Throwable {

    try {
      Thread.sleep(2000); // retardement de deux secondes
    } catch (final InterruptedException e) {
      //
    }
    try {
      this.seleniumTestResources = SeleniumTestResources.getInstance();
      this.loginPage = LoginPage.openPlayApplication();

      /**** Rapport Selenium test ****/

      // On cr�e un nouveu rapport
      rapport = new Rapport(this.name.getMethodName());

      /* TEST */
      rapport.initExtent();
      rapport.initTest(); // initialise la variable test de Rapport � chaque nouveau test
      rapport.debut();

    } catch (final Throwable e) {
      e.printStackTrace();
      throw e;
    }

  }

  @Rule
  public TestName name = new TestName();

  @Rule
  public TestRule testWatcher = new TestWatcher() {

    @Override
    public void failed(final Throwable t, final Description test) {

      // On veut �viter de faire les captures si le test est CloseTest
      if (!BaseTest.this.name.getMethodName().equals("testClose")) {
        // Prend une capture d'�cran et le met dans le dossier Screenshot
        BaseTest.this.takeScreenshot(BaseTest.this.name.getMethodName());

        // Prend une capture d'�cran et l'ajoute dans le rapport
        rapport.ajouterCapture();

        /* Si une erreur de type "Rhino" apparait sur la page on r�cup�re le texte indiquant le type d'erreur */
        if (BaseTest.this.getDriver().findElements(By.id("ConveyorMessage")).size() != 0) {
          try {
            /* D�ploie le champ o� est �crit le descriptif de l'erreur */
            WebElement element =
                SeleniumTestResources.getInstance().getWait()
                    .until(ExpectedConditions.presenceOfElementLocated(By.id("TextAreaMessageGrpThm_span")));
            element.click();

            try {
              Thread.sleep(2000); // retardement d'une seconde
            } catch (final InterruptedException e) {
            }

            /* Copie dans un fichier le message d'erreur */
            element =
                SeleniumTestResources.getInstance().getWait()
                    .until(ExpectedConditions.presenceOfElementLocated(By.id("Se54108p316")));
            final File fichier =
                Paths.get(rapport.obtainCheminScreenshots(), BaseTest.this.name.getMethodName() + "_RHINO_ERROR.txt")
                    .toFile();
            fichier.createNewFile();

            final FileWriter ecrivain = new FileWriter(fichier);
            ecrivain.write(element.getText());
            ecrivain.close();
          } catch (final Throwable e) {
            e.printStackTrace();
          }
        }
      }

      this.after(false);
    }

    @Override
    public void succeeded(final Description description) {

      this.after(true);
    }

    public void after(final boolean passed) {

      try {
        if (!BaseTest.this.name.getMethodName().equals("testClose")) {
          // d�connecte pas
          BaseTest.this.monBureauCSS.logout();
        }
        /* TEST */
        if (passed) {
          rapport.finPass();
        } else {
          rapport.finFail();
        }

        // On arrete l'enregistrement des tests dans le rapport
        rapport.flush();
      } catch (final NoSuchElementException n) {
        n.printStackTrace();
      } catch (final NullPointerException n) {
        n.printStackTrace();
      } catch (final Throwable t) {
        t.printStackTrace();
      }
    }
  };

  protected String getBaseUrl() {

    final String baseUrl = this.seleniumTestResources.getMyProperties().getBaseUrl();
    return baseUrl;
  }

  protected void quitBroswer() throws Exception {

    this.getDriver().quit();
    final String verificationErrorString = this.seleniumTestResources.getVerificationErrors().toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

  protected WebDriver getDriver() {

    return this.seleniumTestResources.getDriver();
  }

  protected StringBuffer getVerificationErrors() {

    return this.seleniumTestResources.getVerificationErrors();
  }

  public LoginPage getCrmLoginPage() {

    return this.loginPage;
  }

  protected String getPassword(final String user) {

    if (user.equals(CSTUsers.CSS)) {
      return this.seleniumTestResources.getMyProperties().getPassword(CSTUsers.CSS);
    } else {
      return this.seleniumTestResources.getMyProperties().getPassword(CSTUsers.CRM);
    }
  }

  protected String getLoginame(final String user) {

    if (user.equals(CSTUsers.CSS)) {
      return this.seleniumTestResources.getMyProperties().getLoginName(CSTUsers.CSS);
    } else {
      return this.seleniumTestResources.getMyProperties().getLoginName(CSTUsers.CRM);
    }
  }

  public void takeScreenshot(final String screenshotName) {

    if (this.getDriver() instanceof TakesScreenshot) {
      final WebDriver augment = new Augmenter().augment(this.getDriver());
      final File tempFile = ((TakesScreenshot) augment).getScreenshotAs(OutputType.FILE);
      try {
        FileUtils.copyFile(tempFile, Paths.get(rapport.obtainCheminScreenshots(), screenshotName + ".png").toFile());
      } catch (final IOException e) {
        // TODO handle exception
      }
    }

  }

}
