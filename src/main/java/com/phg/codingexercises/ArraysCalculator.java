package com.phg.codingexercises;

import java.util.Arrays;
import java.util.List;

public class ArraysCalculator {
  private List<Integer> elements;
  private Integer requestedSublistCount;

  public ArraysCalculator(ArraysInput inputs) {
    elements = inputs.getInputs();
    requestedSublistCount = inputs.getNumberOfSublists();
  }

  public Integer retrieveMinimumAbsoluteSum() {
    Integer minAbsoluteSum = Math.abs(elements.get(0) + elements.get(0));

    //Sad that forEach wants minAbsoluteSum to be final
    for (Integer outer : elements) {
      for (Integer inner : elements) {

        Integer absoluteSum = Math.abs(inner + outer);
        if (absoluteSum < minAbsoluteSum) {
          minAbsoluteSum = absoluteSum;
        }
      }

    }
    return minAbsoluteSum;
  }

  public SublistInformation gatherSublistsWithMinimizedSum() {
    SublistInformation sublistInfo = new SublistInformation();

    if (requestedSublistCount == 1) {
      sublistInfo.setSublists(Arrays.asList(elements));
      sublistInfo.setMinimizedSum(elements.stream().reduce(0, (x, y) -> x + y));
      return sublistInfo;
    }

    if (elements.size() == requestedSublistCount) {
      sublistInfo.setMinimizedSum(-1);
      elements.forEach(input -> {
        sublistInfo.getSublists().add(Arrays.asList(input));
        if (sublistInfo.getMinimizedSum() < input) {
          sublistInfo.setMinimizedSum(input);
        }
      });
      return sublistInfo;
    }



    return sublistInfo;
  }
}