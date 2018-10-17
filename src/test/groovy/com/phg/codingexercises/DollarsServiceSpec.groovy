package com.phg.codingexercises

import spock.lang.Specification

class DollarsServiceSpec extends Specification {
  DollarsService service = new DollarsService()

  void 'calculates and constructs page result for 0 cents'() {
    when:
    PageResult result = service.calculateCoinCounts('0')

    then:
    result.results.size() == 1
    result.results[0].type == 'coinCounts'
    result.results[0].input == 0.00
    result.results[0].quarters == 0
    result.results[0].dimes == 0
    result.results[0].nickels == 0
    result.results[0].pennies == 0
  }
}
