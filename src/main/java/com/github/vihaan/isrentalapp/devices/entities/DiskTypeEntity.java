package com.github.vihaan.isrentalapp.devices.entities;

import com.github.vihaan.isrentalapp.util.BaseEntity;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "disk_type")
class DiskTypeEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_disk")
    private Long id;

    @Column(name = "disk_type", nullable = false, unique = true)
    private String diskType;

    @OneToMany(mappedBy = "diskTypeEntity")
    private List<ComputerEntity> computerEntities;

    public DiskTypeEntity(){}
    public DiskTypeEntity(String diskType) {
        this.diskType = diskType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDiskType() {
        return diskType;
    }

    public void setDiskType(String diskType) {
        this.diskType = diskType;
    }

    public List<ComputerEntity> getComputers() {
        return computerEntities;
    }

    public void setComputers(List<ComputerEntity> computerEntities) {
        this.computerEntities = computerEntities;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DiskTypeEntity diskTypeEntity = (DiskTypeEntity) o;
        return getUuid().equals(diskTypeEntity.getUuid());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUuid());
    }

    @Override
    public String toString() {
        return "DiskType{" +
                "id=" + id +
                ", diskType='" + diskType + '\'' +
                '}';
    }
}
