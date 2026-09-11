package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.example.model.CrewMember
import com.example.model.CrewRoster
import com.example.ui.screens.DossierScreen
import com.example.ui.screens.FloorScreen
import com.example.ui.theme.MyApplicationTheme

sealed interface Screen {
    data object Floor : Screen
    data class Dossier(val crew: CrewMember) : Screen
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .statusBarsPadding(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    SpriteStationApp()
                }
            }
        }
    }
}

@Composable
fun SpriteStationApp() {
    var currentScreen by remember { mutableStateOf<Screen>(Screen.Floor) }

    // Intercept back button when in dossier screen
    BackHandler(enabled = currentScreen !is Screen.Floor) {
        currentScreen = Screen.Floor
    }

    AnimatedContent(
        targetState = currentScreen,
        transitionSpec = {
            if (targetState is Screen.Dossier) {
                (slideInHorizontally { width -> width / 3 } + fadeIn()) togetherWith
                        (slideOutHorizontally { width -> -width / 3 } + fadeOut())
            } else {
                (slideInHorizontally { width -> -width / 3 } + fadeIn()) togetherWith
                        (slideOutHorizontally { width -> width / 3 } + fadeOut())
            }
        },
        label = "screen_transition"
    ) { screen ->
        when (screen) {
            is Screen.Floor -> {
                FloorScreen(
                    onSelectCrew = { selected ->
                        currentScreen = Screen.Dossier(selected)
                    }
                )
            }
            is Screen.Dossier -> {
                DossierScreen(
                    crew = screen.crew,
                    onBackToFloor = {
                        currentScreen = Screen.Floor
                    }
                )
            }
        }
    }
}

