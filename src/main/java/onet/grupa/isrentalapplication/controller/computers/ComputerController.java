package onet.grupa.isrentalapplication.controller.computers;

import onet.grupa.isrentalapplication.devices.entities.Computer;
import onet.grupa.isrentalapplication.dto.ComputerDTO;
import onet.grupa.isrentalapplication.service.HttpStatusEnum;
import onet.grupa.isrentalapplication.service.computers.ComputerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/computers/computers")
public class ComputerController {

    private ComputerService computerService;
    private ModelMapper modelMapper;

    @Autowired
    public ComputerController(ComputerService computerService,
                              ModelMapper modelMapper){
        this.computerService = computerService;
        this.modelMapper = modelMapper;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ComputerDTO>> getComputers(@RequestParam(required = false) String searchPhrase,
                                                          @RequestParam(required = false) String orderBy){
        List<ComputerDTO> computers = computerService.getComputers(searchPhrase, orderBy)
                .stream().map(computer -> modelMapper.map(computer, ComputerDTO.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(computers);
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ComputerDTO> getComputer(@PathVariable long id){
        Optional<Computer> computer = computerService.getComputer(id);
        if(computer.isEmpty())
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(modelMapper.map(computer, ComputerDTO.class));
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<?> changeComputerStatus(@PathVariable Long id, @RequestParam String status){
        HttpStatusEnum statusEnum = computerService.changeComputerStatus(status, id);
        return HttpStatusEnum.isHttpStatusEquals(statusEnum);
    }

    @PatchMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateComputer(@RequestBody Map<String, String> updates, @PathVariable Long id){
        HttpStatusEnum status = computerService.updateComputer(id, updates);
        return HttpStatusEnum.isHttpStatusEquals(status);
    }

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
