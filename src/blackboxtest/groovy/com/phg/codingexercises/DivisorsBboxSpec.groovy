package com.phg.codingexercises

import groovyx.net.http.HttpResponseDecorator
import groovyx.net.http.RESTClient
import spock.lang.Specification


class DivisorsBboxSpec extends Specification {

  def 'basic divisor endpoint returns prime factors'() {
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
//    factorsFor12.all == [1, 2, 3, 4, 6, 12] as int[]
  }
}