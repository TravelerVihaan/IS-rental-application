package com.github.vihaan.isrentalapp.devices;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;

public class ComputerProducer {

    @NotNull
    private final String producerName;

    private final List<ComputerModel> computerModels;

    public ComputerProducer(String producerName, List<ComputerModel> computerModels) {
        this.producerName = producerName;
        this.computerModels = computerModels;
    }

    public String getProducerName() {
        return producerName;
    }

    public List<ComputerModel> getComputerModels() {
        return computerModels;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ComputerProducer that = (ComputerProducer) o;
        return Objects.equals(getProducerName(), that.getProducerName()) && Objects.equals(getComputerModels(), that.getComputerModels());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getProducerName(), getComputerModels());
    }

    @Override
    public String toString() {
        return "ComputerProducer{" +
                "producerName='" + producerName + '\'' +
                ", computerModels=" + computerModels +
                '}';
    }
}
