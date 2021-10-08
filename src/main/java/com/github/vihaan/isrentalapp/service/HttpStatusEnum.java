package com.github.vihaan.isrentalapp.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public enum HttpStatusEnum {
    OK, CONFLICT, BADREQUEST, NOTFOUND, CREATED;

    public static ResponseEntity<?> isHttpStatusEquals(HttpStatusEnum status){
        if(status == HttpStatusEnum.BADREQUEST)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        if(status == HttpStatusEnum.CONFLICT)
            return new ResponseEntity<>(HttpStatus.CONFLICT);

        if(status == HttpStatusEnum.CREATED)
            return new ResponseEntity<>(HttpStatus.CREATED);

        if(status == HttpStatusEnum.NOTFOUND)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
