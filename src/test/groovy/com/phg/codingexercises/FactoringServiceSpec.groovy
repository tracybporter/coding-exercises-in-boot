package com.phg.codingexercises

import spock.lang.Specification
import spock.lang.Unroll

@Unroll
class FactoringServiceSpec extends Specification {
  def 'returns PageResults with all prime factors for #input'() {
    given:
    FactoringService service = new FactoringService()
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
  def 'returns empty prime list when value is #input'() {
    given:
    FactoringService service = new FactoringService()

    when:
    PageResult actual = service.calculatePrime(input)

    then:
    actual.results.size() == 1
    actual.results[0].input == input
    actual.results[0].prime == []

    where:
    input << [1, 0, -1]
  }
}
