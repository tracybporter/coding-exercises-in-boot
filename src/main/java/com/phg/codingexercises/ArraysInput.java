package com.phg.codingexercises;


import java.util.List;

public class ArraysInput {
  private List<Integer> inputs;
  private int numberOfSublists;

  public ArraysInput() {
  }

  public ArraysInput(List<Integer> integers) {
    inputs = integers;
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
