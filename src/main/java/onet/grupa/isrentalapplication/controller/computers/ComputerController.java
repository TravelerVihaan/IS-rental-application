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

@RestController
@RequestMapping("/computers/computers")
public class ComputerController {

    private ComputerService computerService;

    @Autowired
    public ComputerController(ComputerService computerService){
        this.computerService = computerService;
    }

    /*
    Get all computers from database
     */
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Computer>> getComputers(){
        return new ResponseEntity<>(computerService.getAllComputers(), new HttpHeaders(), HttpStatus.OK);
    }

    /*
    Get specified computer from database by id
    @param id - id of computer from database
     */
    @GetMapping("/{id}")
    public ResponseEntity<Computer> getComputer(@PathVariable long id){
        return new ResponseEntity<>(computerService.findComputerById(id), new HttpHeaders(), HttpStatus.OK);
    }

    /*
    Update existing computer from database
     */
    @PutMapping("/{id}")
    public ResponseEntity<Void> addComputer(@PathVariable("id") Long id, @RequestBody @Valid Computer computer, BindingResult result){
        if (result.hasErrors())
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(HttpStatus.OK);


    }
}
