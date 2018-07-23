package com.phg.codingexercises

import spock.lang.Specification

class ArraysCalculatorSpec extends Specification {

  void 'iterates over array and calculates sums'() {
    given:
    ArraysCalculator calculator = new ArraysCalculator(new ArraysInput(inputs))

    when:
    int minAbsSum = calculator.retrieveMinimumAbsoluteSum()

    then:
    minAbsSum == expectedResult

    where:
    inputs        | expectedResult
    [1, 3]        | 2
    [-1, 1, 3]    | 0
    [-10, 2, 3]   | 4
    [0]           | 0
    [5]           | 10
    [-12, 8, 100] | 4
  }
}
