package com.github.vihaan.isrentalapp.devices;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Objects;

public class ComputerModel {

    @NotEmpty
    private final String modelName;

    @NotNull
    private final ComputerProducer computerProducer;


    public ComputerModel(String modelName, ComputerProducer computerProducer) {
        this.modelName = modelName;
        this.computerProducer = computerProducer;
    }

    public String getModelName() {
        return modelName;
    }

    public ComputerProducer getComputerProducer() {
        return computerProducer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ComputerModel that = (ComputerModel) o;
        return Objects.equals(getModelName(), that.getModelName()) && Objects.equals(getComputerProducer(), that.getComputerProducer());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getModelName(), getComputerProducer());
    }

    @Override
    public String toString() {
        return "ComputerModel{" +
                "modelName='" + modelName + '\'' +
                ", computerProducer=" + computerProducer +
                '}';
    }
}
