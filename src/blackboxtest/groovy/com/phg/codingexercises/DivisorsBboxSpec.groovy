package com.phg.codingexercises

import groovyx.net.http.HttpResponseDecorator
import spock.lang.Specification

class DivisorsBboxSpec extends Specification {
  SutRestClient systemUnderTest = new SutRestClient()

  def 'basic factor endpoint returns prime factors for single integer'() {
    when:
    HttpResponseDecorator response = systemUnderTest.get(
            path: '/api/v1/factors/12',
            headers: ['Accept': 'application/json']
    )

    then:
    response.status == 200
    response.headers.'Content-Type' == 'application/json;charset=UTF-8'
    response.data.size() == 1
    List results = response.data.'results'
    results.size() == 1
    Map factorsFor12 = results.find { it.type == 'individual' && it.input == 12 }
    factorsFor12
    factorsFor12.prime == [2, 2, 3]

    results.find { it.greatestCommonFactor } == null
    results.find { it.leastCommonMultiple } == null
  }

  def 'factor endpoint returns prime factors for a list and greatest common factor and least common multiple of all values'() {
    when:
    HttpResponseDecorator response = systemUnderTest.get(
            path: '/api/v1/factors/12,20',
            headers: ['Accept': 'application/json']
    )

    then:
    response.status == 200
    response.headers.'Content-Type' == 'application/json;charset=UTF-8'

    response.data.size() == 1
    List results = response.data.'results'

    results.size() == 3
    Map factorsFor12 = results.find { it.type == 'individual' && it.input == 12 }
    factorsFor12
    factorsFor12.prime == [2, 2, 3]

    Map factorsFor20 = results.find { it.type == 'individual' && it.input == 20 }
    factorsFor20
    factorsFor20.prime == [2, 2, 5]

    results.find { it.type == 'aggregate' && it.greatestCommonFactor == 2 * 2 }
    results.find { it.type == 'aggregate' && it.leastCommonMultiple == 4 * 3 * 5 }
  }
}