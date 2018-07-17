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
    actual.results[0].prime == expected.results[0].prime

    where:
    input | expectedPrimeFactors
    4     | [2, 2]
    9     | [3, 3]
    16    | [2, 2, 2, 2]
    39    | [3, 13]
    221   | [13, 17]
    2     | [1, 2]
    17    | [1, 17]
  }
}
