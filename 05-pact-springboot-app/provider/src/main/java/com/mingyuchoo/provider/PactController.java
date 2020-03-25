package com.mingyuchoo.provider;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class PactController {

  @GetMapping(path = "/pact", produces = "application/json")
  public String getPact() { return "{\"condition\": true, \"name\": \"choo\"}"; }

  @PostMapping(path = "/pact")
  @ResponseStatus(HttpStatus.CREATED)
  public void postPact() {

  }
}
