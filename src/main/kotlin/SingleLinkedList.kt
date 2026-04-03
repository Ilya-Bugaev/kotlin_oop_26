package org.example

class SingleLinkedList : CustomList {

    class Node {
        var value = 0
        var next: Node? = null
    }

    var head: Node? = null

    private val inner = mutableListOf<Int>()

    override val size: Int
        get() {
            var cur = head
            var count = 0

            while (cur != null) {
                cur = cur.next
                count++
            }

            return count
        }

    override fun add(element: Int) {
        if  (head == null) {
            head = Node().also { it.value = element }
        } else {
            var cur = head
            while (cur?.next != null) {
                cur = cur.next
            }
            cur?.next = Node().also { it.value = element }
        }
    }

    override operator fun set(index: Int, value: Int) {
        var cur = head
        var currentIndex = 0

        while (cur != null && currentIndex < index) {
            cur = cur.next
            currentIndex++
        }
        cur?.value = value
    }

    override fun addFirst(element: Int) {
        val newNode = Node().also {
            it.value = element
            it.next = head
        }
        head = newNode
    }

    override operator fun get(index: Int): Int {
        var cur = head
        var currentIndex = 0

        while (cur != null && currentIndex < index) {
            cur = cur.next
            currentIndex++
        }

        if (cur == null) throw IndexOutOfBoundsException("Index: $index, Size: $size")
        return cur.value
    }

    override fun indexOf(element: Int): Int {
        var count = 0

        for (n in this.iterator()) {
            if (n == element) {
                return count
            }
            count++
        }
        return -1
    }

    override fun remove(element: Int): Boolean {
        if (head == null) return false

        if (head?.value == element) {
            head = head?.next
            return true
        }

        var current = head
        while (current?.next != null) {
            if (current.next?.value == element) {
                current.next = current.next?.next
                return true
            }
            current = current.next
        }
        return false
    }

    override fun iterator(): Iterator<Int> {
        return object : Iterator<Int> {
            var cur = head
            override fun hasNext(): Boolean {
                return cur != null
            }

            override fun next(): Int {
                val value = cur!!.value
                cur = cur!!.next

                return value
            }
        }
    }

    companion object {
        fun singleLinkedListOf(vararg items: Int) =
            items.fold(SingleLinkedList()) { list, item ->
                list.also{ it.add(item) }
            }
    }
}