package onet.grupa.isrentalapplication.controller.computers;

import onet.grupa.isrentalapplication.domain.computers.ComputerProducer;
import onet.grupa.isrentalapplication.service.computers.ComputerProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/computers/producers")
public class ComputerProducerController {

    private ComputerProducerService computerProducerService;

    @Autowired
    public ComputerProducerController(ComputerProducerService computerProducerService){
        this.computerProducerService = computerProducerService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ComputerProducer>> getAllComputerProducers(){
        return computerProducerService.getResponseWithAllComputerProducers();
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ComputerProducer> getComputerProducer(@PathVariable long id){
        return computerProducerService.getResponseWithComputerProducer(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addComputerProducer(@RequestBody ComputerProducer computerProducer){
        return computerProducerService.addNewComputerProducer(computerProducer);
    }
}
