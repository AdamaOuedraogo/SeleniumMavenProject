package report;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.coheris.selenium.test.constants.CSTMessages;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class Rapport {

	private static final String SCREENSHOTS = "Screenshots";

	private ExtentReports extent;

	private ExtentTest test;

	private final Path reportDir;

	private final String nomTest;

	private final Path cheminScreenshots;

	public Rapport(final String nom) throws IOException {

		this.reportDir = this.makeReportDir();
		this.nomTest = nom;

		if (!this.reportDir.toFile().exists()) {
			Files.createDirectories(this.reportDir);
		}

		this.cheminScreenshots = Paths.get(this.reportDir.toString(), "Screenshots");

	}

	private Path makeReportDir() {
		final String jenkinsBuildNumber = System.getenv("BUILD_NUMBER");

		// Calcul du repertoire de stockage des rapports
		Path reportsPathFolder = Paths.get("..", "ExtentReports");
		if (jenkinsBuildNumber != null) {
			reportsPathFolder = Paths.get(reportsPathFolder.toString(), jenkinsBuildNumber);
		}

		return reportsPathFolder.toAbsolutePath().normalize();
	}

	public void initExtent() {

		// Nouvelle instance de raport
		this.extent = ExtentManager.getInstance(this.reportDir);
	}

	public void initTest() {

		// D�but du test
		this.test = this.extent.startTest(this.nomTest);
	}

	public void debut() {

		// D�but des log
		this.test.log(LogStatus.INFO, CSTMessages.TEST_START);
	}

	public void finPass() {

		// Fin des log
		this.test.log(LogStatus.INFO, CSTMessages.TEST_END);

		// Test r�ussi
		this.test.log(LogStatus.PASS, CSTMessages.TEST_SUCCESSFUL);

		// ending test
		this.extent.endTest(this.test);
	}

	public void finFail() {

		// Fin des log
		this.test.log(LogStatus.INFO, CSTMessages.TEST_END);

		// Test �chou�
		this.test.log(LogStatus.FAIL, CSTMessages.TEST_FAILED);

		// ending test
		this.extent.endTest(this.test);
	}

	public void flush() {

		// writing everything to document
		this.extent.flush();
	}

	public void ajouterCapture() {

		this.test.log(LogStatus.INFO,
				"Capture d'�cran ci-dessous : " + this.test.addScreenCapture(this.makeScreenshotURL()));
	}

	private String makeScreenshotURL() {
		return "./" + SCREENSHOTS + "/" + this.nomTest + ".png";
	}

	/**
	 * @return le chemin o� les copies d'�cran doivent �tre d�pos�es. Le
	 *         r�pertoire est cr�� s'il n'existe pas
	 */
	public String obtainCheminScreenshots() {

		if (!this.cheminScreenshots.toFile().exists()) {
			try {
				Files.createDirectories(this.cheminScreenshots);
			} catch (final IOException e) {
				throw new RuntimeException(e);
			}
		}

		return this.cheminScreenshots.toString();
	}
}
