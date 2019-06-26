package onet.grupa.isrentalapplication.controller.computers;

import onet.grupa.isrentalapplication.domain.computers.Computer;
import onet.grupa.isrentalapplication.service.computers.ComputerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/computers/computers")
public class ComputerController {

    private ComputerService computerService;

    @Autowired
    public ComputerController(ComputerService computerService){
        this.computerService = computerService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<Computer>> getComputers(){
        return null;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Computer> getComputer(@PathVariable long id){
        return new ResponseEntity<>(computerService.findComputerById(id), new HttpHeaders(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> addComputer(@PathVariable("id") Long id, @RequestBody @Valid Computer computer, BindingResult result){
        if (result.hasErrors())
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        return new ResponseEntity(computerService.updateComputer(id,computer), new HttpHeaders(), HttpStatus.OK);


    }
}
