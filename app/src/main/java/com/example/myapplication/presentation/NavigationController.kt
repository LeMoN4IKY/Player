package com.example.myapplication.presentation

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.domain.AppDatabase
import com.example.myapplication.domain.getNextIndex
import com.example.myapplication.domain.getPrevIndex
import com.example.myapplication.domain.globalArtists
import com.example.myapplication.domain.globalSongs
import com.example.myapplication.presentation.screens.Lover
import com.example.myapplication.presentation.screens.PlayerScreen
import com.example.myapplication.presentation.screens.Profile
import com.example.myapplication.presentation.screens.components.playerScreeenButton


// контроллер , который управляет экранами
//20.10.2026
// Завалюев Артем Александрович
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Controller(database: AppDatabase){


    var index by remember { mutableStateOf(0)}


    val controller = rememberNavController()

    val step1current by controller.currentBackStackEntryAsState()
    val currentScreen = step1current?.destination?.route

    Scaffold(
        topBar = { Column() // что бы засунуть разделительную линию
        {
            TopAppBar(
                title = {Text("Плеер", color = Color.White)},
                actions = {
                    playerScreeenButton(onClick = {controller.navigate("Profile")}, text = "Профиль", sizeClip = 8, Modifier // кнопка профиль
                        .width(120.dp)
                        .border(1.dp, Color.White, RoundedCornerShape(13.dp))
                        .padding(5.dp)
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Transparent
                )
            )
            Spacer(Modifier.height(5.dp))

            HorizontalDivider( // разделительная линия
                thickness = 1.dp,
                color = Color.DarkGray
            )
        }
        },
        bottomBar = {
            NavigationBar(containerColor = Color.Transparent) {
                NavigationBarItem(
                    selected = ( currentScreen == "PlayerScreen"),
                    onClick = {controller.navigate("PlayerScreen") },
                    icon = {Text("\uD83C\uDFE1")},
                    label = {Text("Главная", color = Color.White)}
                )

                NavigationBarItem(
                    selected = ( currentScreen == "Lover"),
                    onClick = {controller.navigate("Lover/$globalSongs") },
                    icon = {Text("❤\uFE0F")},
                    label = {Text("Избраное", color = Color.White)}
                )
            }

        }, containerColor = Color(0xFF121212)
    ) { innerPadding ->
        NavHost(
            navController = controller,
            startDestination = "PlayerScreen",
            modifier = Modifier.padding(innerPadding)
        ){
            composable(route = "PlayerScreen"){
                PlayerScreen(
                    songs = globalSongs[index],
                    artist = globalArtists[index],
                    onNextClick =
                        {
                            index = getNextIndex(index, globalSongs.size)
                        },
                    onPrevClick = {
                        index = getPrevIndex(currentIndex = index, listSize = globalSongs.size)
                    }
                )

            }

            composable(route = "Lover/{songs}"){ save ->
                val songs = save?.arguments?.getString("songs") ?: ""
                Lover(songs = songs)
            }

            composable(route = "Profile"){
                Profile()
            }
        }

    }
}

