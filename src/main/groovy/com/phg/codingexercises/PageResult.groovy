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
  String type = 'individual'
  int input
  List prime
}

class GcfLcmResult {
  String type = 'aggregate'
  int greatestCommonFactor
  int leastCommonMultiple
}

class CoinCountResult {
  String type = 'coinCounts'
  BigDecimal input
  int quarters
  int dimes
  int nickels
  int pennies
}