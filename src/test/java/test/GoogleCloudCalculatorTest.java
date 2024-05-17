package test;


import data.TestData;
import model.ComputeEngine;
import org.testng.annotations.Test;
import page.GoogleCloudEstimateSummaryPage;
import page.GoogleCloudPage;
import page.GoogleCloudPricingCalculatorPage;
import service.ComputerEngineCreator;

import static org.testng.Assert.assertEquals;


public class GoogleCloudCalculatorTest extends BaseTest {
    private GoogleCloudEstimateSummaryPage gcEstimateSummaryPage;
    private String estimatedCost;
    private GoogleCloudPricingCalculatorPage googleCloudPricingCalculatorPage;
    private ComputeEngine computeEngine = ComputerEngineCreator.createFromProperties();

    @Test
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
        gcEstimateSummaryPage.verifySeriesTest(computeEngine)
                .verifyNumberOfGPUsTest(computeEngine)
                .verifyNumberOfInstancesTest(computeEngine)
                .verifyGPUAddedTest(computeEngine)
                .verifyLocalSSDTest(computeEngine)
                .verifyProvisioningModelTest(computeEngine);
    }
    @Test
    public void testVerifyEstimatedCost() {

    }
}
