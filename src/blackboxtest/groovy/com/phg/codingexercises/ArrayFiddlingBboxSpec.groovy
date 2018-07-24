package com.phg.codingexercises

import groovyx.net.http.HttpResponseDecorator
import spock.lang.Specification

class ArrayFiddlingBboxSpec extends Specification {
  SutRestClient systemUnderTest = new SutRestClient()

  void 'calculates the sum of elements in an array and returns the minimum absolute value'() {
    given:
    Map requestBody = [inputs: [1, 4, -3]]

    when:
    HttpResponseDecorator response = systemUnderTest.post(
            path: '/api/v1/arrays',
            requestContentType: 'application/json',
            body: requestBody,
            headers: ['Accept': 'application/json']
    )

    then:
    response.status == 200
    response.headers.'Content-Type' == 'application/json;charset=UTF-8'
    response.data.size() == 1
    List results = response.data.'results'
    results.size() == 1
    results[0].type == 'aggregate'
    results[0].calculation == 'minimuma bsolute sum'
    results[0].originalList == [1, 4, -3]
    results[0].minimizedSum == 1
//    results[0].elements == [4, -3] //If I run out of other stuff to do.
  }

  void 'parses an ordered list of length N into M sublists such that the largest sum is minimized'() {
    given:
    //the original problem also provided the max value within the list but don't think it is all that important
    Map requestBody = [numberOfSublists: 3, inputs: [2, 1, 5, 1, 2, 2, 2]]

    when:
    HttpResponseDecorator response = systemUnderTest.post(
            path: '/api/v1/arrays/parseit',
            requestContentType: 'application/json',
            body: requestBody,
            headers: ['Accept': 'application/json']
    )

    then:
    response.status == 200
    response.headers.'Content-Type' == 'application/json;charset=UTF-8'
    response.data.size() == 1
    List results = response.data.'results'
    results.size() == 1
    results[0].type == 'aggregate'
    results[0].calculation == 'subsets minimize sum for requested subset count'
    results[0].originalList == [2, 1, 5, 1, 2, 2, 2]
    results[0].requestedSublistCount == 3
    results[0].minimizedSum == 6
    results[0].sublists == [[2, 1], [5, 1], 2, 2, 2]
  }
}