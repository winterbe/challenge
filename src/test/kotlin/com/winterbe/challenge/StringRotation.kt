package com.winterbe.challenge

import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

/**
 * Check if s2 is a rotation of s1. You can use at most one invocation to `String.isSubstring()`.
 */
fun isRotation(s1: String, s2: String): Boolean {
    if (s1 == s2) {
        return true
    }
    if (s1.length != s2.length) {
        return false
    }

    fun checkRotationFactor(n: Int): Boolean {
        for (i in 0 until s1.length) {
            val j = (i + n) % s1.length
            if (s1[i] != s2[j]) {
                return false
            }
        }
        return true
    }

    // let's assume `mid` is the rotation factor n
    val mid = s1.length / 2
    val substringBuilder = StringBuilder()
    for (i in 0 until s1.length - mid) {
        substringBuilder.append(s1[i])
    }
    val substring = substringBuilder.toString()

    // Example: n = 5
    // waterbottle -> ottlewaterb
    // ~~~~~~              ~~~~~~

    if (s2.contains(substring)) {
        // rotation factor n must be <= mid
        for (n in mid .. 1) {
            val rotationDetected = checkRotationFactor(n)
            if (rotationDetected) {
                return true
            }
        }
    } else {
        // rotation factor n must be > mid
        for (n in mid + 1 until s1.length - 1) {
            val rotationDetected = checkRotationFactor(n)
            if (rotationDetected) {
                return true
            }
        }
    }

    return false
}


class StringRotationTest {
    @Test
    fun test() {
        assertTrue(isRotation("waterbottle", "erbottlewat"))
    }
}