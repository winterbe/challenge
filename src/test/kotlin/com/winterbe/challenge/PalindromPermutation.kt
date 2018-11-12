package com.winterbe.challenge

import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

/**
 * Check if at least one palindrom exists in all available character permutations of the given input.
 *
 * Assumptions: ASCII input, use lower case, ignore non-letters
 */
fun existsPalindromPermutation(input: String): Boolean {
    val array = Array(26) { false }
    var oddCount = 0
    input
        .toLowerCase()
        .filter { it in 'a'..'z' }
        .forEach {
            val i = it - 'a'
            val odd = !array[i]
            array[i] = odd
            if (odd) {
                oddCount++
            } else {
                oddCount--
            }
        }
    return oddCount <= 1
}

class PalindromPermutationTest {
    @Test
    fun test() {
        assertTrue(existsPalindromPermutation("Tact Coa"))
    }
}