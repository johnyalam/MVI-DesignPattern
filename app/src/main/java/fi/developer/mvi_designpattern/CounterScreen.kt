package fi.developer.mvi_designpattern

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun CounterScreen(viewModel: CounterViewModel = viewModel()) {
    val state by viewModel.counter.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Count: ${state.count}", style = MaterialTheme.typography.bodyLarge)
        Spacer(modifier = Modifier.height(16.dp), )
        Row() {
            Button(onClick = { viewModel.processIntent(CounterIntent.Increment) }) {
                Text(text = "+")
            }
            Spacer(modifier = Modifier.width(8.dp))
            Button(onClick = { viewModel.processIntent(CounterIntent.Decrement) }) {
                Text(text = "-")
            }
        }
    }
}