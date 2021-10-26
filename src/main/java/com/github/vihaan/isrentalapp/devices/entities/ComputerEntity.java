package com.github.vihaan.isrentalapp.devices.entities;

import com.github.vihaan.isrentalapp.rentals.entities.ComputerRentalEntity;
import com.github.vihaan.isrentalapp.util.BaseEntity;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "computers")
public
class ComputerEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_computer")
    private Long id;

    @Column(name = "OT_number", nullable = false, unique = true)
    private String otnumber;

    @Column(name = "serial_number", nullable = false, unique = true)
    private String serialNumber;

    @ManyToOne//(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "os_id")
    private OperatingSystemEntity operatingSystemEntity;

    @ManyToOne//(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "disk_id")
    private DiskTypeEntity diskTypeEntity;

    @ManyToOne//(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "model_id")
    private ComputerModelEntity computerModelEntity;

    @ManyToOne//(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "status_id")
    private ComputerStatusEntity computerStatusEntity;

    @OneToMany(mappedBy = "rentedComputerEntity", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
    private List<ComputerRentalEntity> computerRentalEntities;

    public ComputerEntity(){}
    public ComputerEntity(String otnumber, String serialNumber){
        this.otnumber = otnumber;
        this.serialNumber = serialNumber;
    }
    public ComputerEntity(String otnumber, String serialNumber, OperatingSystemEntity operatingSystemEntity, DiskTypeEntity diskTypeEntity, ComputerModelEntity computerModelEntity, ComputerStatusEntity computerStatusEntity) {
        this.otnumber = otnumber;
        this.serialNumber = serialNumber;
        this.operatingSystemEntity = operatingSystemEntity;
        this.diskTypeEntity = diskTypeEntity;
        this.computerModelEntity = computerModelEntity;
        this.computerStatusEntity = computerStatusEntity;
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

    public OperatingSystemEntity getOperatingSystem() {
        return operatingSystemEntity;
    }

    public void setOperatingSystem(OperatingSystemEntity operatingSystemEntity) {
        this.operatingSystemEntity = operatingSystemEntity;
    }

    public ComputerModelEntity getComputerModel() {
        return computerModelEntity;
    }

    public void setComputerModel(ComputerModelEntity computerModelEntity) {
        this.computerModelEntity = computerModelEntity;
    }

    public List<ComputerRentalEntity> getComputerRentals() {
        return computerRentalEntities;
    }

    public void setComputerRentals(List<ComputerRentalEntity> computerRentalEntities) {
        this.computerRentalEntities = computerRentalEntities;
    }

    public DiskTypeEntity getDiskType() {
        return diskTypeEntity;
    }

    public void setDiskType(DiskTypeEntity diskTypeEntity) {
        this.diskTypeEntity = diskTypeEntity;
    }

    public ComputerStatusEntity getComputerStatus() {
        return computerStatusEntity;
    }

    public void setComputerStatus(ComputerStatusEntity computerStatusEntity) {
        this.computerStatusEntity = computerStatusEntity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ComputerEntity computerEntity = (ComputerEntity) o;
        return getUuid().equals(computerEntity.getUuid());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUuid());
    }

    @Override
    public String toString() {
        return "Computer{" +
                "id=" + id +
                ", OTNumber='" + otnumber + '\'' +
                ", serialNumber='" + serialNumber + '\'' +
                ", operatingSystem=" + operatingSystemEntity +
                ", diskType=" + diskTypeEntity +
                ", computerModel=" + computerModelEntity +
                ", computerStatus=" + computerStatusEntity +
                '}';
    }
}
