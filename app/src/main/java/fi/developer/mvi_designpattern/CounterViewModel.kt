package fi.developer.mvi_designpattern

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class CounterViewModel: ViewModel() {
    private val _counter = MutableStateFlow(CounterState())
    val counter : StateFlow<CounterState> = _counter

    fun processIntent(intent: CounterIntent) {
        when (intent) {
            is CounterIntent.Increment -> _counter.update { currentState->
                currentState.copy(count = currentState.count + 1)
            }
            is CounterIntent.Decrement -> _counter.update { currentState->
                currentState.copy(count = currentState.count - 1)
            }
            }
        }

}