package service;

import model.ComputeEngine;

import static data.ComputeEngineData.*;
import static service.TestDataReader.getTestData;

public class ComputerEngineCreator {

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
