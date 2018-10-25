package com.winterbe.challenge

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import kotlin.math.pow
import kotlin.math.sqrt

// Given a list of points as [x,y] pairs; a vertex in [x,y] form; and an integer k,
// return the kth closest points in terms of euclidean distance

// Example: points = [[1,2], [3,-1], [2,1], [2,3]]  vertex = [2,2]  k = 2

typealias Point = Pair<Int, Int>
typealias Vertex = Point

fun vertexDistance(points: Array<Point>, vertex: Vertex, k: Int): List<Point> {

    fun euclideanDistance(point: Point): Double {
        val a = (point.first - vertex.first).toDouble()
        val b = (point.second - vertex.second).toDouble()
        return sqrt(a.pow(2) + b.pow(2))
    }

    fun divide(from: Int, to: Int): Int {
        val fence = euclideanDistance(points[from])
        var fenceIndex = from
        var index = from + 1
        var end = to
        while (index < end) {
            val point = points[index]
            val distance = euclideanDistance(point)
            if (distance <= fence) {
                fenceIndex++
                index++
            } else {
                val last = points[end - 1]
                points[index] =last
                points[end - 1] = point
                end--
            }
        }

        return when {
            fenceIndex > k - 1 ->
                divide(from, fenceIndex)
            fenceIndex < k - 1 ->
                divide(fenceIndex + 1, to)
            else ->
                fenceIndex
        }
    }

    val index = divide(0, points.size)
    check(index + 1 == k)
    return points.take(k)
}

class VertexDistanceTest {
    @Test
    fun test() {
        val points = arrayOf(Point(1, 2), Point(3, -1), Point(2, 1), Point(2, 3))
        val vertex = Vertex(2, 2)
        val k = 3
        val result = vertexDistance(points, vertex, k)
        assertEquals(3, result.size)
        assertTrue { result.contains(Point(1, 2)) }
        assertTrue { result.contains(Point(2, 1)) }
        assertTrue { result.contains(Point(2, 3)) }
    }
}