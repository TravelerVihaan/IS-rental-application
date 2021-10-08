package com.github.vihaan.isrentalapp.devices.entities;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "disk_type")
class DiskType implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_disk")
    private Long id;

    @NotEmpty
    @Column(name = "disk_type", nullable = false, unique = true)
    private String diskType;

    @OneToMany(mappedBy = "diskType")
    private List<Computer> computers;

    public DiskType(){}
    public DiskType(@NotEmpty String diskType) {
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

    public List<Computer> getComputers() {
        return computers;
    }

    public void setComputers(List<Computer> computers) {
        this.computers = computers;
    }

    @Override
    public String toString() {
        return "DiskType{" +
                "id=" + id +
                ", diskType='" + diskType + '\'' +
                '}';
    }
}
