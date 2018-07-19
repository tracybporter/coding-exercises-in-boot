package com.phg.codingexercises

import spock.lang.Specification
import spock.lang.Unroll

class PrimeNumberCalculatorSpec extends Specification {

  @Unroll
  def 'returns prime factors for #input'() {
    when:
    PrimeNumberCalculator calculator = new PrimeNumberCalculator([input])

    then:
    calculator.calculatePrime(input) == expectedPrimeFactors

    where:
    input | expectedPrimeFactors
    2     | [1, 2]
    4     | [2, 2]
    9     | [3, 3]
    16    | [2, 2, 2, 2]
    17    | [1, 17]
    39    | [3, 13]
    221   | [13, 17]
  }

  void 'returns prime factors for each input'() {
    when:
    PrimeNumberCalculator calculator = new PrimeNumberCalculator([5, 10, 15, 20, 45])

    then:
    calculator.retrievePrimeFactors(5) == [1, 5]
    calculator.retrievePrimeFactors(10) == [2, 5]
    calculator.retrievePrimeFactors(15) == [3, 5]
    calculator.retrievePrimeFactors(20) == [2, 2, 5]
    calculator.retrievePrimeFactors(45) == [3, 3, 5]
  }

  @Unroll
  def 'returns empty prime list when value is single #input'() {
    when:
    PrimeNumberCalculator calculator = new PrimeNumberCalculator([input])

    then:
    calculator.calculatePrime(input) == []

    where:
    input << [1, 0, -1]
  }

  @Unroll
  def 'calculates GCF when #inputs results in value #gcf'() {
    when:
    PrimeNumberCalculator calculator = new PrimeNumberCalculator(inputs)

    then:
    calculator.calculateGreatestCommonFactor() == gcf

    where:
    inputs          | gcf
    [2, 4]          | 2
    [12, 20]        | 4
    [3, 5]          | 1
    [5, 10, 15, 20] | 5
    [5, 10, 11]     | 1
  }

  @Unroll
  def 'calculates LCM when #inputs results in value #lcm'() {
    when:
    PrimeNumberCalculator calculator = new PrimeNumberCalculator(inputs)

    then:
    calculator.calculateLeastCommonMultiple() == lcm
    calculator.calculateLeastCommonMultiple() == lcm //can be called twice

    where:
    inputs          | lcm
    [2, 4]          | 4
    [12, 20]        | 60
    [3, 5]          | 15
    [5, 10, 15, 20] | 60
    [5, 10, 11]     | 110
    [5, 10, 11, 15] | 330
  }
}
