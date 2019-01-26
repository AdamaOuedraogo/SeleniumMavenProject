package po;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

  @FindBy(id = "login")
  protected WebElement loginElement;

  @FindBy(id = "ConveyorLoginzz19919")
  protected WebElement passwordElement;

  @FindBy(id = "sub1")
  protected WebElement submitElement;

  @FindBy(id = "remember")
  protected WebElement rememberMe;

  public LoginPage() throws Throwable {
    super();
  }

  public static LoginPage openPlayApplication() throws Throwable {
    return new LoginPage().launch();
  }

  /**
   * cette methode appelle l'application coheriscrm pour la connexion
   */

  private LoginPage launch() {
    this.getDriver().get(this.getMyProperties().getBaseUrl());
    return this.initElements(LoginPage.class);
  }

  /**
   * Cette methode recupere l'indentifiant et le mot de passe pour se connecter a coheriscrm
   */
  public BureauPage loginWithPassword(final String username, final String password) {

    this.loginElement.sendKeys(username);
    this.passwordElement.sendKeys(password);
    this.submitElement.click();
    return this.initElements(BureauPage.class);

  }

  public BureauPage loginWithoutPassword(final String username) {
    this.loginElement.sendKeys(username);
    this.submitElement.click();
    return this.initElements(BureauPage.class);

  }

}
