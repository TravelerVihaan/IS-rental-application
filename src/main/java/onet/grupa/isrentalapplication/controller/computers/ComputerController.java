package onet.grupa.isrentalapplication.controller.computers;

import onet.grupa.isrentalapplication.domain.computers.Computer;
import onet.grupa.isrentalapplication.repository.computers.ComputerRepository;
import onet.grupa.isrentalapplication.service.computers.ComputerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/computers/computers")
public class ComputerController {

    private ComputerService computerService;

    @Autowired
    public ComputerController(ComputerService computerService){
        this.computerService = computerService;
    }

    @GetMapping
    ResponseEntity<List<Computer>> getComputers(){
        return null;
    }
}
