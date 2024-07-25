package de.eymers.matthias.jokerapichallenge.ui.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewDynamicColors
import androidx.compose.ui.tooling.preview.PreviewFontScale
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.sp
import de.eymers.matthias.jokerapichallenge.core.Constant.GENERAL_API_ERROR_MESSAGE

@Composable
fun GeneralErrorView(
    errorMessage: String?
) {
    val message = errorMessage ?: GENERAL_API_ERROR_MESSAGE

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = message,
            fontSize = 24.sp
        )
    }
}

@PreviewLightDark
@PreviewDynamicColors
@PreviewFontScale
@Preview(showBackground = true)
@Composable
fun GeneralErrorPreview() {
    GeneralErrorView(
        errorMessage = GENERAL_API_ERROR_MESSAGE
    )
}