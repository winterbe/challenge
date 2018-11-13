package com.winterbe.challenge

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

fun urlify(input: CharArray, length: Int) {
    var writeIndex = input.size - 1
    var readIndex = length - 1
    while (readIndex >= 0) {
        val c = input[readIndex]
        if (c != ' ') {
            input[writeIndex] = c
            writeIndex--
            readIndex--
        } else {
            input[writeIndex--] = '0'
            input[writeIndex--] = '2'
            input[writeIndex--] = '%'
            readIndex--
        }
    }
}

class URLifyTest {
    @Test
    fun test() {
        val given =
            "Mr John Smith    "
        val expected =
            "Mr%20John%20Smith"
        val charArray = given.toCharArray()
        urlify(charArray, 13)
        assertEquals(expected, String(charArray))
    }
}