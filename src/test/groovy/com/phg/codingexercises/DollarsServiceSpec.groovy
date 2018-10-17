package com.phg.codingexercises

import spock.lang.Specification
import spock.lang.Unroll

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

  @Unroll
  void 'calculates and constructs page results for #amount'() {
    when:
    PageResult result = service.calculateCoinCounts(amount)

    then:
    result.results.size() == 1
    result.results[0].type == 'coinCounts'
    result.results[0].input == inputValue
    result.results[0].quarters == quarters
    result.results[0].dimes == dimes
    result.results[0].nickels == nickels
    result.results[0].pennies == pennies

    where:
    amount | inputValue | quarters | dimes | nickels | pennies
    '.2'   | 0.20       | 0        | 2     | 0       | 0
    '.05'  | 0.05       | 0        | 0     | 1       | 0
    '.04'  | 0.04       | 0        | 0     | 0       | 4
    '1'    | 1.00       | 4        | 0     | 0       | 0
    '1.01' | 1.01       | 4        | 0     | 0       | 1
    '1.21' | 1.21       | 4        | 2     | 0       | 1
    '1.15' | 1.15       | 4        | 1     | 1       | 0
  }
}
