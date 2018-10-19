package com.winterbe.challenge

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

/**
 * Given an unsorted array, find the nth smallest element in the array.
 */
fun nthSmallest(n: Int, nums: Array<Int>): Int {
    fun nthSmallest(from: Int, to: Int): Int {
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
                nthSmallest(from, fenceIndex)
            fenceIndex < n - 1 ->
                nthSmallest(fenceIndex + 1, to)
            else ->
                nums[fenceIndex]
        }
    }
    return nthSmallest(0, nums.size)
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