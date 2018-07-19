package com.phg.codingexercises

import org.springframework.stereotype.Service

@Service
class FactoringService {

  PageResult calculatePrime(String input) {
    PageResult pageResult = new PageResult()
    List inputsAsStrings = input.tokenize(',')
    List allInputs = inputsAsStrings.collect {
      try {
        it.toInteger().intValue()
      } catch (NumberFormatException ex) {
        null
      }
    }

    List inputs = allInputs - null

    PrimeNumberCalculator primeNumberCalculator = new PrimeNumberCalculator(inputs)

    inputs.each { int individualInput ->
      pageResult.results << new FactorResult(input: individualInput, prime: primeNumberCalculator.retrievePrimeFactors(individualInput))
    }

    if (pageResult.results.size() > 1) {
      pageResult.results << new GcfLcmResult(
              greatestCommonFactor: primeNumberCalculator.calculateGreatestCommonFactor(),
              leastCommonMultiple: primeNumberCalculator.calculateLeastCommonMultiple())
    }
    pageResult
  }

  PageResult calculatePrime(int input) {
    calculatePrime(Integer.toString(input))
  }
}