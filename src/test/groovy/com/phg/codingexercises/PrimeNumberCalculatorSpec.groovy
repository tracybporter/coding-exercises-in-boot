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
    inputs         | gcf
    [2, 4]         | 2
    [12, 20]       | 4
    [3, 5]         | 1
    [5, 10, 15, 2] | 5
    [5, 10, 11]    | 1
  }
}
