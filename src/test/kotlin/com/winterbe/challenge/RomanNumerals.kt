package com.winterbe.challenge

import org.junit.jupiter.api.Assertions.assertAll
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.function.Executable

val nums = mapOf(
    "I" to 1,
    "V" to 5,
    "X" to 10,
    "L" to 50,
    "C" to 100,
    "D" to 500,
    "M" to 1000
)

fun sumRomanNumerals(romanNumerals: String): Int {
    val nums = romanNumerals
        .reversed()
        .map { nums[it.toString()]!! }
    var result = 0
    var summand = 0
    nums.forEach { num ->
        if (num >= summand) {
            summand = num
            result += summand
        } else {
            result -= num
        }
    }
    return result
}


class RomanNumeralsTest {

    @Test
    fun `single chars`() {
        verifyAll(
            "I" to 1,
            "V" to 5,
            "X" to 10,
            "L" to 50,
            "C" to 100,
            "D" to 500,
            "M" to 1000
        )
    }

    @Test
    fun `more complex numbers`() {
        verifyAll(
            "VI" to 6,
            "IV" to 4,
            "IIX" to 8,
            "XXXXII" to 42,
            "XCIX" to 99,
            "MMXVIII" to 2018
        )
    }

    private fun verifyAll(vararg pairs: Pair<String, Int>) {
        val executables = pairs.map { (roman, num) ->
            Executable {
                assertEquals(num, sumRomanNumerals(roman)) {
                    "Expected roman number $roman to be: $num"
                }
            }
        }
        assertAll(executables)
    }
}