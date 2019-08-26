package onet.grupa.isrentalapplication.controller.computers;

import onet.grupa.isrentalapplication.domain.computers.Computer;
import onet.grupa.isrentalapplication.dto.ComputerDTO;
import onet.grupa.isrentalapplication.service.computers.ComputerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/computers/computers")
public class ComputerController {

    private ComputerService computerService;
    private ModelMapper modelMapper;

    @Autowired
    public ComputerController(ComputerService computerService, ModelMapper modelMapper){
        this.computerService = computerService;
        this.modelMapper = modelMapper;
    }

    /**
    Get all computers from database
     */
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ComputerDTO>> getComputers(){
        return ResponseEntity.ok(computerService
                .getAllComputers()
                .stream()
                .map(computer -> modelMapper.map(computer, ComputerDTO.class))
                .collect(Collectors.toList()));
    }

    /**
    Get specified computer from database by id
    @param id - id of computer from database
     */
    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ComputerDTO> getComputer(@PathVariable long id){
        Optional<ComputerDTO> computerDTO = Optional.ofNullable(modelMapper
                .map(computerService.getComputer(id).orElseThrow(),ComputerDTO.class));
        return ResponseEntity.of(computerDTO);
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<?> changeComputerStatus(@PathVariable Long id, @RequestParam String status){
        return computerService.changeComputerStatus(status, id);
    }

    /**
    Update existing computer from database
     */
    @PutMapping("/{id}")
    public ResponseEntity<Void> addComputer(@PathVariable("id") Long id, @RequestBody @Valid Computer computer, BindingResult result){
        if (result.hasErrors())
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(HttpStatus.OK);


    }
}
