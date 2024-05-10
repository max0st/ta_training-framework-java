package model;

import java.util.Objects;

public class ComputeEngine {
    private int numberOfInstances;
    private String operatingSystem;
    private String provisioningModel;
    private String machineFamily;
    private String series;
    private String machineType;
    private boolean gpuAdded;
    private String gpuType;
    private int numberOfGPUs;
    private String localSsd;
    private String datacenterLocation;
    private String committedUsageTerm;

    public ComputeEngine(int numberOfInstances, String operatingSystem, String machineFamily, String series, String machineType, String gpuType, int numberOfGPUs, String localSsd, String datacenterLocation, String committedUsageTerm) {
        this.numberOfInstances = numberOfInstances;
        this.operatingSystem = operatingSystem;
        this.provisioningModel = "Regular";
        this.machineFamily = machineFamily;
        this.series = series;
        this.machineType = machineType;
        this.gpuAdded = true;
        this.gpuType = gpuType;
        this.numberOfGPUs = numberOfGPUs;
        this.localSsd = localSsd;
        this.datacenterLocation = datacenterLocation;
        this.committedUsageTerm = committedUsageTerm;
    }

    public String getProvisioningModel() {
        return provisioningModel;
    }

    public String getMachineFamily() {
        return machineFamily;
    }

    public int getNumberOfInstances() {
        return numberOfInstances;
    }

    public String getOperatingSystem() {
        return operatingSystem;
    }


    public String getSeries() {
        return series;
    }


    public String getMachineType() {
        return machineType;
    }


    public boolean isGpuAdded() {
        return gpuAdded;
    }


    public String getGpuType() {
        return gpuType;
    }


    public int getNumberOfGPUs() {
        return numberOfGPUs;
    }


    public String getLocalSsd() {
        return localSsd;
    }


    public String getDatacenterLocation() {
        return datacenterLocation;
    }


    public String getCommittedUsageTerm() {
        return committedUsageTerm;
    }


    @Override
    public String toString() {
        return "ComputeEngine{" +
                "numberOfInstances=" + numberOfInstances +
                ", operatingSystem='" + operatingSystem + '\'' +
                ", provisioningModel='" + provisioningModel + '\'' +
                ", machineFamily='" + machineFamily + '\'' +
                ", series='" + series + '\'' +
                ", machineType='" + machineType + '\'' +
                ", gpuAdded=" + gpuAdded +
                ", gpuType='" + gpuType + '\'' +
                ", numberOfGpus=" + numberOfGPUs +
                ", localSsd='" + localSsd + '\'' +
                ", datacenterLocation='" + datacenterLocation + '\'' +
                ", committedUsageTerm='" + committedUsageTerm + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ComputeEngine)) return false;
        ComputeEngine that = (ComputeEngine) o;
        return numberOfInstances == that.numberOfInstances &&
                gpuAdded == that.gpuAdded &&
                numberOfGPUs == that.numberOfGPUs &&
                Objects.equals(operatingSystem, that.operatingSystem) &&
                Objects.equals(provisioningModel, that.provisioningModel) &&
                Objects.equals(machineFamily, that.machineFamily) &&
                Objects.equals(series, that.series) &&
                Objects.equals(machineType, that.machineType) &&
                Objects.equals(gpuType, that.gpuType) &&
                Objects.equals(localSsd, that.localSsd) &&
                Objects.equals(datacenterLocation, that.datacenterLocation) &&
                Objects.equals(committedUsageTerm, that.committedUsageTerm);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numberOfInstances, operatingSystem, provisioningModel, machineFamily, series, machineType, gpuAdded, gpuType, numberOfGPUs, localSsd, datacenterLocation, committedUsageTerm);
    }

    public String getSeriesFromMachineType() {
        String fullType = machineType;

        int dashIndex = fullType.indexOf('-');

        if (dashIndex != -1) {
            return fullType.substring(0, dashIndex);
        }

        return fullType;
    }
}
