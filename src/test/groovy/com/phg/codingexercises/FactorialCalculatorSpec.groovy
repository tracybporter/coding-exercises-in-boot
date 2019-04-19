package com.phg.codingexercises

import spock.lang.Specification

class FactorialCalculatorSpec extends Specification {

    void "calculates factorial as product of all subsequent numbers"() {
        given:
        FactorialCalculator calculator = new FactorialCalculator()

        expect:
        result == calculator.computeFactorial(value)

        where:
        value | result
        1     | 1
        2     | 2
        3     | 6
        25    | 15511210043330985984000000

    }
}
