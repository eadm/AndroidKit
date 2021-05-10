package ru.nobird.app.core.model

import ru.nobird.app.core.model.insert
import kotlin.test.Test
import kotlin.test.assertEquals

class CollectionExtensionsTest {
    @Test
    fun insertAtStart() {
        val sample = emptyList<String>()
        val text = "Hello"
        assertEquals(listOf(text), sample.insert(text, pos = 0))
    }
}