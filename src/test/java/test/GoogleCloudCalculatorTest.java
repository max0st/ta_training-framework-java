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
    public void verifySeriesTest() {
        gcEstimateSummaryPage.assertSeries(computeEngine);
    }

    @Test(priority = 3)
    public void verifyNumberOfGPUsTest() {
        gcEstimateSummaryPage.assertNumberOfGPUs(computeEngine);
    }

    @Test(priority = 3)
    public void verifyNumberOfInstancesTest() {
        gcEstimateSummaryPage.assertNumberOfInstances(computeEngine);
    }

    @Test(priority = 3)
    public void verifyLocalSSDTest() {
        gcEstimateSummaryPage.assertSSD(computeEngine);
    }

    @Test(priority = 3)
    public void verifyProvisioningModelTest() {
        gcEstimateSummaryPage.assertProvisioningModel(computeEngine);
    }

    @Test(priority = 3)
    public void verifyGPUAddedTest() {
        gcEstimateSummaryPage.assertGPUAdded(computeEngine);
    }

    @Test(priority = 2)
    public void verifyEstimatedCostsTest() {
        gcEstimateSummaryPage.assertEstimatedCost(estimatedCost);
    }
}
