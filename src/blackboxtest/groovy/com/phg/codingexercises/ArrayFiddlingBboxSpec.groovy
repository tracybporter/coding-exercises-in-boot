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
    results[0].calculation == 'minimumabsolutesum'
    results[0].value == 1
    results[0].elements == [4, -3]
  }
}