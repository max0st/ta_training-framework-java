package service;

import model.ComputeEngine;

import static service.TestDataReader.getTestData;

public class ComputerEngineCreator {

    public static final String NUMBER_OF_INSTANCES = "testdata.computeEngine.numberOfInstances";
    public static final String OPERATING_SYSTEM = "testdata.computeEngine.operatingSystem";
    public static final String MACHINE_FAMILY = "testdata.computeEngine.machineFamily";
    public static final String SERIES = "testdata.computeEngine.series";
    public static final String MACHINE_TYPE = "testdata.computeEngine.machineType";
    public static final String GPU_TYPE = "testdata.computeEngine.gpuType";
    public static final String NUMBER_OF_GPUS = "testdata.computeEngine.numberOfGpus";
    public static final String LOCAL_SSD = "testdata.computeEngine.localSSD";
    public static final String DATACENTER_LOCATION = "testdata.computeEngine.datacenterLocation";
    public static final String COMMITTED_USAGE_TERM = "testdata.computeEngine.committedUsageTerm";

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
