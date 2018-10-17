package com.phg.codingexercises

import org.springframework.stereotype.Service

@Service
class DollarsService {
  PageResult calculateCoinCounts(String input) {
    BigDecimal dollars = new BigDecimal(input).setScale(2)

    CoinCountResult coinCountResult = new CoinCountResult(input: dollars)

    new PageResult(results: [coinCountResult])
  }
}
