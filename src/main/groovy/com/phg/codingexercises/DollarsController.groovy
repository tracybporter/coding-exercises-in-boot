package com.phg.codingexercises

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(value = '/api/v1/coins')
class DollarsController {

  @Autowired
  DollarsService service

  @GetMapping("/{input:.+}")
  PageResult computeMinimumCoinCountForDollarAmount(@PathVariable String input) {
    service.calculateCoinCounts(input)
  }
}
