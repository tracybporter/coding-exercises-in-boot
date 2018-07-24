package com.phg.codingexercises;


import java.util.List;

public class ArraysInput {
  private List<Integer> inputs;
  private int numberOfSublists;

  public ArraysInput() {
  }

  public ArraysInput(Integer numberOfSublists, List<Integer> integers) {
    this.numberOfSublists = numberOfSublists == null ? 0 : numberOfSublists;
    inputs = integers;
  }

  public ArraysInput(List<Integer> integers) {
    this(null, integers);
  }

  public List<Integer> getInputs() {
    return inputs;
  }

  public void setInputs(List<Integer> inputs) {
    this.inputs = inputs;
  }

  public int getNumberOfSublists() {
    return numberOfSublists;
  }

  public void setNumberOfSublists(int numberOfSublists) {
    this.numberOfSublists = numberOfSublists;
  }

}
