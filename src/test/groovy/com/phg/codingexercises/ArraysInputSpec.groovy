package com.phg.codingexercises

import spock.lang.Specification

class ArraysInputSpec extends Specification {

  void 'constructor sets list'() {
    expect:
    new ArraysInput([5, 9, 0]).inputs == [5, 9, 0]
  }
}
