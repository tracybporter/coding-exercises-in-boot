package com.phg.codingexercises

import org.springframework.test.web.servlet.MockMvc
import spock.lang.Specification

import static org.springframework.http.HttpStatus.OK
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup

class JavaTestControllerSpec extends Specification {
  JavaTestController controller
  MockMvc mockMvc

  def setup() {
    controller = new JavaTestController()
    mockMvc = standaloneSetup(controller).build()
  }

  void 'returns a message if everything compiles and runs as expected for a java class'() {
    when:
    def response = mockMvc.perform(get("/javaController/health")).andReturn().response

    then:
    response.status == OK.value
    response.getContentAsString() == '{"message":"Java is being compiled too."}'
  }
}
