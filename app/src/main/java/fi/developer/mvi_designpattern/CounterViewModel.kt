package fi.developer.mvi_designpattern

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CounterViewModel: ViewModel() {
    private val reducer = CounterReducer()

    private val _state = MutableStateFlow(CounterState())
    val state: StateFlow<CounterState> = _state.asStateFlow()

    fun processIntent(intent: CounterIntent) {
        when (intent) {
            is CounterIntent.LoadAsync -> handleAsyncOperation()
            else -> {
                val currentState = _state.value
                val newState = reducer.reduce(currentState, intent)
                _state.value = newState
            }
        }
    }

    private fun handleAsyncOperation() {
        viewModelScope.launch {
            _state.value = reducer.reduce(_state.value, CounterIntent.LoadAsync)

            try {
                delay(2000) // Simulate network call
                _state.value = _state.value.copy(
                    count = _state.value.count + 10,
                    isLoading = false
                )
            } catch (e: Exception) {
                _state.value = _state.value.copy(
                    isLoading = false,
                    error = e.message
                )
            }
        }
    }
}