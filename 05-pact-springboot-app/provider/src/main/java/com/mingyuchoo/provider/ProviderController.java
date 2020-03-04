package com.mingyuchoo.provider;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProviderController {

  private static final String template = "Hello, %s!";
  private final AtomicLong counter = new AtomicLong();

  @RequestMapping("/")
  public Provider Provider(@RequestParam(value = "name", defaultValue = "World") String name) {
    return new Provider(counter.incrementAndGet(), String.format(template, name));
  }
}
