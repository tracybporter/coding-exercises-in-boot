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

    List<Integer> elements = new ArrayList<>(this.elements);
    int requestedSublistCount = this.requestedSublistCount;
    List<List<Integer>> sublists2 = bisectList(elements, requestedSublistCount);

    sublistInfo.getSublists().addAll(sublists2);
    sublistInfo.setMinimizedSum(calculateMax(sublistInfo.getSublists()));

    return sublistInfo;
  }

  private List<List<Integer>> bisectList(List<Integer> inputs, int remainingCount) {
    List<List<Integer>> bisectedLists = new ArrayList<>();
    int maxInputsValue = Collections.max(inputs);
    int indexOfMax = inputs.indexOf(maxInputsValue);
    int sumOfFirstPart;
    int sumOfLastPart;
    List<Integer> firstPart;

    List<Integer> lastPart;

    int bisectedSegments = ((indexOfMax + 1) == inputs.size()) ? 2 : 3;
    int partitionsToCreate = remainingCount - bisectedSegments;

    firstPart = new ArrayList<>(inputs.subList(0, indexOfMax));
    lastPart = new ArrayList<>(inputs.subList(indexOfMax + 1, inputs.size()));

    sumOfFirstPart = sumItems(firstPart);
    sumOfLastPart = sumItems(lastPart);

    if (bisectedSegments == 3) {
      int middlePart = maxInputsValue;

      if (partitionsToCreate == -1) {//Group max with adjacent
        if (sumOfFirstPart > sumOfLastPart) {
          lastPart.add(0, maxInputsValue);
        } else {
          firstPart.add(maxInputsValue);
        }
        bisectedLists.add(firstPart);
        bisectedLists.add(lastPart);
      }
      if (partitionsToCreate == 0) {
        bisectedLists.add(firstPart);
        bisectedLists.add(Arrays.asList(middlePart));
        bisectedLists.add(lastPart);
      }
    } else {

      if (partitionsToCreate == 0) {
        bisectedLists.add(firstPart);
        bisectedLists.add(Arrays.asList(maxInputsValue));
      } else if (partitionsToCreate == 1) {
        bisectedLists.addAll(bisectList(firstPart, 2));
        bisectedLists.add(Arrays.asList(maxInputsValue));
      }
    }
    return bisectedLists;
  }

  private int sumItems(List<Integer> items) {
    return items.stream().reduce(0, (x, y) -> x + y);
  }

  private int calculateMax(List<List<Integer>> sublists) {
    int maxValue = sumItems(sublists.get(0));
    for (List<Integer> sublist : sublists) {
      int sum = sumItems(sublist);
      if (sum > maxValue) {
        maxValue = sum;
      }
    }
    return maxValue;
  }
}