package com.phg.codingexercises

import org.springframework.stereotype.Service

@Service
class FactoringService {
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
}
