package onet.grupa.isrentalapplication.dto;

public class ComputerDTO {

    private String OTNumber;
    private String serialNumber;
    private OperatingSystemDTO operatingSystem;
    private DiskTypeDTO diskType;
    private ComputerModelDTO computerModel;
    private ComputerStatusDTO computerStatus;

    public String getOTNumber() {
        return OTNumber;
    }

    public void setOTNumber(String OTNumber) {
        this.OTNumber = OTNumber;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public OperatingSystemDTO getOperatingSystem() {
        return operatingSystem;
    }

    public void setOperatingSystem(OperatingSystemDTO operatingSystem) {
        this.operatingSystem = operatingSystem;
    }

    public DiskTypeDTO getDiskType() {
        return diskType;
    }

    public void setDiskType(DiskTypeDTO diskType) {
        this.diskType = diskType;
    }

    public ComputerModelDTO getComputerModel() {
        return computerModel;
    }

    public void setComputerModel(ComputerModelDTO computerModel) {
        this.computerModel = computerModel;
    }

    public ComputerStatusDTO getComputerStatus() {
        return computerStatus;
    }

    public void setComputerStatus(ComputerStatusDTO computerStatus) {
        this.computerStatus = computerStatus;
    }
}
