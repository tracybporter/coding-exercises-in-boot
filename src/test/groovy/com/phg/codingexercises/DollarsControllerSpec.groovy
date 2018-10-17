package com.phg.codingexercises

import org.springframework.test.web.servlet.MockMvc
import spock.lang.Specification
import spock.lang.Unroll

import static org.springframework.http.HttpStatus.OK
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup


class DollarsControllerSpec extends Specification {
  DollarsController controller
  DollarsService mockService
  MockMvc mockMvc

  def setup() {
    controller = new DollarsController()
    mockService = Mock(DollarsService)
    controller.service = mockService
    mockMvc = standaloneSetup(controller).build()
  }

  @Unroll
  def 'money controller delegates to the service for #input'() {
    when:
    def response = mockMvc.perform(get("/api/v1/coins/$input")).andReturn().response

    then:
    1 * mockService.calculateCoinCounts(input) >> new PageResult()
    response.status == OK.value
    response.getContentAsString() == '{"results":[]}'

    where:
    input << ['a', "5", '3333.2']
  }
}