package com.example.democoroutines

import android.os.Build
import kotlinx.coroutines.*
import java.io.IOException
import kotlin.concurrent.thread
import kotlin.coroutines.CoroutineContext
import kotlin.system.measureTimeMillis


//fun main() {
//    val timeC = measureTimeMillis { coroutineT() }
//    println("coro ")
//    println(timeC)
//
//    val timeT = measureTimeMillis { threadT() }
//    println("thr ")
//    println(timeT)
//}
//
//fun coroutineT() = runBlocking {
//    var i = 0
//    repeat(100_000) { // launch 100_000 coroutines
//        launch (Dispatchers.Unconfined){
//            print(" ${i++} ")
//        }
//    }
//}
//
//fun threadT(){
//    var i = 0
//    repeat(100_000) { // 100
//        thread {
//            print(" ${i++} ")
//        }
//    }
//}

//fun main() {
//    Main().process()
//}
//
//class Main {
//    @OptIn(ObsoleteCoroutinesApi::class)
//    private val processContext = newFixedThreadPoolContext(4, name = "test")
//
//    fun process() {
//
//        runBlocking {
//            delay(2000)
//            println("FIRST")
//            while (true) {
//                println("LOOP")
//                launch {
//                    println("INNER THING")
//                }
//                launch {
//                    delay(500)
//                    println("INNER THING")
//                }
//                val blockResult = execute()
//
//                println("Block result $blockResult")
//            }
//        }
//    }
//
//    private fun execute() = runBlocking(processContext) {
//        Thread.sleep(1000) // simulating a slow command
//        1
//    }
//}

// join
//fun main() = runBlocking {
//    val job = GlobalScope.launch { // launch a new coroutine and keep a reference to its Job
//        delay(5000L)
//        println("World!")
//    }
//    println("Hello,")
//    job.join() // wait until child coroutine completes
//    println("Kotlin")
//}


//fun main() = runBlocking {
//    val startTime = System.currentTimeMillis()
//    val job = launch(Dispatchers.Default) {
//        var nextPrintTime = startTime
//        var i = 0
//        while (isActive) {
//            if (System.currentTimeMillis() >= nextPrintTime) {
//                println("job: I'm sleeping ${i++} ...")
//                nextPrintTime += 500L
//            }
//        }
//    }
//    delay(1300L) // delay a bit
//    println("main: I'm tired of waiting!")
//    job.cancel() // cancels the job
//    println("main: Now I can quit.")
//}

//fun main() = runBlocking {
//    val job = launch {
//        try {
//            repeat(1000) { i ->
//                println("I'm sleeping $i ...")
//                delay(500L)
//            }
//        } finally {
//            // Tranh thủ close resource trong này đi nha :D
//            println("I'm running finally")
//        }
//    }
//    delay(1300L) // delay a bit
//    println("main: I'm tired of waiting!")
//    job.cancel() // cancels the job
//    println("main: Now I can quit.")
//}


//fun main() = runBlocking {
//    val time = measureTimeMillis {
//        val one = async { printOne() }
//        val two = async { printTwo() }
//        println("The answer is ${one.await() + two.await()}")
//    }
//    println("Completed in $time ms")
//}
//
//suspend fun printOne(): Int {
//    delay(1000L)
//    return 10
//}
//
//suspend fun printTwo(): Int {
//    delay(1000L)
//    return 20
//}

//// lazy async
//fun main() = runBlocking {
//    val time = measureTimeMillis {
//        val one = async(start = CoroutineStart.LAZY) { printOne() }
//        val two = async(start = CoroutineStart.LAZY) { printTwo() }
////        one.start() // start the first one
////        two.start() // start the second one
//        println("The answer is ${one.await() + two.await()}")
//    }
//    println("Completed in $time ms")
//}
//
//suspend fun printOne(): Int {
//    delay(1000L)
//    return 10
//}
//
//suspend fun printTwo(): Int {
//    delay(1000L)
//    return 20
//}

//fun main() {
//    runBlocking {
//        val coroutine1 = launch {
//            delay(2000)
//            println("coroutine1")
//        }
//        val coroutine2 = launch {
//            delay(2000)
//            println("coroutine2")
//            val coroutine3 = launch {
//                delay(2000)
//                println("coroutine3")
//            }
//        }
//
//    }
//}

//fun main() = runBlocking<Unit> {
//    val request = launch {
//        // it spawns two other jobs, one with GlobalScope
//        GlobalScope.launch {
//            println("job1: GlobalScope and execute independently!")
//            delay(1000)
//            println("job1: I am not affected by cancellation")  // line code 1 này vẫn được in ra mặc dù bị delay 1000ms
//        }
//        // and the other inherits the parent context
//        launch {
//            delay(100)
//            println("job2: I am a child of the request coroutine")
//            delay(1000)
//            println("job2: I will not execute this line if my parent request is cancelled")
//        }
//    }
//    delay(500)
//    request.cancel() // cancel processing of the request
//    delay(1000) // delay a second to see what happens
//    println("main: Who has survived request cancellation?")
//}
//

//fun main() {
//    runBlocking {
//        GlobalScope.launch {
//            println("Throwing exception from launch")
//            throw IndexOutOfBoundsException()
//            println("Unreached")
//        }
//    }
//
//    val deferred = GlobalScope.async {
//        println("Throwing exception from async")
//        throw ArithmeticException()
//        println("Unreached")
//    }
//}

//fun main() = runBlocking {
//    GlobalScope.launch {
//        try {
//            println("Throwing exception from launch")
//            throw IndexOutOfBoundsException()
//            println("Unreached")
//        } catch (e: IndexOutOfBoundsException) {
//            println("Caught IndexOutOfBoundsException")
//        }
//    }
//
//    val deferred = GlobalScope.async {
//        println("Throwing exception from async")
//        throw ArithmeticException()
//        println("Unreached")
//    }
//    try {
//        deferred.await()
//        println("Unreached")
//    } catch (e: ArithmeticException) {
//        println("Caught ArithmeticException")
//    }
//}

//fun main() {
//    runBlocking {
//        val handler = CoroutineExceptionHandler { _, exception ->
//            println("Caught $exception")
//        }
//        val job = GlobalScope.launch(handler) {
//            throw AssertionError()
//        }
//        val deferred = GlobalScope.async(handler) {
//            throw ArithmeticException() // Nothing will be printed, relying on user to call deferred.await()
//        }
//        joinAll(job, deferred)
//    }
//}


//fun main() = runBlocking {
//    val handler = CoroutineExceptionHandler { _, exception ->
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            println("Caught $exception with suppressed ${exception.suppressed.contentToString()}")
//        }
//    }
//    val job = GlobalScope.launch(handler) {
//        launch {
//            try {
//                delay(Long.MAX_VALUE) // delay vô hạn
//            } finally {
//                throw ArithmeticException()
//            }
//        }
//        launch {
//            try {
//                delay(Long.MAX_VALUE) // delay vô hạn
//            } finally {
//                throw IndexOutOfBoundsException()
//            }
//        }
//        launch {
//            delay(100)
//            throw IOException()
//        }
//        delay(Long.MAX_VALUE)
//    }
//    job.join()
//}


//fun main() = runBlocking {
//    val supervisor = SupervisorJob()
//    with(CoroutineScope(coroutineContext + supervisor)) {
//        // launch the first child -- its exception is ignored for this example (don't do this in practice!)
//        val firstChild = launch(CoroutineExceptionHandler { _, _ ->  }) {
//            println("First child is failing")
//            throw AssertionError("First child is cancelled")
//        }
//        // launch the second child
//        val secondChild = launch {
//            firstChild.join()
//            // Cancellation of the first child is not propagated to the second child
//            println("First child is cancelled: ${firstChild.isCancelled}, but second one is still active")
//            try {
//                delay(Long.MAX_VALUE)
//            } finally {
//                // But cancellation of the supervisor is propagated
//                println("Second child is cancelled because supervisor is cancelled")
//            }
//        }
//        // wait until the first child fails & completes
//        firstChild.join()
//        println("Cancelling supervisor")
//        supervisor.cancel()
//        secondChild.join()
//    }
//}

//
//fun main() = runBlocking {
//    val handler = CoroutineExceptionHandler { _, exception ->
//        println("Caught $exception")
//    }
//    supervisorScope {
//        val first = launch(handler) {
//            println("Child throws an exception")
//            throw AssertionError()
//        }
//        val second = launch {
//            delay(100)
//            println("Scope is completing")
//        }
//    }
//    println("Scope is completed")
//    CoroutineScope(Dispatchers.IO)
//}
//
//fun main() {
//    Thread.sleep(1000000)
//    println("aaaaaaaaa")
//}

//        fun main() {
//
//            GlobalScope.launch {
//                delay(1000)
//                println("show A")
//            }
//            GlobalScope.launch {
//                delay(1000)
//                println("show B")
//            }
//
//
//
//
//
//    Thread.sleep(5000)
//}
//
//fun functionA() {
//    println("show A")
//}
//
//fun functionB() {
//    println("show B")
//}
//
//        suspend fun sayHello() {
//            delay(1000L)
//            println("Hello!")
//        }
//
//
//

fun main() {
    runBlocking {  }
    GlobalScope.launch {  }

}