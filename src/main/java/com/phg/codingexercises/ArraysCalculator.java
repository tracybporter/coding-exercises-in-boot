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
    List<List<Integer>> sublists = new ArrayList();
    int remainingCount = requestedSublistCount;
    sublists.addAll(bisectList(elements));
    remainingCount = requestedSublistCount - sublists.size();

    while (remainingCount > 0) {
      int maxSublistSum = -1;
      int locationOfLargestList = -1;
      for (int index = 0; index < sublists.size(); index++) {
        int sum = sumItems(sublists.get(index));
        if (maxSublistSum < sum && sublists.get(index).size() > 1) {
          maxSublistSum = sum;
          locationOfLargestList = index;
        }
      }
      if (locationOfLargestList == -1) {
        throw new RuntimeException("this should not happen");
      }
      List<Integer> largestListToBisect = sublists.get(locationOfLargestList);
      sublists.remove(locationOfLargestList);
      sublists.addAll(locationOfLargestList, bisectList(largestListToBisect));
      remainingCount = requestedSublistCount - sublists.size();

    }
    sublistInfo.getSublists().addAll(sublists);
    sublistInfo.setMinimizedSum(calculateMax(sublists));

    return sublistInfo;
  }

  private List<List<Integer>> bisectList(List<Integer> inputs) {
    List<List<Integer>> bisectedLists = new ArrayList<>();
    int maxInputsValue = Collections.max(inputs);
    int indexOfMax = inputs.indexOf(maxInputsValue);

    List<Integer> firstPart;
    List<Integer> lastPart;

    int inputLength = inputs.size();
    boolean maxIsOnTheBoundary = false;

    if (indexOfMax == 0 || indexOfMax == (inputLength - 1)) {
      maxIsOnTheBoundary = true;
    }

    if (!maxIsOnTheBoundary) {
      firstPart = new ArrayList<>(inputs.subList(0, indexOfMax));
      lastPart = new ArrayList<>(inputs.subList(indexOfMax + 1, inputLength));

      int lastPartSum = sumItems(lastPart);
      int firstPartSum = sumItems(firstPart);
      if (firstPartSum < lastPartSum) {
        firstPart.add(maxInputsValue);
      } else {
        lastPart.add(0, maxInputsValue);
      }
      bisectedLists.add(firstPart);
      bisectedLists.add(lastPart);

    } else {
      int parseIndex = indexOfMax;
      if (inputLength > 3 && indexOfMax == 0) {
        for (int index = 2; index < inputLength - 1; index++) {
          if (sumItems(inputs.subList(0, index)) <= sumItems(inputs.subList(index, inputLength))) {
            parseIndex = index;
          }
        }
      }

      firstPart = new ArrayList<>(inputs.subList(0, parseIndex == 0 ? 1 : parseIndex));
      lastPart = new ArrayList<>(inputs.subList(parseIndex == 0 ? parseIndex + 1 : parseIndex, inputLength));

      bisectedLists.add(firstPart);
      bisectedLists.add(lastPart);

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