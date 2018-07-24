package com.phg.codingexercises

import spock.lang.Specification

class ArraysServiceSpec extends Specification {

  void 'request calculation and constructs the PageResult object'() {
    given:
    ArraysCalculator mockArraysCalculator = Mock()
    ArraysInput mockArraysInput = Mock()
    ArraysService service = new ArraysService() {
      public ArraysCalculator constructCalculator(ArraysInput inputs) {
        assert inputs == mockArraysInput
        mockArraysCalculator
      }
    }

    when:
    PageResult actual = service.calculateAbsoluteMinimumOfSums(mockArraysInput)

    then:
    1 * mockArraysInput.inputs >> [0, 5]
    1 * mockArraysCalculator.retrieveMinimumAbsoluteSum() >> 12

    actual.results.size() == 1
    actual.results[0].type == 'aggregate'
    actual.results[0].calculation == 'minimumabsolutesum'
    actual.results[0].originalList == [0, 5]
    actual.results[0].value == 12
  }
}
