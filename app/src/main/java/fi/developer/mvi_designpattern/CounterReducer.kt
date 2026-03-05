package fi.developer.mvi_designpattern

class CounterReducer {
    fun reduce(currentState: CounterState, intent: CounterIntent): CounterState {
        return when (intent) {
            is CounterIntent.Increment -> currentState.copy(
                count = currentState.count + 1,
                error = null
            )
            is CounterIntent.Decrement -> currentState.copy(
                count = currentState.count - 1,
                error = null
            )
            is CounterIntent.Reset -> currentState.copy(
                count = 0,
                error = null
            )
            is CounterIntent.LoadAsync -> currentState.copy(
                isLoading = true,
                error = null
            )
        }
    }
}