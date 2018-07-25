package com.phg.codingexercises;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

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
      sublistInfo.setMinimizedSum(sumItems(elements));
      return sublistInfo;
    }

    if (elements.size() <= requestedSublistCount) {
      sublistInfo.setMinimizedSum(-1);
      elements.forEach(input -> {
        sublistInfo.getSublists().add(Arrays.asList(input));
        if (sublistInfo.getMinimizedSum() < input) {
          sublistInfo.setMinimizedSum(input);
        }
      });
      IntStream.range(0, (requestedSublistCount - elements.size())).forEach(i -> sublistInfo.getSublists().add(new ArrayList()));
      return sublistInfo;
    }

    int maxInputsValue = Collections.max(elements);
    int indexOfMax = elements.indexOf(maxInputsValue);
    List<Integer> firstPart = new ArrayList<>(elements.subList(0, indexOfMax));
    List<Integer> lastPart = new ArrayList<>(elements.subList(indexOfMax + 1, elements.size()));

    int sumOfFirstPart = sumItems(firstPart);
    int sumOfLastPart = sumItems(lastPart);

    if (requestedSublistCount == 2) {
      if (sumOfFirstPart > sumOfLastPart) {
        lastPart.add(0, maxInputsValue);
      } else {
        firstPart.add(maxInputsValue);
      }
      sublistInfo.getSublists().add(firstPart);
      sublistInfo.getSublists().add(lastPart);
      sublistInfo.setMinimizedSum(Math.max(sumItems(firstPart), sumItems(lastPart)));
    }

    return sublistInfo;
  }

  private int sumItems(List<Integer> items) {
    return items.stream().reduce(0, (x, y) -> x + y);
  }
}