package onet.grupa.isrentalapplication.controller.rentals;

import onet.grupa.isrentalapplication.rentals.entities.RentStatus;
import onet.grupa.isrentalapplication.service.rentals.RentStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rentals/status/")
public class RentStatusController {

    private RentStatusService rentStatusService;

    @Autowired
    public RentStatusController(RentStatusService rentStatusService){
        this.rentStatusService = rentStatusService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<RentStatus>> getAllStatuses(){
        return ResponseEntity.ok(rentStatusService.getAllStatuses());
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RentStatus> getStatus(@PathVariable long id){
        return ResponseEntity.of(rentStatusService.getStatus(id));
    }

}
