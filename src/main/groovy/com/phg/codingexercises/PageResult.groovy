package com.phg.codingexercises

import groovy.transform.EqualsAndHashCode

@EqualsAndHashCode()
class PageResult {
  List results

  public PageResult(List results = []) {
    this.results = results
  }
}

@EqualsAndHashCode()
class FactorResult {
  int input
  List prime
}
