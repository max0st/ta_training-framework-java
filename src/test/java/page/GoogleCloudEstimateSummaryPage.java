package page;

import model.ComputeEngine;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.testng.Assert.assertEquals;

public class GoogleCloudEstimateSummaryPage {
    private final WebDriverWait webDriverWait;
    @FindBy(xpath = "//span[contains(text(), 'Number of Instances')]/following-sibling::span[@class='Kfvdz']")
    private WebElement txtInstancesNumber;
    @FindBy(xpath = "//span[text()='Provisioning Model']/following-sibling::span[@class='Kfvdz']")
    private WebElement txtProvisioningModel;
    @FindBy(xpath = "//span[text()='Machine type']/following-sibling::span[@class='Kfvdz']")
    private WebElement txtMachineType;
    @FindBy(xpath = "//span[text()='Add GPUs']/following-sibling::span")
    private WebElement txtAddGPUs;
    @FindBy(xpath = "//span[text()='Number of GPUs']/following-sibling::span[@class='Kfvdz']")
    private WebElement txtGPUsNumber;
    @FindBy(xpath = "//span[text()='Local SSD']/following-sibling::span")
    private WebElement txtLocalSSD;
    @FindBy(xpath = "//h4[@class='n8xu5 Nh2Phe D0aEmf']")
    private WebElement estimatedCost;

    public GoogleCloudEstimateSummaryPage(WebDriver webDriver, WebDriverWait webDriverWait) {
        this.webDriverWait = webDriverWait;
        PageFactory.initElements(webDriver, this);
    }

    private String retrieveNumberOfInstances() {
        return waitUntilLoad(txtInstancesNumber).getText();
    }

    private String retrieveProvisioningModel() {
        return waitUntilLoad(txtProvisioningModel).getText();
    }

    private String retrieveGPUAdded() {
        return waitUntilLoad(txtAddGPUs).getText();
    }


    private String retrieveNumberOfGPUs() {
        return waitUntilLoad(txtGPUsNumber).getText();
    }

    private String retrieveEstimatedCost() {
        return waitUntilLoad(estimatedCost).getText();
    }


    private String retrieveLocalSSDType() {
        return waitUntilLoad(txtLocalSSD).getText();
    }


    private String retrieveSeriesType() {
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

    public void assertSeries(ComputeEngine computeEngine) {
        assertEquals(retrieveSeriesType(), computeEngine.getSeriesFromMachineType());
    }

    public void assertSSD(ComputeEngine computeEngine) {
        assertEquals(retrieveLocalSSDType(), computeEngine.getLocalSsd());
    }

    public void assertNumberOfGPUs(ComputeEngine computeEngine) {
        assertEquals(retrieveNumberOfGPUs(), String.valueOf(computeEngine.getNumberOfGPUs()));
    }

    public void assertNumberOfInstances(ComputeEngine computeEngine) {
        assertEquals(retrieveNumberOfInstances(), String.valueOf(computeEngine.getNumberOfInstances()));
    }

    public void assertProvisioningModel(ComputeEngine computeEngine) {
        assertEquals(retrieveProvisioningModel(), computeEngine.getProvisioningModel());
    }

    public void assertGPUAdded(ComputeEngine computeEngine) {
        assertEquals(retrieveGPUAdded(), String.valueOf(computeEngine.isGpuAdded()));
    }

    public void assertEstimatedCost(String estimatedCost) {
        assertEquals(retrieveEstimatedCost(), estimatedCost);
    }
}
