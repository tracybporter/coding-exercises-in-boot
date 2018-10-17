package com.phg.codingexercises

import groovyx.net.http.HttpResponseDecorator
import spock.lang.Specification


class MoneyChangeBboxSpec extends Specification {
  SutRestClient systemUnderTest = new SutRestClient()

  def 'change calculator endpoint returns count of USD coins'() {
    when:
    HttpResponseDecorator response = systemUnderTest.get(
            path: '/api/v1/coins/.68',
            headers: ['Accept': 'application/json']
    )

    then:
    response.status == 200
    response.headers.'Content-Type' == 'application/json;charset=UTF-8'
    response.data.size() == 1
    List results = response.data.'results'
    results.size() == 1
    Map changeInfo = results.find { it.type == 'coinCounts' && it.input == 0.68 }
    changeInfo
    changeInfo.quarters == 2
    changeInfo.dimes == 1
    changeInfo.nickels == 1
    changeInfo.pennies == 3
  }
}