package com.winterbe.challenge

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

fun findDuplicate(array: Array<Int>): Int? {
    // check for consecutive duplicates in O(n)
    for (i in 0 until array.size - 1) {
        if (array[i] == array[i + 1]) {
            return array[i]
        }
    }
    // check for non-consecutive duplicates in O(n^2) total
    for (i in 0 until array.size - 1) {
        val num = array[i]
        // skip consecutive duplicates (i+2) as it's already handled
        for (j in i + 2 until array.size) {
            val current = array[j]
            if (num == current) {
                return num
            }
        }
    }
    return null
}

class FindDuplicateTest {
    @Test
    fun test1() {
        assertEquals(2, findDuplicate(arrayOf(1, 3, 4, 2, 2)))
    }

    @Test
    fun test2() {
        assertEquals(3, findDuplicate(arrayOf(3, 1, 3, 4, 2)))
    }

    @Test
    fun test3() {
        assertEquals(3, findDuplicate(arrayOf(5, 1, 2, 3, 4, 5, 5)))
    }
}