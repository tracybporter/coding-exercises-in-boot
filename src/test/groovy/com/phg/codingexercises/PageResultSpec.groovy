package com.phg.codingexercises

import com.fasterxml.jackson.databind.ObjectMapper
import spock.lang.Specification

class PageResultSpec extends Specification {

  def 'marshalls with type of individual when FactorResult in results'() {
    when:
    String json = new ObjectMapper().writeValueAsString(new FactorResult())

    then:
    json == '{"type":"individual","input":0,"prime":null}'
  }

  def 'marshalls with type of aggregate when FactorResult in results'() {
    when:
    String json = new ObjectMapper().writeValueAsString(new GcfLcmResult())

    then:
    json == '{"type":"aggregate","greatestCommonFactor":0,"leastCommonMultiple":0}'
  }
}
