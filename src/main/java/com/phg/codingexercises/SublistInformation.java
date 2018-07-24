package com.phg.codingexercises;

import java.util.ArrayList;
import java.util.List;

public class SublistInformation {
  private int minimizedSum;
  private List<List<Integer>> sublists = new ArrayList<>();

  public int getMinimizedSum() {
    return minimizedSum;
  }

  public void setMinimizedSum(int minimizedSum) {
    this.minimizedSum = minimizedSum;
  }

  public List<List<Integer>> getSublists() {
    return sublists;
  }

  public void setSublists(List<List<Integer>> sublists) {
    this.sublists = sublists;
  }
}
