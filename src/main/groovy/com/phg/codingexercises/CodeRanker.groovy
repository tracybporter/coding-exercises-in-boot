package com.phg.codingexercises

import groovy.transform.EqualsAndHashCode

class CodeRanker {
  List calculateScores(List<ScoreCategories> scoreCategories) {

    List scores = []
    scoreCategories.each { scores << 0 }

    scores = rewardWinners('clarity', scoreCategories, scores)
    scores = rewardWinners('originality', scoreCategories, scores)
    scores = rewardWinners('difficulty', scoreCategories, scores)

    scores
  }

  List rewardWinners(String scoreDimension, List<ScoreCategories> scoreCategories, List scores) {
    int maxValue = scoreCategories[scoreDimension].max()
    List matches = scoreCategories.findAll { it[scoreDimension] == maxValue }

    if (matches.size() < scoreCategories.size()) {
      matches.each { winner ->
        def indexWinner = scoreCategories.indexOf(winner)
        scores[indexWinner] += 1
      }
    }
    scores
  }
}

@EqualsAndHashCode
class ScoreCategories {
  int clarity
  int originality
  int difficulty
}