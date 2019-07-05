package onet.grupa.isrentalapplication.controller.computers;

import onet.grupa.isrentalapplication.domain.computers.ComputerModel;
import onet.grupa.isrentalapplication.service.HttpStatusEnum;
import onet.grupa.isrentalapplication.service.computers.ComputerModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("computers/models")
public class ComputerModelController {

    private ComputerModelService computerModelService;

    @Autowired
    public ComputerModelController(ComputerModelService computerModelService){
        this.computerModelService = computerModelService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ComputerModel>> getAllComputerModels(){
        return ResponseEntity.of(computerModelService.getAllComputerModels());
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
