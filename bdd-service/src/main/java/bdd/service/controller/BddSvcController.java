package bdd.service.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by p0a00hg on 11/11/22
 **/

@RestController
public class BddSvcController {

    @GetMapping("/status")
    public ResponseEntity<String> status() {
        return new ResponseEntity<>("Application is UP and running", HttpStatus.OK);
    }
}
