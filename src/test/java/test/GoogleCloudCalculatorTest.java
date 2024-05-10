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
        assertEquals(computeEngine.getSeries(), gcEstimateSummaryPage.retrieveSeriesType());
    }

    @Test(priority = 3)
    public void testNumberOfGPUsMatchesExpected() {
        assertEquals(String.valueOf(computeEngine.getNumberOfGPUs()), gcEstimateSummaryPage.retrieveNumberOfGPUs());
    }

    @Test(priority = 3)
    public void testNumberOfInstancesMatchesExpected() {
        assertEquals(String.valueOf(computeEngine.getNumberOfInstances()), gcEstimateSummaryPage.retrieveNumberOfInstances());
    }

    @Test(priority = 3)
    public void testLocalSSDMatchesExpected() {
        assertEquals(computeEngine.getLocalSsd(), gcEstimateSummaryPage.retrieveLocalSSDType());
    }
    @Test(priority = 3)
    public void testProvisioningModelMatchesExpected() {
        assertEquals(computeEngine.getProvisioningModel(), gcEstimateSummaryPage.retrieveProvisioningModel());
    }
    @Test(priority = 3)
    public void testIsGPUAddedMatchesExpected() {
        assertEquals(String.valueOf(computeEngine.isGpuAdded()), gcEstimateSummaryPage.IsGPUAdded());
    }

    @Test(priority = 2)
    public void verifyMatchingEstimatedCosts() {
        assertEquals(estimatedCost, gcEstimateSummaryPage.retrieveEstimatedCost());
    }

}
