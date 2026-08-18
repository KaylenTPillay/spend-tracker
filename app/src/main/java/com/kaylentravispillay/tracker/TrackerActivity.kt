package com.kaylentravispillay.tracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import com.kaylentravispillay.core.ui.theme.TrackerTheme
import com.kaylentravispillay.feature.categories.ui.component.screen.CategoriesScreenRoot
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TrackerActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TrackerTheme {
                TrackerRoot {
                    onBackPressedDispatcher.onBackPressed()
                }
            }
        }
    }
}

@Composable
private fun TrackerRoot(onBackNav: () -> Unit) {
    CategoriesScreenRoot(onNavigationIconClick = onBackNav)
}
