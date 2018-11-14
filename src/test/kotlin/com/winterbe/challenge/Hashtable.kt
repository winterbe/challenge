package com.winterbe.challenge

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.util.*

class MyHashtable<K : Any, V : Any>(private val capacity: Int = 128) {
    private val data = Array<LinkedList<Entry>>(capacity) { LinkedList() }

    fun put(key: K, value: V) {
        val index = indexOf(key)
        data[index].add(Entry(key, value))
    }

    fun get(key: K): V? {
        val index = indexOf(key)
        data[index].forEach {
            if (it.key == key) {
                return it.value
            }
        }
        return null
    }

    fun remove(key: K) {
        TODO("Not implemented")
    }

    fun size(): Int {
        var size = 0
        data.forEach {
            size += it.size
        }
        return size
    }

    private fun indexOf(key: K): Int {
        val hashCode = key.hashCode()
        return hashCode % capacity
    }

    inner class Entry(val key: K, val value: V)
}

class HashtableTest {
    @Test
    fun testPutGet() {
        val hashtable = MyHashtable<String, Int>()
        hashtable.put("foo", 23)
        assertEquals(23, hashtable.get("foo"))
    }
}