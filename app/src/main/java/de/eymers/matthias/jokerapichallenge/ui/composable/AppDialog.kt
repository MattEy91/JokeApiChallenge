package de.eymers.matthias.jokerapichallenge.ui.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import de.eymers.matthias.jokerapichallenge.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppDialog(
    message: String,
    onDismissRequest: () -> Unit
) {
    MaterialTheme {
        BasicAlertDialog(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .padding(16.dp),
            onDismissRequest = { onDismissRequest() }
        ) {
            Column(
                modifier = Modifier
                    .background(color = Color.White)
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = message)
                Spacer(modifier = Modifier.padding(top = 24.dp))
                Button(onClick = { onDismissRequest() }) {
                    Text(text = stringResource(id = R.string.answer_dialog_button_text))
                }
            }
        }
    }

}
