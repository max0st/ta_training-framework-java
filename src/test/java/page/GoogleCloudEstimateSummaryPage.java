package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GoogleCloudEstimateSummaryPage {
    private final WebDriverWait webDriverWait;
    @FindBy(xpath = "(//span[@class='Kfvdz'])[10]")
    private WebElement txtInstancesNumber;
    @FindBy(xpath = "(//span[@class='Kfvdz'])[12]")
    private WebElement txtProvisioningModel;
    @FindBy(xpath = "(//span[@class='Kfvdz'])[3]")
    private WebElement txtMachineType;
    @FindBy(xpath = "(//span[@class='Kfvdz'])[16]")
    private WebElement txtAddGPUs;
    @FindBy(xpath = "(//span[@class='Kfvdz'])[6]")
    private WebElement txtGPUsNumber;
    @FindBy(xpath = "(//span[@class='Kfvdz'])[7]")
    private WebElement txtLocalSSD;
    @FindBy(xpath = "//h4[@class='n8xu5 Nh2Phe D0aEmf']")
    private WebElement estimatedCost;

    public GoogleCloudEstimateSummaryPage(WebDriver webDriver, WebDriverWait webDriverWait) {
        this.webDriverWait = webDriverWait;
        PageFactory.initElements(webDriver, this);
    }

    public String retrieveNumberOfInstances() {
        return waitUntilLoad(txtInstancesNumber).getText();
    }

    public String retrieveProvisioningModel() {
        return waitUntilLoad(txtProvisioningModel).getText();
    }

    public String IsGPUAdded() {
        return waitUntilLoad(txtAddGPUs).getText();
    }


    public String retrieveNumberOfGPUs() {
        return waitUntilLoad(txtGPUsNumber).getText();
    }

    public String retrieveEstimatedCost() {
        return waitUntilLoad(estimatedCost).getText();
    }


    public String retrieveLocalSSDType() {
        return waitUntilLoad(txtLocalSSD).getText();
    }


    public String retrieveSeriesType() {
        return getSeriesFromMachineType();
    }

    private String getSeriesFromMachineType() {
        String fullType = waitUntilLoad(txtMachineType).getText();

        int dashIndex = fullType.indexOf('-');

        if (dashIndex != -1) {
            return fullType.substring(0, dashIndex);
        }

        return fullType;
    }


    private WebElement waitUntilLoad(WebElement element) {
        return webDriverWait.until(ExpectedConditions.visibilityOf(element));
    }

}
