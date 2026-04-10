package org.example

import org.example.list.SingleLinkedList
import org.example.list.CustomArrayList
import org.example.stack.SingleLinkedStack
import org.example.stack.ArrayListStack

fun main() {
    // 1. Связный список
    val linkedList = SingleLinkedList.singleLinkedListOf(1, 2, 3)

    // 2. Массивный список
    val arrayList = CustomArrayList.customArrayListOf(10, 20, 30)

    // 3. Стек на связном списке (LIFO)
    val linkedStack = SingleLinkedStack().apply { push(100); push(200); push(300) }

    // 4. Стек на массиве (LIFO)
    val arrayStack = ArrayListStack().apply { push(1000); push(2000); push(3000) }

    // Один и тот же метод корректно обрабатывает все 4 типа
    ListPrinter.printList(linkedList)
    ListPrinter.printList(arrayList)
    ListPrinter.printList(linkedStack)
    ListPrinter.printList(arrayStack)
}