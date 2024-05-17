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

    public String retrieveEstimatedCost() {
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



    public GoogleCloudEstimateSummaryPage verifySeriesTest(ComputeEngine computeEngine) {
        assertSeries(computeEngine);
        return this;
    }


    public GoogleCloudEstimateSummaryPage verifyNumberOfGPUsTest(ComputeEngine computeEngine) {
        assertNumberOfGPUs(computeEngine);
        return this;
    }


    public GoogleCloudEstimateSummaryPage verifyNumberOfInstancesTest(ComputeEngine computeEngine) {
        assertNumberOfInstances(computeEngine);
        return this;
    }


    public GoogleCloudEstimateSummaryPage verifyLocalSSDTest(ComputeEngine computeEngine) {
        assertSSD(computeEngine);
        return this;
    }

    public GoogleCloudEstimateSummaryPage verifyProvisioningModelTest(ComputeEngine computeEngine) {
        assertProvisioningModel(computeEngine);
        return this;
    }


    public GoogleCloudEstimateSummaryPage verifyGPUAddedTest(ComputeEngine computeEngine) {
        assertGPUAdded(computeEngine);
        return this;
    }
    public GoogleCloudEstimateSummaryPage verifyGPUAddedTest(String estimatedCost) {
        assertEstimatedCost(estimatedCost);
        return this;
    }

    private void assertSeries(ComputeEngine computeEngine) {
        assertEquals(retrieveSeriesType(), computeEngine.getSeriesFromMachineType());
    }

    private void assertSSD(ComputeEngine computeEngine) {
        assertEquals(retrieveLocalSSDType(), computeEngine.getLocalSsd());
    }

    private void assertNumberOfGPUs(ComputeEngine computeEngine) {
        assertEquals(retrieveNumberOfGPUs(), String.valueOf(computeEngine.getNumberOfGPUs()));
    }

    private void assertNumberOfInstances(ComputeEngine computeEngine) {
        assertEquals(retrieveNumberOfInstances(), String.valueOf(computeEngine.getNumberOfInstances()));
    }

    private void assertProvisioningModel(ComputeEngine computeEngine) {
        assertEquals(retrieveProvisioningModel(), computeEngine.getProvisioningModel());
    }

    private void assertGPUAdded(ComputeEngine computeEngine) {
        assertEquals(retrieveGPUAdded(), String.valueOf(computeEngine.isGpuAdded()));
    }

    private void assertEstimatedCost(String estimatedCost) {
        assertEquals(retrieveEstimatedCost(), estimatedCost);
    }
}
