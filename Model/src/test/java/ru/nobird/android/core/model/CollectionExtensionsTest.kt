package ru.nobird.android.core.model

import org.junit.Assert
import org.junit.Test


class CollectionExtensionsTest {
    @Test
    fun insertAtStart() {
        val sample = emptyList<String>()
        val text = "Hello"
        Assert.assertEquals(listOf(text), sample.insert(text, pos = 0))
    }
}