package com.valentyne.tiktaktoe

import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun checkDraw_isCorrect() {
        val strategy1 = mutableListOf(1, 3, 4, 8, 6)
        val strategy2 = mutableListOf(2, 5, 7, 9)
        val total = strategy1 + strategy2
        assertEquals(total.toSet(), setOf(1, 2, 3, 4, 5, 6, 7, 8, 9))
    }
}