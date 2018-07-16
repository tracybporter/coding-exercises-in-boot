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
  PageResult retrievePrimeFactorsForSingleInput(@PathVariable int input) {
    service.calculatePrime(input)
  }
}
