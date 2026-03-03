package fi.developer.mvi_designpattern

sealed class CounterIntent {
    object Increment : CounterIntent()
    object Decrement : CounterIntent()
}