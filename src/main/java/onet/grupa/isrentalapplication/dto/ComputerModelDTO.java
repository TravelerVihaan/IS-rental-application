package onet.grupa.isrentalapplication.dto;

public class ComputerModelDTO {

    private String model;

    private ComputerProducerDTO computerProducer;

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public ComputerProducerDTO getComputerProducer() {
        return computerProducer;
    }

    public void setComputerProducer(ComputerProducerDTO computerProducer) {
        this.computerProducer = computerProducer;
    }
}
