package onet.grupa.isrentalapplication.controller.rentals;

import onet.grupa.isrentalapplication.domain.rentals.RentStatus;
import onet.grupa.isrentalapplication.service.rentals.RentStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
        return rentStatusService.getResponseWithAllStatuses();
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RentStatus> getStatus(@PathVariable long id){
        return rentStatusService.getResponseWithStatus(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addNewStatus(@RequestBody @Valid RentStatus rentStatus, BindingResult result){
        return rentStatusService.addNewRentStatus(rentStatus, result);
    }
}
