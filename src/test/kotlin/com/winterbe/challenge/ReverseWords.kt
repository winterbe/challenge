package com.winterbe.challenge

import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import java.util.*

/**
 * Given a string with multiple words and spaces represented as a character array.
 * Write an in-place algorithm to reverse the order of words in the string.
 */
fun reverseWords(array: CharArray) {
    if (array.isEmpty() || !array.contains(' ')) {
        return
    }

    fun swapCharsBetween(from: Int, to: Int) {
        val max = from + ((to - from) / 2)
        for ((i, index1) in (from..max).withIndex()) {
            val index2 = to - i
            val c1 = array[index1]
            val c2 = array[index2]
            array[index1] = c2
            array[index2] = c1
        }
    }

    // (1) swap all chars from left to right
    swapCharsBetween(0, array.size - 1)

    // (2) determine word boundaries
    val wordBoundaries = mutableListOf(0)
    array.forEachIndexed { index, c ->
        if (c == ' ') {
            wordBoundaries.add(index + 1)
        }
    }
    wordBoundaries.add(array.size + 1)

    // (3) swap chars of each word
    for (i in (0 until wordBoundaries.size - 1)) {
        val from = wordBoundaries[i]
        val to = wordBoundaries[i + 1]
        swapCharsBetween(from, to - 2)
    }
}

class ReverseWordsTest {
    @Test
    fun asymmetric() {
        verify("future is near", "near is future")
    }

    @Test
    fun symmetric() {
        verify("123 456", "456 123")
    }

    private fun verify(given: String, expected: String) {
        val array = given.toCharArray()
        reverseWords(array)
        assertTrue(Arrays.equals(array, expected.toCharArray())) {
            "Expected `$expected` but was `${array.joinToString("")}`"
        }
    }
}