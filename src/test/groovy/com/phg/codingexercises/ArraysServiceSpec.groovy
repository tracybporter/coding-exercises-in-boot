package com.phg.codingexercises

import spock.lang.Specification

class ArraysServiceSpec extends Specification {
  ArraysCalculator mockArraysCalculator
  ArraysInput mockArraysInput
  ArraysService testableService

  def setup() {
    mockArraysCalculator = Mock()
    mockArraysInput = Mock()
    testableService = new ArraysService() {
      public ArraysCalculator constructCalculator(ArraysInput inputs) {
        assert inputs == mockArraysInput
        mockArraysCalculator
      }
    }
  }

  void 'request calculation and constructs the PageResult object'() {
    when:
    PageResult actual = testableService.calculateAbsoluteMinimumOfSums(mockArraysInput)

    then:
    1 * mockArraysInput.inputs >> [0, 5]
    1 * mockArraysCalculator.retrieveMinimumAbsoluteSum() >> 12

    actual.results.size() == 1
    actual.results[0].type == 'aggregate'
    actual.results[0].calculation == 'minimum absolute sum'
    actual.results[0].originalList == [0, 5]
    actual.results[0].minimizedSum == 12
  }

  void 'request sublists and constructs the PageResult object'() {
    given:
    SublistInformation mockSublistInformation = Mock(SublistInformation)

    when:
    PageResult actual = testableService.findSubsetsWithMinimizedSum(mockArraysInput)

    then:
    1 * mockArraysInput.inputs >> [9, 1]
    1 * mockArraysInput.numberOfSublists >> 12
    1 * mockSublistInformation.minimizedSum >> 4
    1 * mockSublistInformation.sublists >> [[], [2, 1], [9999999999]]
    1 * mockArraysCalculator.gatherSublistsWithMinimizedSum() >> mockSublistInformation

    actual.results.size() == 1
    Map actualInfo = actual.results[0]
    actualInfo.type == 'aggregate'
    actualInfo.calculation == 'subsets minimize sum for requested subset count'
    actualInfo.originalList ==  [9, 1]
    actualInfo.requestedSublistCount == 12
    actualInfo.minimizedSum == 4
    actualInfo.sublists == [[], [2, 1], [9999999999]]
  }
}
