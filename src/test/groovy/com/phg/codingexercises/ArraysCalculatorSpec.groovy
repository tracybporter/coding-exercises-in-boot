package com.phg.codingexercises

import spock.lang.Specification

class ArraysCalculatorSpec extends Specification {
  void 'construct sets the elements'(){
    expect:
    new ArraysCalculator(new ArraysInput(inputs: [5])).elements == [5]
  }
 void 'construct sets the requested sublist count'(){
    expect:
    new ArraysCalculator(new ArraysInput(numberOfSublists:9)).requestedSublistCount == 9
  }

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

  void 'return sublists when requested count matches the number of elements'() {
    given:
    ArraysCalculator calculator = new ArraysCalculator(new ArraysInput(3, [1, 2, 3]))

    when:
    SublistInformation result = calculator.gatherSublistsWithMinimizedSum()

    then:
    result.minimizedSum == 3
    result.sublists[[1], [2], [3]]
  }
}
