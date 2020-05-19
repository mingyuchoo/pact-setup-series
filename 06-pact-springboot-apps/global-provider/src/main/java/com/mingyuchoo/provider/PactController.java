package com.mingyuchoo.provider;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
public class PactController {

    @GetMapping(
            path = "/user",
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public String getPact() {
        return "{\"status:true\"}";
        // return "{\"id\":\"e0520\",\"name\":\"Smith\"}";
        // return "{\"id\":\"e0520\",\"name\":\"Smith\",\"username\":\"Smith\"}";
        // return
        // "{\"id\":\"e0520\",\"name\":\"Smith\",\"username\":\"Smith\",\"userId\":\"e0520\"}";

    }
}
