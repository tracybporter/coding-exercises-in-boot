package com.phg.codingexercises

import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import spock.lang.Specification

import static org.springframework.http.HttpStatus.OK
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup

class ArraysControllerSpec extends Specification {
  ArraysController controller
  ArraysService mockService
  MockMvc mockMvc

  def setup() {
    controller = new ArraysController()
    mockService = Mock(ArraysService)
    controller.service = mockService
    mockMvc = standaloneSetup(controller).build()
  }

  void 'serializes request body, delegates to service and returns PageResult'() {
    when:
    def response = mockMvc.perform(post("/api/v1/arrays")
            .content('{"inputs":[3,9,-100]}')
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON))
            .andReturn().response

    then:
    1 * mockService.calculateAbsoluteMinimumOfSums(_) >> { args ->
      assert args.size() == 1
      ArraysInput serializedInputs = args[0]
      assert serializedInputs.inputs.size() == 3
      assert serializedInputs.inputs[0] == 3
      assert serializedInputs.inputs[1] == 9
      assert serializedInputs.inputs[2] == -100

      return new PageResult()
    }

    response.status == OK.value
    response.getContentAsString() == '{"results":[]}'
  }
}
