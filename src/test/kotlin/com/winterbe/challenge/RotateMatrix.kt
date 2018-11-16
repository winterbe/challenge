package com.winterbe.challenge

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

/**
 * Given a NxN matrix, rotate the matrix in place by 90°.
 */
fun rotateMatrix(matrix: Array<Array<String>>) {
    fun rotateCoord(c: Pair<Int, Int>) = Pair(c.second, matrix.size - 1 - c.first)

    for (i in 0 until matrix.size - 2) {
        for (j in i until matrix.size - 1 - i) {
            val c1 = Pair(i, j)
            val c2 = rotateCoord(c1)
            val c3 = rotateCoord(c2)
            val c4 = rotateCoord(c3)

            val v1 = matrix[i][j]
            val v2 = matrix[c2.first][c2.second]
            val v3 = matrix[c3.first][c3.second]
            val v4 = matrix[c4.first][c4.second]

            matrix[c2.first][c2.second] = v1
            matrix[c3.first][c3.second] = v2
            matrix[c4.first][c4.second] = v3
            matrix[c1.first][c1.second] = v4
        }
    }

}

class RotateMatrixTest {
    @Test
    fun testOddLength() {
        val given =
            """
            01234
            12345
            23456
            34567
            45678
            """.trimIndent()
        val expected =
            """
            43210
            54321
            65432
            76543
            87654
            """.trimIndent()
        val input = given.toMatrix()
        rotateMatrix(input)
        assertEquals(expected, input.toMatrixString())
    }

    @Test
    fun testEvenLength() {
        val given =
            """
            0123
            1234
            2345
            3456
            """.trimIndent()
        val expected =
            """
            3210
            4321
            5432
            6543
            """.trimIndent()
        val input = given.toMatrix()
        rotateMatrix(input)
        assertEquals(expected, input.toMatrixString())
    }
}

fun String.toMatrix(): Array<Array<String>> {
    return lines()
        .map { it.split("").filter { it.isNotBlank() }.toTypedArray() }
        .toTypedArray()
}

fun Array<Array<String>>.toMatrixString(): String {
    return this.joinToString("\n") { it.joinToString("") }
}