package org.example.list

class CustomArrayList(startSize: Int) : CustomList {
    var inner = IntArray(startSize)
    private var elementCount: Int = 0

    override val size: Int
        get() = elementCount

    override fun add(element: Int) {
        if (elementCount == inner.size) {
            resize(if (inner.isEmpty()) 1 else inner.size * 2)
        }
        inner[elementCount++] = element
    }

    override operator fun get(index: Int): Int {
        if ((index < 0) || (index >= elementCount)) {
            throw IndexOutOfBoundsException("Index: $index, Size: $size")
        }
        return inner[index]
    }

    override operator fun set(index: Int, value: Int) {
        if ((index < 0) || (index >= elementCount)) {
            throw IndexOutOfBoundsException("Index: $index, Size: $size")
        }
        inner[index] = value
    }

    override fun addFirst(element: Int) {
        if (elementCount == inner.size) {
            resize(if (inner.isEmpty()) 1 else inner.size * 2)
        }
        // Сдвигаем все элементы вправо на 1 позицию
        for (i in elementCount downTo 1) {
            inner[i] = inner[i - 1]
        }
        inner[0] = element
        elementCount++
    }

    override fun remove(element: Int): Boolean {
        val index = indexOf(element)
        if (index == -1) return false

        // Сдвигаем хвост массива влево
        for (i in index until elementCount - 1) {
            inner[i] = inner[i + 1]
        }
        inner[elementCount - 1] = 0 // Очищаем последнюю ячейку
        elementCount--
        return true
    }

    override fun indexOf(element: Int): Int {
        for (i in 0 until elementCount) {
            if (inner[i] == element) return i
        }
        return -1
    }

    override fun iterator(): Iterator<Int> {
        return object : Iterator<Int> {
            private var currentIndex = 0
            override fun hasNext(): Boolean = currentIndex < elementCount
            override fun next(): Int {
                if (!hasNext()) throw NoSuchElementException()
                return inner[currentIndex++]
            }
        }
    }
    private fun resize(newSize: Int) {
        val newArray = IntArray(newSize)
        for (i in 0 until elementCount) {
            newArray[i] = inner[i]
        }
        inner = newArray
    }

    companion object {
        fun customArrayListOf(vararg items: Int) =
            items.fold(CustomArrayList(items.size)) { list, item ->
                list.also { it.add(item) }
            }
    }
}