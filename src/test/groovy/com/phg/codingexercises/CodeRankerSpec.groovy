package com.phg.codingexercises

import spock.lang.Specification

class CodeRankerSpec extends Specification {

  void 'scores Alice and Bob with a single tie value'() {
    given:
    ScoreCategories aliceScores = new ScoreCategories(clarity: 5, originality: 6, difficulty: 7)
    ScoreCategories bobScores = new ScoreCategories(clarity: 3, originality: 6, difficulty: 10)

    when:
    List rankings = new CodeRanker().calculateScores([aliceScores, bobScores])

    then:
    rankings == [1, 1]
  }

  void 'scores Alice and Bob with larger numbers'() {
    given:
    ScoreCategories aliceScores = new ScoreCategories(clarity: 17, originality: 28, difficulty: 30)
    ScoreCategories bobScores = new ScoreCategories(clarity: 99, originality: 16, difficulty: 8)

    when:
    List rankings = new CodeRanker().calculateScores([aliceScores, bobScores])

    then:
    rankings == [2, 1]
  }
}
