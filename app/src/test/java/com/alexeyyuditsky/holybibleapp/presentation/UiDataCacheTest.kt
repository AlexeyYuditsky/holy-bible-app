package com.alexeyyuditsky.holybibleapp.presentation

import org.junit.Assert.*
import org.junit.Test

/**
 * Test for [UiDataCache.Base]
 * */
class UiDataCacheTest {

    private val dataCache = UiDataCache.Base()

    private val source = listOf(
        BookUi.Testament(Int.MIN_VALUE, "old"),
        BookUi.Base(1, "first"),
        BookUi.Base(2, "second"),
        BookUi.Testament(Int.MAX_VALUE, "new"),
        BookUi.Base(3, "third"),
        BookUi.Base(4, "fourth"),
    )

    @Test
    fun `collapse first testament`() {
        dataCache.cache(source)

        val actual = dataCache.changeState(Int.MIN_VALUE)
        val expected = listOf(
            BookUi.Testament(Int.MIN_VALUE, "old", true),
            BookUi.Testament(Int.MAX_VALUE, "new"),
            BookUi.Base(3, "third"),
            BookUi.Base(4, "fourth"),
        )

        assertEquals(expected, actual)
    }

    @Test
    fun `collapse second testament`() {
        dataCache.cache(source)

        val actual = dataCache.changeState(Int.MAX_VALUE)
        val expected = listOf(
            BookUi.Testament(Int.MIN_VALUE, "old"),
            BookUi.Base(1, "first"),
            BookUi.Base(2, "second"),
            BookUi.Testament(Int.MAX_VALUE, "new", true),
        )

        assertEquals(expected, actual)
    }

    @Test
    fun `collapse first testament then expand first testament`() {
        dataCache.cache(source)

        dataCache.changeState(Int.MIN_VALUE)
        val actual = dataCache.changeState(Int.MIN_VALUE)
        val expected = listOf(
            BookUi.Testament(Int.MIN_VALUE, "old"),
            BookUi.Base(1, "first"),
            BookUi.Base(2, "second"),
            BookUi.Testament(Int.MAX_VALUE, "new"),
            BookUi.Base(3, "third"),
            BookUi.Base(4, "fourth"),
        )

        assertEquals(expected, actual)
    }

    @Test
    fun `collapse second testament then expand second testament`() {
        dataCache.cache(source)

        dataCache.changeState(Int.MAX_VALUE)
        val actual = dataCache.changeState(Int.MAX_VALUE)
        val expected = listOf(
            BookUi.Testament(Int.MIN_VALUE, "old"),
            BookUi.Base(1, "first"),
            BookUi.Base(2, "second"),
            BookUi.Testament(Int.MAX_VALUE, "new"),
            BookUi.Base(3, "third"),
            BookUi.Base(4, "fourth"),
        )

        assertEquals(expected, actual)
    }

    @Test
    fun `collapse first testament then collapse second testament`() {
        dataCache.cache(source)

        dataCache.changeState(Int.MIN_VALUE)
        val actual = dataCache.changeState(Int.MAX_VALUE)
        val expected = listOf(
            BookUi.Testament(Int.MIN_VALUE, "old", true),
            BookUi.Testament(Int.MAX_VALUE, "new", true),
        )

        assertEquals(expected, actual)
    }

    @Test
    fun `collapse first testament then collapse second testament then expand first testament`() {
        dataCache.cache(source)

        dataCache.changeState(Int.MIN_VALUE)
        dataCache.changeState(Int.MAX_VALUE)
        val actual = dataCache.changeState(Int.MIN_VALUE)
        val expected = listOf(
            BookUi.Testament(Int.MIN_VALUE, "old"),
            BookUi.Base(1, "first"),
            BookUi.Base(2, "second"),
            BookUi.Testament(Int.MAX_VALUE, "new", true),
        )

        assertEquals(expected, actual)
    }

    @Test
    fun `collapse first testament then collapse second testament then expand second testament`() {
        dataCache.cache(source)

        dataCache.changeState(Int.MIN_VALUE)
        dataCache.changeState(Int.MAX_VALUE)
        val actual = dataCache.changeState(Int.MAX_VALUE)
        val expected = listOf(
            BookUi.Testament(Int.MIN_VALUE, "old", true),
            BookUi.Testament(Int.MAX_VALUE, "new"),
            BookUi.Base(3, "third"),
            BookUi.Base(4, "fourth"),
        )

        assertEquals(expected, actual)
    }

    @Test
    fun `collapse first testament then collapse second testament then expand first testament then expand second testament`() {
        dataCache.cache(source)

        dataCache.changeState(Int.MIN_VALUE)
        dataCache.changeState(Int.MAX_VALUE)
        dataCache.changeState(Int.MIN_VALUE)
        val actual = dataCache.changeState(Int.MAX_VALUE)
        val expected = listOf(
            BookUi.Testament(Int.MIN_VALUE, "old"),
            BookUi.Base(1, "first"),
            BookUi.Base(2, "second"),
            BookUi.Testament(Int.MAX_VALUE, "new"),
            BookUi.Base(3, "third"),
            BookUi.Base(4, "fourth"),
        )

        assertEquals(expected, actual)
    }

}