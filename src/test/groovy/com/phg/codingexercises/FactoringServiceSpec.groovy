package com.phg.codingexercises

import spock.lang.Specification
import spock.lang.Unroll

@Unroll
class FactoringServiceSpec extends Specification {
  FactoringService service
  PrimeNumberCalculator mockPrimeNumberCalculator

  void setup() {
    service = new FactoringService()
    mockPrimeNumberCalculator = Mock()
  }

  def 'returns PageResults with all prime factors for single input'() {
    given:
    GroovyMock(global: true, PrimeNumberCalculator)

    when:
    PageResult actual = service.calculatePrime(2)

    then:
    1 * new PrimeNumberCalculator([2]) >> mockPrimeNumberCalculator
    1 * mockPrimeNumberCalculator.retrievePrimeFactors(2) >> [9]
    0 * mockPrimeNumberCalculator.calculateGreatestCommonFactor()
    actual.results.size() == 1
    actual.results[0].input == 2
    actual.results[0].prime == [9]
  }

  def 'returns PageResults for "21,39"'() {
    given:
    GroovyMock(global: true, PrimeNumberCalculator)

    when:
    PageResult actual = service.calculatePrime('21, 39')

    then:
    1 * new PrimeNumberCalculator([21, 39]) >> mockPrimeNumberCalculator
    1 * mockPrimeNumberCalculator.retrievePrimeFactors(21) >> [91, 0]
    1 * mockPrimeNumberCalculator.retrievePrimeFactors(39) >> [91, 11]
    1 * mockPrimeNumberCalculator.calculateGreatestCommonFactor() >> 6
    actual.results.size() == 3
    actual.results[0].input == 21
    actual.results[0].prime == [91, 0]

    actual.results[1].input == 39
    actual.results[1].prime == [91, 11]

    actual.results[2].greatestCommonFactor == 6
  }

  def 'ignores empty single value when for ",,21,"'() {
    given:
    GroovyMock(global: true, PrimeNumberCalculator)

    when:
    PageResult actual = service.calculatePrime(',,21, ')

    then:
    1 * new PrimeNumberCalculator([21]) >> mockPrimeNumberCalculator
    1 * mockPrimeNumberCalculator.retrievePrimeFactors(21) >> [1]
    0 * mockPrimeNumberCalculator.calculateGreatestCommonFactor()
    actual.results.size() == 1
  }

  def 'ignores empty single value when for "21,,,39, 1"'() {
    given:
    GroovyMock(global: true, PrimeNumberCalculator)

    when:
    PageResult actual = service.calculatePrime('21,,,39, 1')

    then:
    1 * new PrimeNumberCalculator([21, 39, 1]) >> mockPrimeNumberCalculator
    1 * mockPrimeNumberCalculator.retrievePrimeFactors(21) >> [1]
    1 * mockPrimeNumberCalculator.retrievePrimeFactors(39) >> [0]
    1 * mockPrimeNumberCalculator.retrievePrimeFactors(1) >> []
    1 * mockPrimeNumberCalculator.calculateGreatestCommonFactor() >> 601
    actual.results.size() == 4
    actual.results[0].input == 21
    actual.results[1].input == 39
    actual.results[2].input == 1
    actual.results[3].greatestCommonFactor == 601
  }
}
