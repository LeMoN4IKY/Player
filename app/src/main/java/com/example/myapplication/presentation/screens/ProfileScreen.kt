package com.example.myapplication.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


//Экран профиля, где можно создать аккаунт
//20.10.2026
// Завалюев Артем Александрович
@Composable
fun Profile(){
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(horizontal = 5.dp)

    ) {
        Text("Ваш профиль", color = Color.White)
    }


}