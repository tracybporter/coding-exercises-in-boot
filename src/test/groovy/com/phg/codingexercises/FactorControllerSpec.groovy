package com.phg.codingexercises

import org.springframework.test.web.servlet.MockMvc
import spock.lang.Specification
import spock.lang.Unroll

import static org.springframework.http.HttpStatus.OK
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup


class FactorControllerSpec extends Specification {
  FactorController factorController
  FactoringService mockFactoringService
  MockMvc mockMvc

  def setup() {
    factorController = new FactorController()
    mockFactoringService = Mock(FactoringService)
    factorController.service = mockFactoringService
    mockMvc = standaloneSetup(factorController).build()
  }

  @Unroll
  def 'factor controller delegates to the service for #input'() {
    when:
    def response = mockMvc.perform(get("/api/v1/factors/$input")).andReturn().response

    then:
    1 * mockFactoringService.calculatePrime(input) >> new PageResult()
    response.status == OK.value
    response.getContentAsString() == '{"results":[]}'

    where:
    input << [16, '16,17']
  }
}