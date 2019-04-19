package com.phg.codingexercises

class FactorialCalculator {
    BigInteger computeFactorial(int input) {
        BigInteger total = new BigInteger(1)
        if (input < 3) {
            return input
        }
        (2..input).each { step -> total = total * step }
        return total
    }

    String generateFizzBuzz(int inputValue) {
        if (inputValue < 2) {
            return '1'
        }

        String result = '1'
        (2..inputValue).each { value ->
            String appender = ''
            if (value % 3 == 0) {
                appender = 'Fizz'
            }

            if (value % 5 == 0) {
                appender += 'Buzz'
            }

            if (appender) {
                result += appender
            } else {
                result += value
            }
        }
        result
    }
}
