package com.winterbe.challenge

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.lang.StringBuilder

fun compressText(input: String): String {
    val builder = StringBuilder(input.length)
    var count = 0
    for (i in 0 until input.length) {
        count++
        if (i + 1 >= input.length || input[i] != input[i + 1]) {
            builder.append(input[i])
            builder.append(count)
            count = 0
        }
    }
    return if (builder.length < input.length) {
        builder.toString()
    } else {
        input
    }
}

class StringCompressionTest {
    @Test
    fun test() {
        assertEquals("a2b1c5a3", compressText("aabcccccaaa"))
    }
}