package com.example.myapplication.presentation.screens.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


// кастомный DRY компонент отвечает за кнопки переключения треков и старта
// 20.10.2026
// Завалюев Артем Александрович
@Composable
fun playerScreeenButton(onClick: () -> Unit, text: String, sizeClip: Int, modifier: Modifier = Modifier){ // = - на всякий если пользователь ничего
    // не напишет просто оставим Modifier пустым
    Button( onClick ={ onClick() }, // разный функционал по нажатию
        modifier = modifier // делаем возможным прописывать разные модификаторы для разных кнопок
            .height(45.dp),
        border = BorderStroke(1.dp, Color.Gray),
        shape = RoundedCornerShape(sizeClip.dp), // разное скругление
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.White,     // Цвет самой кнопки (фон)
            contentColor = Color.Black        // Цвет текста и иконок внутри кнопки
        ))
    {
        Text(text = text , color = Color.Black) // разный текст
    }
}