package com.phg.codingexercises

import spock.lang.Specification
import spock.lang.Unroll

class FactorialCalculatorSpec extends Specification {
    FactorialCalculator calculator = new FactorialCalculator()

    void "calculates factorial as product of all subsequent numbers"() {
        expect:
        result == calculator.computeFactorial(value)

        where:
        value | result
        1     | 1
        2     | 2
        3     | 6
        25    | 15511210043330985984000000

    }

    @Unroll
    void 'returns Fizz for multiples of 3 and Buzz for multiples of 5'() {
        expect:
        result == calculator.generateFizzBuzz(value)

        where:
        value | result
        1     | '1'
        2     | '12'
        3     | '12Fizz'
        4     | '12Fizz4'
        5     | '12Fizz4Buzz'
        16    | '12Fizz4BuzzFizz78FizzBuzz11Fizz1314FizzBuzz16'
    }
}
