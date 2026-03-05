package fi.developer.mvi_designpattern

data class CounterState (
    val count: Int = 0,
    val isLoading: Boolean = false,
    val error: String? = null
)