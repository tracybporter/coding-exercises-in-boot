package com.phg.codingexercises;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/api/v1/arrays")
public class ArraysController {

  @Autowired
  private ArraysService service;

  @PostMapping(consumes = "application/json", produces = "application/json")
  @ResponseBody
  public PageResult calculateMinAbsSum(@RequestBody ArraysInput inputs) {
    return service.calculateAbsoluteMinimumOfSums(inputs);
  }

  @PostMapping(path = "/parseit", consumes = "application/json", produces = "application/json")
  @ResponseBody
  public PageResult deriveSublistsWithMinimizedSums(@RequestBody ArraysInput inputs) {
    return service.findSubsetsWithMinimizedSum(inputs);
  }

}
