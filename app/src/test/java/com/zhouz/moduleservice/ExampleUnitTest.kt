package com.zhouz.moduleservice

import org.junit.Assert.assertEquals
import org.junit.Test
import java.lang.ref.WeakReference
import kotlin.concurrent.thread

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
}


class ZZTest {
    fun start() {
        search {
            println("search end")
        }
    }

    fun stop() {
        thread {
            Thread.sleep(5000)
            Runtime.getRuntime().gc()
            println("${callbackWeak?.get()}")
            callbackWeak?.get()?.invoke()
        }
    }

    private var callbackWeak: WeakReference<() -> Unit>? = null

    private fun search(callback: () -> Unit) {
        callbackWeak = WeakReference(callback)
    }
}



fun main(args: Array<String>) {
    val test = ZZTest()
    test.start()
    test.stop()
    println("end")
}



