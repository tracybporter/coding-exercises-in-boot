package com.phg.codingexercises

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.web.server.LocalServerPort
import spock.lang.Shared
import spock.lang.Specification

@SpringBootTest(classes = CodingExercisesApplication, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CodingExercisesApplicationSpec extends Specification {
  @Autowired
  TestRestTemplate testRestTemplate

  @LocalServerPort
  private int testPort

  @Shared
  String host

  void setup() {
    host = "http://localhost:$testPort"
  }

  void 'spring context loads'() {
    expect:
    testRestTemplate.getForEntity("$host/actuator/info", String.class).statusCode.value() == 200
  }
}