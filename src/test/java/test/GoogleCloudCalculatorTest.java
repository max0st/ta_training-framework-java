package test;


import data.TestData;
import model.ComputeEngine;
import org.testng.annotations.Test;
import page.GoogleCloudEstimateSummaryPage;
import page.GoogleCloudPage;
import page.GoogleCloudPricingCalculatorPage;
import service.ComputerEngineCreator;


public class GoogleCloudCalculatorTest extends BaseTest {
    private GoogleCloudEstimateSummaryPage gcEstimateSummaryPage;
    private String estimatedCost;
    private GoogleCloudPricingCalculatorPage googleCloudPricingCalculatorPage;
    private ComputeEngine computeEngine = ComputerEngineCreator.createFromProperties();

    @Test(priority = 1)
    public void testComputeEngineCostEstimationProcess() {
        GoogleCloudPage googleCloudPage = new GoogleCloudPage(driver);
        googleCloudPricingCalculatorPage = googleCloudPage
                .openHomePage()
                .openSearchTextArea()
                .inputSearchText(TestData.TEXT_TO_SEARCH_ON_GOOGLE_CLOUD_PLATFORM)
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
                .chooseCommittedUsage(computeEngine.getCommittedUsageTerm())
                .verifyPriceCalculated();
        estimatedCost = googleCloudPricingCalculatorPage.getEstimatedCost();
        gcEstimateSummaryPage = googleCloudPricingCalculatorPage.pressShareButton()
                .openLinkEstimateSummary();
    }

    @Test(priority = 3)
    public void testSeries() {
        gcEstimateSummaryPage.assertSeries(computeEngine);
    }

    @Test(priority = 3)
    public void testNumberOfGPUs() {
        gcEstimateSummaryPage.assertNumberOfGPUs(computeEngine);
    }

    @Test(priority = 3)
    public void testNumberOfInstances() {
        gcEstimateSummaryPage.assertNumberOfInstances(computeEngine);
    }

    @Test(priority = 3)
    public void testLocalSSD() {
        gcEstimateSummaryPage.assertSSD(computeEngine);
    }

    @Test(priority = 3)
    public void testProvisioningModel() {
        gcEstimateSummaryPage.assertProvisioningModel(computeEngine);
    }

    @Test(priority = 3)
    public void testGPUAdded() {
        gcEstimateSummaryPage.assertGPUAdded(computeEngine);
    }

    @Test(priority = 2)
    public void verifyEstimatedCosts() {
        gcEstimateSummaryPage.assertEstimatedCost(estimatedCost);
    }
}
