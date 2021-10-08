package com.github.vihaan.isrentalapp.devices.entities;

import com.github.vihaan.isrentalapp.rentals.entities.ComputerRental;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "computers")
class Computer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_computer")
    private Long id;

    @NotEmpty
    @Column(name = "OT_number", nullable = false, unique = true)
    private String otnumber;

    @NotEmpty
    @Column(name = "serial_number", nullable = false, unique = true)
    private String serialNumber;

    @NotNull
    @ManyToOne//(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "os_id")
    private OperatingSystem operatingSystem;

    @NotNull
    @ManyToOne//(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "disk_id")
    private DiskType diskType;

    @NotNull
    @ManyToOne//(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "model_id")
    private ComputerModel computerModel;

    @NotNull
    @ManyToOne//(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "status_id")
    private ComputerStatus computerStatus;

    @OneToMany(mappedBy = "rentedComputer", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
    private List<ComputerRental> computerRentals;

    public Computer(){}
    public Computer(@NotEmpty String otnumber, @NotEmpty String serialNumber){
        this.otnumber = otnumber;
        this.serialNumber = serialNumber;
    }
    public Computer(@NotEmpty String otnumber, @NotEmpty String serialNumber, @NotNull OperatingSystem operatingSystem, @NotNull DiskType diskType, @NotNull ComputerModel computerModel, @NotNull ComputerStatus computerStatus) {
        this.otnumber = otnumber;
        this.serialNumber = serialNumber;
        this.operatingSystem = operatingSystem;
        this.diskType = diskType;
        this.computerModel = computerModel;
        this.computerStatus = computerStatus;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public OperatingSystem getOperatingSystem() {
        return operatingSystem;
    }

    public void setOperatingSystem(OperatingSystem operatingSystem) {
        this.operatingSystem = operatingSystem;
    }

    public ComputerModel getComputerModel() {
        return computerModel;
    }

    public void setComputerModel(ComputerModel computerModel) {
        this.computerModel = computerModel;
    }

    public List<ComputerRental> getComputerRentals() {
        return computerRentals;
    }

    public void setComputerRentals(List<ComputerRental> computerRentals) {
        this.computerRentals = computerRentals;
    }

    public DiskType getDiskType() {
        return diskType;
    }

    public void setDiskType(DiskType diskType) {
        this.diskType = diskType;
    }

    public ComputerStatus getComputerStatus() {
        return computerStatus;
    }

    public void setComputerStatus(ComputerStatus computerStatus) {
        this.computerStatus = computerStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Computer computer = (Computer) o;
        return Objects.equals(id, computer.id) &&
                Objects.equals(otnumber, computer.otnumber) &&
                Objects.equals(serialNumber, computer.serialNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, otnumber, serialNumber);
    }

    @Override
    public String toString() {
        return "Computer{" +
                "id=" + id +
                ", OTNumber='" + otnumber + '\'' +
                ", serialNumber='" + serialNumber + '\'' +
                ", operatingSystem=" + operatingSystem +
                ", diskType=" + diskType +
                ", computerModel=" + computerModel +
                ", computerStatus=" + computerStatus +
                '}';
    }
}
