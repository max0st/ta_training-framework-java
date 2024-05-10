package test;


import model.ComputeEngine;
import org.testng.annotations.Test;
import page.GoogleCloudEstimateSummaryPage;
import page.GoogleCloudPage;
import page.GoogleCloudPricingCalculatorPage;
import service.ComputerEngineCreator;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;


public class GoogleCloudCalculatorTest extends BaseTest {
    private GoogleCloudEstimateSummaryPage gcEstimateSummaryPage;
    private String estimatedCost;
    private GoogleCloudPricingCalculatorPage googleCloudPricingCalculatorPage;
    private ComputeEngine computeEngine = ComputerEngineCreator.createFromProperties();

    @Test(priority = 1)
    public void testComputeEngineCostEstimationProcess() {
        String textToSearch = "Google Cloud Platform Pricing Calculator";
        GoogleCloudPage googleCloudPage = new GoogleCloudPage(driver);
        googleCloudPricingCalculatorPage = googleCloudPage
                .openHomePage()
                .openSearchTextArea()
                .inputSearchText(textToSearch)
                .startSearch()
                .openLinkToCalculator()
                .pressAddToEstimate()
                .pressComputerEngine()
                .specifyInstancesNumber(computeEngine.getNumberOfInstances())
                .openOperatingSystemMenu()
                .chooseOperatingSystem(computeEngine.getOperatingSystem())
                .chooseRegular()
                .openMachineFamilyMenu()
                .chooseMachineFamily(computeEngine.getMachineFamily())
                .openSeriesMenu()
                .chooseSeries(computeEngine.getSeries())
                .openMachineTypeMenu()
                .chooseMachineType(computeEngine.getMachineType())
                .addGPU()
                .openGPUModelMenu()
                .chooseGPUModel(computeEngine.getGpuType())
                .openGPUsNumberMenu()
                .chooseNumberOfGPUs(computeEngine.getNumberOfGPUs())
                .openSSDMenu()
                .chooseLocalSSD(computeEngine.getLocalSsd())
                .openRegionMenu()
                .chooseRegion(computeEngine.getDatacenterLocation())
                .chooseCommittedUsage(computeEngine.getCommittedUsageTerm());
        validatePriceCalculated();
        estimatedCost = googleCloudPricingCalculatorPage.getEstimatedCost();
        gcEstimateSummaryPage = googleCloudPricingCalculatorPage.pressShareButton()
                .openLinkEstimateSummary();
    }

    private void validatePriceCalculated() {
        assertTrue(googleCloudPricingCalculatorPage.isPriceCalculated());
    }

    @Test(priority = 3)
    public void testSeriesMatchesExpected() {
        assertEquals(gcEstimateSummaryPage.retrieveSeriesType(), computeEngine.getSeriesFromMachineType());
    }

    @Test(priority = 3)
    public void testNumberOfGPUsMatchesExpected() {
        assertEquals(gcEstimateSummaryPage.retrieveNumberOfGPUs(), String.valueOf(computeEngine.getNumberOfGPUs()));
    }

    @Test(priority = 3)
    public void testNumberOfInstancesMatchesExpected() {
        assertEquals(gcEstimateSummaryPage.retrieveNumberOfInstances(), String.valueOf(computeEngine.getNumberOfInstances()));
    }

    @Test(priority = 3)
    public void testLocalSSDMatchesExpected() {
        assertEquals(gcEstimateSummaryPage.retrieveLocalSSDType(), computeEngine.getLocalSsd());
    }

    @Test(priority = 3)
    public void testProvisioningModelMatchesExpected() {
        assertEquals(gcEstimateSummaryPage.retrieveProvisioningModel(), computeEngine.getProvisioningModel());
    }

    @Test(priority = 3)
    public void testIsGPUAddedMatchesExpected() {
        assertEquals(gcEstimateSummaryPage.IsGPUAdded(), String.valueOf(computeEngine.isGpuAdded()));
    }

    @Test(priority = 2)
    public void verifyMatchingEstimatedCosts() {
        assertEquals(gcEstimateSummaryPage.retrieveEstimatedCost(), estimatedCost);
    }

}
