package onet.grupa.isrentalapplication.controller.computers;

import onet.grupa.isrentalapplication.domain.computers.ComputerModel;
import onet.grupa.isrentalapplication.dto.ComputerModelDTO;
import onet.grupa.isrentalapplication.service.HttpStatusEnum;
import onet.grupa.isrentalapplication.service.computers.ComputerModelService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("computers/models")
public class ComputerModelController {

    private ComputerModelService computerModelService;
    private ModelMapper modelMapper;

    @Autowired
    public ComputerModelController(ComputerModelService computerModelService, ModelMapper modelMapper){
        this.computerModelService = computerModelService;
        this.modelMapper = modelMapper;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ComputerModelDTO>> getAllComputerModels(){
        return ResponseEntity.of(Optional.of(computerModelService
                .getAllComputerModels()
                .stream()
                .map(model -> modelMapper.map(model, ComputerModelDTO.class))
                .collect(Collectors.toList())));
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ComputerModel> getComputerModel(@PathVariable long id){
        return ResponseEntity.of(computerModelService.getComputerModel(id));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addComputerProducer(@RequestBody ComputerModel computerModel){
        HttpStatusEnum status = computerModelService.addNewComputerModel(computerModel);
        return HttpStatusEnum.isHttpStatusEquals(status);
    }
}
