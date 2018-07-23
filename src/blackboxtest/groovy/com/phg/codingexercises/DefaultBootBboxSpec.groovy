package com.phg.codingexercises

import groovyx.net.http.HttpResponseDecorator
import spock.lang.Specification

class DefaultBootBboxSpec extends Specification {
  SutRestClient systemUnderTest = new SutRestClient()

  def 'actuator endpoint returns standard boot links'() {
    when:
    HttpResponseDecorator response = systemUnderTest.get(
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

  def 'confirm java controller works'() {
    when:
    HttpResponseDecorator response = systemUnderTest.get(
            path: '/javaController/health',
            headers: ['Accept': 'application/json']
    )

    then:
    response.status == 200
    response.headers.'Content-Type' == 'application/json;charset=UTF-8'
    response.data.size() == 1
    response.data.message == 'Java is being compiled too.'
  }
}