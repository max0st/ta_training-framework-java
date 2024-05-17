package test;


import data.TestData;
import model.ComputeEngine;
import org.testng.annotations.Test;
import page.GoogleCloudEstimateSummaryPage;
import page.GoogleCloudPage;
import page.GoogleCloudPricingCalculatorPage;
import service.ComputerEngineCreator;

public class GoogleCloudCalculatorTest extends BaseTest {
    private final ComputeEngine computeEngine = ComputerEngineCreator.createFromProperties();
    private GoogleCloudEstimateSummaryPage gcEstimateSummaryPage;
    private String estimatedCost;
    private GoogleCloudPricingCalculatorPage googleCloudPricingCalculatorPage;

    @Test
    public void testComputeEngineCostEstimationProcess() {
        GoogleCloudPage googleCloudPage = new GoogleCloudPage(driver);
        googleCloudPricingCalculatorPage = googleCloudPage.openHomePage()
                .openSearchTextArea()
                .inputSearchText(TestData.TEXT_TO_SEARCH_ON_GOOGLE_CLOUD_PLATFORM)
                .startSearch()
                .openLinkToCalculator()
                .pressAddToEstimate()
                .pressComputerEngine()
                .specifyInstancesNumber(computeEngine.getNumberOfInstances())
                .openOperatingSystemMenu()
                .chooseOperatingSystem(computeEngine.getOperatingSystem())
                .chooseRegular().openMachineFamilyMenu()
                .chooseMachineFamily(computeEngine.getMachineFamily())
                .openSeriesMenu().chooseSeries(computeEngine.getSeries())
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
        gcEstimateSummaryPage.verifySeriesTest(computeEngine)
                .verifyNumberOfGPUsTest(computeEngine)
                .verifyNumberOfInstancesTest(computeEngine)
                .verifyGPUAddedTest(computeEngine)
                .verifyLocalSSDTest(computeEngine)
                .verifyProvisioningModelTest(computeEngine);
    }

    /**
     * This test method is included to demonstrate that multiple test suites can
     * operate independently. It is included only in the testng-all suite to showcase
     * different configurations for separate execution paths.
     */
    @Test
    public void testVerifyEstimatedCost() {
        gcEstimateSummaryPage.verifyEstimatedCost(estimatedCost);
    }
}
