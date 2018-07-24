package com.phg.codingexercises;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class ArraysService {
  public PageResult calculateAbsoluteMinimumOfSums(ArraysInput inputs) {

    ArraysCalculator calculator = constructCalculator(inputs);
    List results = new ArrayList();
    results.add(
            new HashMap<String, Object>() {
              {
                put("type", "aggregate");
                put("calculation", "minimum absolute sum");
                put("originalList", inputs.getInputs());
                put("minimizedSum", calculator.retrieveMinimumAbsoluteSum());
              }
            });

    PageResult result = new PageResult();
    result.setResults(results);
    return result;
  }

  public PageResult findSubsetsWithMinimizedSum(ArraysInput inputs) {
    ArraysCalculator calculator = constructCalculator(inputs);
    SublistInformation sublistInfo = calculator.gatherSublistsWithMinimizedSum();
    List results = new ArrayList();
    results.add(
            new HashMap<String, Object>() {
              {
                put("type", "aggregate");
                put("calculation", "subsets minimize sum for requested subset count");
                put("originalList", inputs.getInputs());
                put("requestedSublistCount", inputs.getNumberOfSublists());
                put("minimizedSum", sublistInfo.getMinimizedSum());
                put("sublists", sublistInfo.getSublists());
              }
            });

    PageResult result = new PageResult();
    result.setResults(results);
    return result;

  }

  public ArraysCalculator constructCalculator(ArraysInput inputs) {
    return new ArraysCalculator(inputs);
  }
}
