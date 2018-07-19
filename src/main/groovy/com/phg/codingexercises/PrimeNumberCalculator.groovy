package com.phg.codingexercises

class PrimeNumberCalculator {
  List inputs
  Map<Integer, List> inputWithPrimeFactors = new HashMap<>()
  List<List> primeLists
  List gcfList
  int lcm

  PrimeNumberCalculator(List inputs) {
    this.inputs = inputs
    inputs.each { input ->
      inputWithPrimeFactors << [(input): calculatePrime(input)]
    }
    gatherCommonPrimeFactors()
    primeLists = inputWithPrimeFactors.collect { it.value }
  }

  List retrievePrimeFactors(int input) {
    inputWithPrimeFactors[input]
  }

  Integer calculateGreatestCommonFactor() {
    calculateProduct(gcfList)
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

  private List gatherCommonPrimeFactors() {
    List<List> primeLists = inputWithPrimeFactors.collect { it.value }
    List gcfValues
    gcfValues = primeLists[0]

    (0..(primeLists.size() - 1)).each { int index ->
      if (gcfValues.intersect(primeLists[index]).size() > primeLists[index].intersect(gcfValues).size()) {
        gcfValues = primeLists[index].intersect(gcfValues)
      } else {
        gcfValues = gcfValues.intersect(primeLists[index])
      }
    }
    this.gcfList = gcfValues
  }

  Integer calculateLeastCommonMultiple() {
    if (lcm > 0) return lcm

    List allUniqueFactors = primeLists.collect { it }.flatten().unique() - 1

    Map lcmFactorsWithMaxExponent = [:]

    allUniqueFactors.each { factor ->
      lcmFactorsWithMaxExponent << [(factor): 1]
      primeLists.each { primeFactors ->
        lcmFactorsWithMaxExponent[factor] = Math.max(primeFactors.count(factor), lcmFactorsWithMaxExponent[factor])
      }
    }

    lcm = 1
    lcmFactorsWithMaxExponent.each { lcmFactor ->
      lcm = lcm * lcmFactor.key**lcmFactor.value
    }
    lcm
  }

  private int calculateProduct(List<Integer> integers) {
    if (integers == null || integers.size() == 0) {
      return 1
    }

    if (integers.size() == 1) {
      return integers[0]
    }

    int product = 1
    integers.each { value ->
      product = product * value
    }

    product
  }

}
