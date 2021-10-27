package com.github.vihaan.isrentalapp.devices;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;

public class Computer {

    @NotEmpty
    private final String otNumber;
    @NotEmpty
    private final String serialNumber;
    @NotNull
    private OperatingSystem operatingSystem;
    @NotNull
    private DiskType diskType;

    @NotNull
    private final ComputerModel computerModel;
    @NotNull
    private final ComputerProducer computerProducer;

    @NotNull
    private final List<ComputerRental> computerRentals;

    public Computer(String otNumber,
                    String serialNumber,
                    OperatingSystem operatingSystem,
                    DiskType diskType,
                    ComputerModel computerModel,
                    ComputerProducer computerProducer,
                    List<ComputerRental> computerRentals) {
        this.otNumber = otNumber;
        this.serialNumber = serialNumber;
        this.operatingSystem = operatingSystem;
        this.diskType = diskType;
        this.computerModel = computerModel;
        this.computerProducer = computerProducer;
        this.computerRentals = computerRentals;
    }

    public String getOtNumber() {
        return otNumber;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public OperatingSystem getOperatingSystem() {
        return operatingSystem;
    }

    public void setOperatingSystem(OperatingSystem operatingSystem) {
        this.operatingSystem = operatingSystem;
    }

    public DiskType getDiskType() {
        return diskType;
    }

    public void setDiskType(DiskType diskType) {
        this.diskType = diskType;
    }

    public ComputerModel getComputerModel() {
        return computerModel;
    }

    public ComputerProducer getComputerProducer() {
        return computerProducer;
    }

    public List<ComputerRental> getComputerRentals() {
        return computerRentals;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Computer computer = (Computer) o;
        return Objects.equals(getOtNumber(), computer.getOtNumber()) && Objects.equals(getSerialNumber(), computer.getSerialNumber());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getOtNumber(), getSerialNumber());
    }

    @Override
    public String toString() {
        return "Computer{" +
                "otNumber='" + otNumber + '\'' +
                ", serialNumber='" + serialNumber + '\'' +
                ", operatingSystem=" + operatingSystem +
                ", diskType=" + diskType +
                ", computerModel=" + computerModel +
                ", computerProducer=" + computerProducer +
                '}';
    }
}
