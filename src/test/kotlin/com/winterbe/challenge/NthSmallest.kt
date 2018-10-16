package com.winterbe.challenge

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

/**
 * Finds the n-th smallest number of a given Array.
 *
 * https://interviewing.io/recordings/Java-Google-1/
 */
fun nthSmallest(n: Int, nums: Array<Int>): Int {
    fun divide(nums: Array<Int>, from: Int, to: Int): Pair<Int, Int> {
        val fence = nums[from]
        var fenceIndex = from
        var index = from + 1
        var end = to
        while (index < end) {
            val num = nums[index]
            if (num < fence) {
                nums[fenceIndex] = num
                nums[index] = fence
                fenceIndex = index
                index++
            } else {
                val last = nums[end - 1]
                nums[index] = last
                nums[end - 1] = num
                end--
            }
        }
        return when {
            fenceIndex > n - 1 ->
                from to fenceIndex
            fenceIndex < n - 1 ->
                (fenceIndex + 1) to to
            else ->
                fenceIndex to fenceIndex
        }
    }

    var from = 0
    var to = nums.size
    while (from != to) {
        val (a, b) = divide(nums, from, to)
        from = a
        to = b
    }
    return nums[from]
}


class NthSmallestTest {
    @Test
    fun test() {
        assertEquals(1, nthSmallest(1, arrayOf(1, 6, 3, 9, 8, 5)))
        assertEquals(3, nthSmallest(2, arrayOf(1, 6, 3, 9, 8, 5)))
        assertEquals(5, nthSmallest(3, arrayOf(1, 6, 3, 9, 8, 5)))
        assertEquals(6, nthSmallest(4, arrayOf(1, 6, 3, 9, 8, 5)))
        assertEquals(8, nthSmallest(5, arrayOf(1, 6, 3, 9, 8, 5)))
        assertEquals(9, nthSmallest(6, arrayOf(1, 6, 3, 9, 8, 5)))
    }
}