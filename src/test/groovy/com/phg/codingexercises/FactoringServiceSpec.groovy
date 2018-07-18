package com.phg.codingexercises

import spock.lang.Specification
import spock.lang.Unroll

@Unroll
class FactoringServiceSpec extends Specification {
  FactoringService service = new FactoringService()

  def 'returns PageResults with all prime factors for single #input'() {
    given:
    PageResult expected = new PageResult(results: [new FactorResult(input: input, prime: expectedPrimeFactors)])

    when:
    PageResult actual = service.calculatePrime(input)

    then:
    actual.results.size() == 1
    actual.results[0].input == input
    actual.results[0].prime == expected.results[0].prime

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
    PageResult actual = service.calculatePrime(input)

    then:
    actual.results.size() == 1
    actual.results[0].input == input
    actual.results[0].prime == []

    where:
    input << [1, 0, -1]
  }

  def 'returns PageResults with list of prime factors for "21,39"'() {
    when:
    PageResult actual = service.calculatePrime('21, 39')

    then:
    actual.results.size() >= 2
    actual.results[0].input == 21
    actual.results[0].prime == [3, 7]

    actual.results[1].input == 39
    actual.results[1].prime == [3, 13]
  }

  def 'ignores empty single value when for ",,21,"'() {
    when:
    PageResult actual = service.calculatePrime('21, ')

    then:
    actual.results.input.size() == 1
    actual.results[0].input == 21
    actual.results[0].prime == [3, 7]
  }

  def 'ignores empty single value when for "21,,,39, 1"'() {
    when:
    PageResult actual = service.calculatePrime('21,,,39, 1')

    then:
    actual.results.size() >= 3
    actual.results[0].input == 21
    actual.results[0].prime == [3, 7]

    actual.results[1].input == 39
    actual.results[1].prime == [3, 13]

    actual.results[2].input == 1
    actual.results[2].prime == []
  }

  @Unroll
  def 'calculates GCF when #inputs results in value #gcf'() {
    when:
    PageResult actual = service.calculatePrime(inputs)

    then:
    actual.results.size() == 3
    actual.results[2].greatestCommonFactor == gcf

    where:
    inputs  | gcf
    '2,4'   | 2
    '12,20' | 4
    '3,5'   | 1
  }
}
