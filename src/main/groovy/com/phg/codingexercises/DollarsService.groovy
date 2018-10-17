package com.phg.codingexercises

import org.springframework.stereotype.Service

import java.math.RoundingMode

@Service
class DollarsService {
  PageResult calculateCoinCounts(String input) {
    BigDecimal dollars = new BigDecimal(input).setScale(2)


    CoinCountResult coinCountResult = new CoinCountResult(input: dollars)
    CalculationResult computation = computeDifference(dollars, 0.25)
    coinCountResult.quarters = computation.coinCount

    computation = computeDifference(computation.reducedValue, 0.10)
    coinCountResult.dimes = computation.coinCount

    computation = computeDifference(computation.reducedValue, 0.05)
    coinCountResult.nickels = computation.coinCount

    computation = computeDifference(computation.reducedValue, 0.01)
    coinCountResult.pennies = computation.coinCount

    new PageResult(results: [coinCountResult])
  }

  private CalculationResult computeDifference(BigDecimal value, double divisorValue) {
    CalculationResult calcResult = new CalculationResult(reducedValue: value.setScale(3, RoundingMode.UP))
    int coinCount

    if (calcResult.reducedValue > 0 && divisorValue <= calcResult.reducedValue) {
      coinCount = calcResult.reducedValue / divisorValue
    }

    if (coinCount > 0) {
      calcResult.coinCount = coinCount
      calcResult.reducedValue = value - coinCount * divisorValue
    }
    calcResult
  }
}

class CalculationResult {
  int coinCount
  BigDecimal reducedValue
}