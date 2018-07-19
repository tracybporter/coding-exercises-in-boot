package com.phg.codingexercises

import org.springframework.stereotype.Service

@Service
class FactoringService {

  def productOfEntries = { result, i ->
    result * i
  }

  PageResult calculatePrime(String input) {
    PageResult pageResult = new PageResult()
    List inputs = input.tokenize(',')

    inputs.each { String individualInput ->
      if (individualInput.isInteger()) {
        pageResult.results << calculatePrime(individualInput as int).results[0]
      }
    }

    if (pageResult.results.size() > 1) {
      List commonPrimeFactors = gatherCommonPrimeFactors(pageResult.results.prime)
      int gcf = calculateProduct(commonPrimeFactors)
      pageResult.results << new GcfLcmResult(greatestCommonFactor: gcf)
    }
    pageResult
  }

  PageResult calculatePrime(int input) {
    FactorResult result = new FactorResult(input: input, prime: [])

    findNextFactor(input, result)
    if (result.prime.size() == 1) {
      result.prime = [1, input]
    }
    new PageResult(results: [result])
  }

  private void findNextFactor(int input, FactorResult result) {
    if (input < 2) return

    Integer divisor = (2..input).find { input.mod(it) == 0 }

    if (divisor) {
      result.prime << divisor.intValue()
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
      return 0
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