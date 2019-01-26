package po;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class BureauSocialPage extends BasePage {

  public BureauSocialPage() throws Throwable {
    super();
    // TODO Auto-generated constructor stub
  }

  /**
   * Acces aux element du bureau de la page BureauSocialPage
   */

  @FindBy(xpath = "//li[@id='sidebarSocialMenu']/a/span")
  WebElement socialMenuButtonElement;

  @FindBy(xpath = "//li[@id='side-bar-social-publication']/a")
  WebElement socialPublicationButtonElement;

  @FindBy(xpath = "//a[@id='side-bar-form:j_idt47']/span")
  WebElement socialInformationButtonElement;

  @FindBy(xpath = "xpath=(//a[contains(@href, '#')])[5]")
  WebElement OpenSocialDeconnexionMenuElement;

  @FindBy(id = "id=j_idt41")
  WebElement socialDeconnexionButtonElement;

  /**
   * Les methodes de la classe bureau page
   */

  public void openSocialMenu() {

    // this.changerFenetre();
    this.waitForElementVisible2("element", this.socialMenuButtonElement);
    this.socialMenuButtonElement.click();

    this.waitForElementVisible2("element", this.socialPublicationButtonElement);
    this.socialPublicationButtonElement.click();

  }
}
