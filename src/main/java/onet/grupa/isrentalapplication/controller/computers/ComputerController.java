package onet.grupa.isrentalapplication.controller.computers;

import onet.grupa.isrentalapplication.domain.computers.Computer;
import onet.grupa.isrentalapplication.dto.ComputerDTO;
import onet.grupa.isrentalapplication.service.HttpStatusEnum;
import onet.grupa.isrentalapplication.service.computers.ComputerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
    public ResponseEntity<List<ComputerDTO>> getComputers(@RequestParam(required = false) String searchPhrase, @RequestParam(required = false) String orderBy){
        List<Computer> computers = new ArrayList<>();
        if(searchPhrase != null && !searchPhrase.isEmpty())
            computers = computerService.getAllComputers();
        else
            computers = computerService.getSpecificComputers(searchPhrase, orderBy);

        return ResponseEntity.ok(computers.stream()
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
        HttpStatusEnum statusEnum = computerService.changeComputerStatus(status, id);
        return HttpStatusEnum.isHttpStatusEquals(statusEnum);
    }

    /**
    Update existing computer from database
     */
    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addComputer(@RequestBody ComputerDTO computerDTO){
        Computer computer = modelMapper.map(computerDTO, Computer.class);
        HttpStatusEnum status = computerService.addNewComputer(computer);
        return HttpStatusEnum.isHttpStatusEquals(status);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteComputer(@PathVariable Long id){
        HttpStatusEnum status = computerService.deleteComputer(id);
        return HttpStatusEnum.isHttpStatusEquals(status);
    }
}
