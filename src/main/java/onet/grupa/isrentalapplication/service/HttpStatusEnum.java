package onet.grupa.isrentalapplication.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public enum HttpStatusEnum {
    OK, CONFLICT, BADREQUEST, NOTFOUND, CREATED;

    public static ResponseEntity<?> isHttpStatusEquals(HttpStatusEnum status){
        if(status == HttpStatusEnum.BADREQUEST)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        if(status == HttpStatusEnum.CONFLICT)
            return new ResponseEntity<>(HttpStatus.CONFLICT);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
