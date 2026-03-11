package fi.developer.baselibrary.coroutine

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * Examples covering Coroutine Scopes, Dispatchers, Suspend functions, and Async/Await.
 */
class CoroutineExamples {

    // 1. CoroutineScope: Defines the lifecycle of coroutines.
    // In Android, you usually use viewModelScope or lifecycleScope.
    private val scope = CoroutineScope(Dispatchers.Main + Job())

    /**
     * Demonstrates 'launch' with different Dispatchers.
     * 'launch' is "fire and forget" - it returns a Job and doesn't return a result.
     */
    fun demonstrateDispatchers() {
        // Dispatchers.Main: For UI operations
        scope.launch(Dispatchers.Main) {
            println("Running on Main thread: ${Thread.currentThread().name}")
        }

        // Dispatchers.IO: Optimized for disk or network I/O
        scope.launch(Dispatchers.IO) {
            println("Running on IO thread: ${Thread.currentThread().name}")
            performNetworkCall() // Calling a suspend function
        }

        // Dispatchers.Default: Optimized for CPU-intensive work (sorting, parsing)
        scope.launch(Dispatchers.Default) {
            println("Running on Default thread: ${Thread.currentThread().name}")
            calculateHeavySum()
        }
    }

    /**
     * 2. Suspend Function: A function that can be paused and resumed.
     * It must be called from a coroutine or another suspend function.
     */
    private suspend fun performNetworkCall(): String {
        delay(1000) // Non-blocking delay
        return "Data from Network"
    }

    private suspend fun calculateHeavySum() {
        var sum = 0L
        for (i in 1..1_000_000L) sum += i
        println("Sum calculated: $sum")
    }

    /**
     * 3. Async/Await: Used when you need a result back from the coroutine.
     * Allows running multiple tasks concurrently.
     */
    fun demonstrateAsyncAwait() {
        scope.launch {
            println("Starting concurrent tasks...")

            // Start two tasks in parallel
            val deferredOne = async(Dispatchers.IO) {
                fetchFirstPart()
            }
            val deferredTwo = async(Dispatchers.IO) {
                fetchSecondPart()
            }

            // 'await()' suspends until the result is ready
            val result = deferredOne.await() + " " + deferredTwo.await()

            println("Combined Result: $result")
        }
    }

    private suspend fun fetchFirstPart(): String {
        delay(500)
        return "Hello"
    }

    private suspend fun fetchSecondPart(): String {
        delay(500)
        return "World"
    }

    /**
     * Clean up the scope when the class/component is destroyed to prevent memory leaks.
     */
    fun clear() {
        scope.cancel()
    }
}