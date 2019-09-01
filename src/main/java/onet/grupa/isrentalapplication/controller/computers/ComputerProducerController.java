package onet.grupa.isrentalapplication.controller.computers;

import onet.grupa.isrentalapplication.domain.computers.ComputerProducer;
import onet.grupa.isrentalapplication.dto.ComputerProducerDTO;
import onet.grupa.isrentalapplication.service.HttpStatusEnum;
import onet.grupa.isrentalapplication.service.computers.ComputerProducerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/computers/producers")
public class ComputerProducerController {

    private ComputerProducerService computerProducerService;
    private ModelMapper modelMapper;

    @Autowired
    public ComputerProducerController(ComputerProducerService computerProducerService, ModelMapper modelMapper){
        this.computerProducerService = computerProducerService;
        this.modelMapper = modelMapper;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ComputerProducerDTO>> getAllComputerProducers(){
        return ResponseEntity.ok(computerProducerService
                .getAllComputerProducers()
                .stream()
                .map(producer -> modelMapper.map(producer, ComputerProducerDTO.class))
                .collect(Collectors.toList()));
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ComputerProducerDTO> getComputerProducer(@PathVariable long id){
        Optional<ComputerProducerDTO> computerProducerDTO = Optional.ofNullable(modelMapper
            .map(computerProducerService.getComputerProducerById(id).orElseThrow(),ComputerProducerDTO.class));
        return ResponseEntity.of(computerProducerDTO);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addComputerProducer(@RequestBody ComputerProducerDTO computerProducerDTO){
        ComputerProducer computerProducer = modelMapper.map(computerProducerDTO, ComputerProducer.class);
        HttpStatusEnum status = computerProducerService.addNewComputerProducer(computerProducer);
        return HttpStatusEnum.isHttpStatusEquals(status);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteComputerProducer(@PathVariable Long id){
        HttpStatusEnum status = computerProducerService.deleteComputerProducer(id);
        return HttpStatusEnum.isHttpStatusEquals(status);
    }
}
