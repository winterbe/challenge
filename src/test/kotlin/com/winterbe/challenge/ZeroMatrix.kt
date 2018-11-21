package com.winterbe.challenge

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

/**
 * Write an algorithmn such that if an element in an MxN matrix is 0, its entire row and column are set to 0.
 */
fun zeroMatrix(matrix: Array<Array<String>>) {
    val n = matrix.size
    val m = matrix[0].size
    var zeroX = false
    var zeroY = false
    for (i in 0 until n) {
        for (j in 0 until m) {
            if (matrix[i][j] == "0") {
                if (i == 0) {
                    zeroX = true
                }
                if (j == 0) {
                    zeroY = true
                }
                if (i > 0 && j > 0) {
                    matrix[0][j] = "0"
                    matrix[i][0] = "0"
                }
            }
        }
    }
    for (i in 1 until n) {
        if (matrix[i][0] == "0") {
            for (j in 0 until m) {
                matrix[i][j] = "0"
            }
        }
    }
    for (j in 1 until m) {
        if (matrix[0][j] == "0") {
            for (i in 0 until m) {
                matrix[i][j] = "0"
            }
        }
    }
    if (zeroX) {
        for (j in 0 until m) {
            matrix[0][j] = "0"
        }
    }
    if (zeroY) {
        for (i in 0 until m) {
            matrix[i][0] = "0"
        }
    }
}

class ZeroMatrixTest {
    @Test
    fun test() {
        val given =
            """
            11101
            10111
            11111
            11111
            11110
            """.trimIndent()
        val expected =
            """
            00000
            00000
            10100
            10100
            00000
            """.trimIndent()
        val input = given.toMatrix()
        zeroMatrix(input)
        assertEquals(expected, input.toMatrixString())
    }
}