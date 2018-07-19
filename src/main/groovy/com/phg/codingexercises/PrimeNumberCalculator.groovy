package com.phg.codingexercises

class PrimeNumberCalculator {
  List inputs

  PrimeNumberCalculator(List inputs) {
    this.inputs = inputs
  }

  List retrievePrimeFactors(int input) {
    calculatePrime(input)
  }

  Integer calculateGreatestCommonFactor() {
    throw new RuntimeException('not implemented yet')
  }


  List calculatePrime(int input) {
    List primeFactors = []

    findNextFactor(input, primeFactors)
    if (primeFactors.size() == 1) {
      primeFactors = [1, input]
    }
    primeFactors
  }

  private void findNextFactor(int input, List result) {
    if (input < 2) return

    Integer divisor = (2..input).find { input.mod(it) == 0 }

    if (divisor) {
      result << divisor.intValue()
      findNextFactor((input / divisor) as int, result)
    }
  }

  private List gatherCommonPrimeFactors(List<List> primeLists) {
    List gcfValues
    gcfValues = primeLists[0]

    (0..(primeLists.size() - 1)).each { int index ->
      if (gcfValues.intersect(primeLists[index]).size() > primeLists[index].intersect(gcfValues).size()) {
        gcfValues = primeLists[index].intersect(gcfValues)
      } else {
        gcfValues = gcfValues.intersect(primeLists[index])
      }
    }
    gcfValues
  }

  private int calculateProduct(List<Integer> gcfValues) {
    if (gcfValues == null || gcfValues.size() == 0) {
      return 1
    }

    if (gcfValues.size() == 1) {
      return gcfValues[0]
    }

    int product = 1
    gcfValues.each { factor ->
      product = product * factor
    }

    product
  }

}
