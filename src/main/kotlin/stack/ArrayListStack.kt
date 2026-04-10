package org.example.stack

import org.example.list.CustomArrayList
import org.example.list.CustomList

class ArrayListStack(startSize: Int = 16) : Stack, CustomList by CustomArrayList(startSize) {

    override fun push(value: Int) {
        addFirst(value)
    }

    override fun pop(): Int {
        if (isEmpty) throw NoSuchElementException("Stack is empty")
        val value = get(0)
        remove(value)
        return value
    }

    override fun peek(): Int {
        if (isEmpty) throw NoSuchElementException("Stack is empty")
        return get(0)
    }

    override val isEmpty: Boolean
        get() = size == 0
}