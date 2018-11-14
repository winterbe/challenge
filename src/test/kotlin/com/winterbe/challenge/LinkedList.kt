package com.winterbe.challenge

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test


class MyLinkedList<T> {
    private var head: Node<T>? = null
    private var tail: Node<T>? = null

    fun add(element: T) {
        val node = Node(element)
        if (head == null) {
            head = node
            tail = node
        } else {
            tail!!.next = node
            node.prev = tail
            tail = node
        }
    }

    fun remove(element: T) {
        if (head == null) {
            return
        }
        var node: Node<T> = head!!
        while (true) {
            if (node.element == element) {
                remove(node)
                return
            }
            if (node.next == null) {
                return
            }
            node = node.next!!
        }
    }

    private fun remove(node: Node<T>) {
        if (node == head && node == tail) {
            head = null
            tail = null
            return
        }
        node.prev?.let {
            it.next = node.next
            if (it.prev == null) {
                head = it
            }
            if (it.next == null) {
                tail = it
            }
        }
        node.next?.let {
            it.prev = node.prev
            if (it.prev == null) {
                head = it
            }
            if (it.next == null) {
                tail = it
            }
        }
    }

    fun size(): Int {
        if (head == null) {
            return 0
        }
        var count = 1
        var current: Node<T> = head!!
        while (current.next != null) {
            current = current.next!!
            count++
        }
        return count
    }

    private class Node<T>(
        val element: T,
        var next: Node<T>? = null,
        var prev: Node<T>? = null
    )
}


class MyLinkedListTest {
    @Test
    fun testAdd() {
        val list = MyLinkedList<String>()
        assertEquals(0, list.size())
        list.add("foo")
        assertEquals(1, list.size())
        list.add("bar")
        assertEquals(2, list.size())
    }

    @Test
    fun testRemove() {
        val list = MyLinkedList<String>()
        list.add("foo")
        list.add("bar")
        assertEquals(2, list.size())
        list.remove("foo")
        assertEquals(1, list.size())
        list.remove("bar")
        assertEquals(0, list.size())
    }
}