package com.github.vihaan.isrentalapp.devices.dto;

public class ComputerDTO {

    private String otnumber;
    private String serialNumber;
    private String operatingSystem;
    private String diskType;
    private String computerModelProducerName;
    private String model;
    private String computerStatus;

    public String getOtnumber() {
        return otnumber;
    }

    public void setOtnumber(String otnumber) {
        this.otnumber = otnumber;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getOperatingSystem() {
        return operatingSystem;
    }

    public void setOperatingSystem(String operatingSystem) {
        this.operatingSystem = operatingSystem;
    }

    public String getDiskType() {
        return diskType;
    }

    public void setDiskType(String diskType) {
        this.diskType = diskType;
    }

    public String getComputerModelProducerName() {
        return computerModelProducerName;
    }

    public void setComputerModelProducerName(String computerModelProducerName) {
        this.computerModelProducerName = computerModelProducerName;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getComputerStatus() {
        return computerStatus;
    }

    public void setComputerStatus(String computerStatus) {
        this.computerStatus = computerStatus;
    }
}
