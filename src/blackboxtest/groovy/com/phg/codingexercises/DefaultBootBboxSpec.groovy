package com.phg.codingexercises

import groovyx.net.http.HttpResponseDecorator
import groovyx.net.http.RESTClient
import spock.lang.Specification

class DefaultBootBboxSpec extends Specification {
  def 'actuator endpoint returns standard boot links'() {
    given:
    RESTClient restClient = new RESTClient('http://localhost:8080')

    when:
    HttpResponseDecorator response = restClient.get(
            path: '/actuator',
            headers: ['Accept': 'application/json']
    )

    then:
    response.status == 200
    response.headers.'Content-Type' == 'application/json;charset=UTF-8'
    response.data.size() == 1
    Map actuatorLinks = response.data.'_links'
    actuatorLinks.size() == 3
    actuatorLinks.self
    actuatorLinks.health
    actuatorLinks.info
    actuatorLinks.each { k, v ->
      assert v.href instanceof String
      assert v.templated instanceof Boolean
    }
  }
}