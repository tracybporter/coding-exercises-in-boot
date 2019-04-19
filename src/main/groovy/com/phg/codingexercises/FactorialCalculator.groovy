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
}
