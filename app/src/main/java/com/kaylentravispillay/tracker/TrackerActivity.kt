package com.kaylentravispillay.tracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.kaylentravispillay.core.ui.theme.TrackerTheme

class TrackerActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TrackerTheme {
                TrackerRoot()
            }
        }
    }
}

@Composable
private fun TrackerRoot() {
    Scaffold { contentPadding ->
        Text(
            modifier = Modifier.padding(contentPadding),
            text = "Hello, World",
            style = TrackerTheme.Typography.numericLarge
        )
    }
}
