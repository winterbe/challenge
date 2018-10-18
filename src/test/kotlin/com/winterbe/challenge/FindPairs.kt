package com.winterbe.challenge

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

/**
 * Given an integer array and number k, output all unique pairs that sum up to k.
 * Example: for input [1, 3, 2, 5, 46, 6, 7, 4] and k = 4, output (1, 3).
 */
fun findPairs(k: Int, array: Array<Int>): List<Pair<Int, Int>> {

    fun divide(fence: Int, from: Int, to: Int): Int {
        var index = from
        var end = to
        while (index < end) {
            val num = array[index]
            if (num > fence) {
                val last = array[end - 1]
                array[index] = last
                array[end - 1] = num
                end--
            } else {
                index++
            }
        }
        return index
    }

    fun findPairsRecursively(depth: Int, fromLeft: Int, toLeft: Int, fromRight: Int, toRight: Int): List<Pair<Int, Int>> {
        if (fromLeft - toLeft <= 1 || fromRight - toRight <= 1) {
            val pairs = mutableListOf<Pair<Int, Int>>()
            for (i in (fromLeft until toLeft)) {
                for (j in (fromRight until toRight)) {
                    val a = array[i]
                    val b = array[j]
                    if (a + b == k) {
                        pairs.add(Pair(a, b))
                    }
                }
            }
            return pairs
        }
        val nextDepth = depth + 2
        val leftDivider = divide(k / nextDepth, fromLeft, toLeft)
        val rightDivider = divide(k / nextDepth, fromRight, toRight)
        return findPairsRecursively(nextDepth, fromLeft, leftDivider, rightDivider, toRight) +
                findPairsRecursively(nextDepth, leftDivider, toLeft, fromRight, rightDivider)
    }

    val depth = 2
    val index = divide(k / depth, 0, array.size)
    return findPairsRecursively(depth, 0, index, index, array.size)
}


class FindPairsTest {
    @Test
    fun test() {
        val array = arrayOf(1, 3, 2, 5, 46, 6, 7, 4)
        assertEquals(listOf(Pair(1, 3)), findPairs(4, array))
    }
}