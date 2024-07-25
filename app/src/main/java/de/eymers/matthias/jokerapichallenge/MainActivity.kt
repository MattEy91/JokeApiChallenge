package de.eymers.matthias.jokerapichallenge

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import dagger.hilt.android.AndroidEntryPoint
import de.eymers.matthias.jokerapichallenge.navigation.NavigationHost
import de.eymers.matthias.jokerapichallenge.ui.theme.JokerApiChallengeTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JokerApiChallengeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    NavigationHost()
                }
            }
        }
    }
}