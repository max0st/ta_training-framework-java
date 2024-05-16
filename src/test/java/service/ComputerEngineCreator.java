package service;

import model.ComputeEngine;

import static service.TestDataReader.getTestData;

public class ComputerEngineCreator {

    private static final String NUMBER_OF_INSTANCES = "testdata.computeEngine.numberOfInstances";
    private static final String OPERATING_SYSTEM = "testdata.computeEngine.operatingSystem";
    private static final String MACHINE_FAMILY = "testdata.computeEngine.machineFamily";
    private static final String SERIES = "testdata.computeEngine.series";
    private static final String MACHINE_TYPE = "testdata.computeEngine.machineType";
    private static final String GPU_TYPE = "testdata.computeEngine.gpuType";
    private static final String NUMBER_OF_GPUS = "testdata.computeEngine.numberOfGpus";
    private static final String LOCAL_SSD = "testdata.computeEngine.localSSD";
    private static final String DATACENTER_LOCATION = "testdata.computeEngine.datacenterLocation";
    private static final String COMMITTED_USAGE_TERM = "testdata.computeEngine.committedUsageTerm";

    public static ComputeEngine createFromProperties() {
        return new ComputeEngine(Integer.parseInt(getTestData(NUMBER_OF_INSTANCES)),
                getTestData(OPERATING_SYSTEM),
                getTestData(MACHINE_FAMILY),
                getTestData(SERIES),
                getTestData(MACHINE_TYPE),
                getTestData(GPU_TYPE),
                Integer.parseInt(getTestData(NUMBER_OF_GPUS)),
                getTestData(LOCAL_SSD),
                getTestData(DATACENTER_LOCATION),
                getTestData(COMMITTED_USAGE_TERM));
    }
}
