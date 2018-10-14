package com.winterbe.challenge.roman

import org.junit.jupiter.api.Assertions.assertAll
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.function.Executable

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