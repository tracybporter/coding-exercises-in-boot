package com.phg.codingexercises

import groovyx.net.http.HttpResponseDecorator
import groovyx.net.http.RESTClient
import spock.lang.Specification


class DivisorsBboxSpec extends Specification {

  def 'basic factor endpoint returns prime factors for single integer'() {
    given:
    RESTClient restClient = new RESTClient('http://localhost:8080')

    when:
    HttpResponseDecorator response = restClient.get(
            path: '/api/v1/factors/12',
            headers: ['Accept': 'application/json']
    )

    then:
    response.status == 200
    response.headers.'Content-Type' == 'application/json;charset=UTF-8'
    response.data.size() == 1
    List results = response.data.'results'
    results.size() == 1
    Map factorsFor12 = results.find { it.input == 12 }
    factorsFor12.prime == [2, 2, 3]
  }

  def 'factor endpoint returns prime factors for a list'() {
    given:
    RESTClient restClient = new RESTClient('http://localhost:8080')

    when:
    HttpResponseDecorator response = restClient.get(
            path: '/api/v1/factors/12,20',
            headers: ['Accept': 'application/json']
    )

    then:
    response.status == 200
    response.headers.'Content-Type' == 'application/json;charset=UTF-8'
    response.data.size() == 1
    List results = response.data.'results'
    results.size() == 2
    Map factorsFor12 = results.find { it.input == 12 }
    factorsFor12.prime == [2, 2, 3]

    Map factorsFor20 = results.find { it.input == 20 }
    factorsFor20.prime == [2, 2, 5]
  }
}