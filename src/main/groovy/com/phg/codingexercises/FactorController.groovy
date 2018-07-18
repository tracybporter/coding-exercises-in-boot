package com.phg.codingexercises

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(value = '/api/v1/factors')
class FactorController {
  @Autowired
  FactoringService service

  @GetMapping("/{input}")
  PageResult retrievePrimeFactorsForSingleInput(@PathVariable String input) {
    if (input.isInteger()) {
      service.calculatePrime(input as Integer)
    } else {
      service.calculatePrime(input)
    }
  }
}
