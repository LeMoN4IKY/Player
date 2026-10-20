package com.example.myapplication.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


// второстепенный экран с любимыми и сохраненными треками
// 20.10.2026
// Завалюев Артем Александрович
@Composable
fun Lover(songs: String){
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(horizontal = 5.dp)

    ) {
        Spacer(Modifier.height(10.dp))
        Text("Ваши песни - $songs", color = Color.White)
    }


}