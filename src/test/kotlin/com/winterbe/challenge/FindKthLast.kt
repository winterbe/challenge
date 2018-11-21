package com.winterbe.challenge

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

data class Node(val value: Int, val next: Node? = null)

/**
 * Find the kth to last element of a singly linked list.
 */
fun findKthLast(head: Node, k: Int): Node {
    var p1 = head
    var p2 = head.run {
        var i = 1
        var n = head.next!!
        while (i < k) {
            n = n.next!!
            i++
        }
        n
    }
    while (p2.next != null) {
        p1 = p1.next!!
        p2 = p2.next!!
    }
    return p1
}

class FindKthLast {
    @Test
    fun test() {
        val head = Node(1, Node(2, Node(3, Node(4, Node(5, Node(6, Node(7, Node(8, Node(9, Node(10))))))))))
        val kthLast = findKthLast(head, 3)
        assertEquals(kthLast.value, 7)
    }
}