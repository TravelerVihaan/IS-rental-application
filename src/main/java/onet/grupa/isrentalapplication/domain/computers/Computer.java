package onet.grupa.isrentalapplication.domain.computers;

import onet.grupa.isrentalapplication.domain.rentals.ComputerRentals;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "computers")
public class Computer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_computer")
    private Long id;

    @NotEmpty
    @Column(name = "OT_number", nullable = false, unique = true)
    private String OTNumber;

    @NotEmpty
    @Column(name = "serial_number", nullable = false, unique = true)
    private String serialNumber;

    @NotNull
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "os_id")
    private OperatingSystem operatingSystem;

    @NotNull
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "model_id")
    private ComputerModel computerModel;

    @OneToMany(mappedBy = "rentedComputer")
    private List<ComputerRentals> computerRentals;

    public Computer(){}
    public Computer(@NotEmpty String OTNumber, @NotEmpty String serialNumber, @NotNull OperatingSystem operatingSystem, @NotNull ComputerModel computerModel) {
        this.OTNumber = OTNumber;
        this.serialNumber = serialNumber;
        this.operatingSystem = operatingSystem;
        this.computerModel = computerModel;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public List<ComputerRentals> getComputerRentals() {
        return computerRentals;
    }

    public void setComputerRentals(List<ComputerRentals> computerRentals) {
        this.computerRentals = computerRentals;
    }

    @Override
    public String toString() {
        return "Computer{" +
                "id=" + id +
                ", OTNumber='" + OTNumber + '\'' +
                ", serialNumber='" + serialNumber + '\'' +
                ", operatingSystem=" + operatingSystem +
                ", producer=" + computerModel +
                '}';
    }
}
