package com.example.myapplication.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.presentation.screens.components.playerScreeenButton


// Главный экран с треками
//20.10.2026
// Завалюев Артем Александрович

@Composable
fun PlayerScreen(songs: String, artist: String, onNextClick: () -> Unit, onPrevClick: () -> Unit) {

    var play by remember { mutableStateOf(false) }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 5.dp),
        horizontalAlignment = Alignment.CenterHorizontally

    ) {

        Spacer(modifier = Modifier.height(30.dp))


        Box(modifier = Modifier
            .width(280.dp)
            .height(280.dp)
            .clip(shape = RoundedCornerShape(12.dp))
            .background(Color(0xFF8B5CF6))
        ){}

        Spacer(modifier = Modifier.height(24.dp))

        Text(songs, color = Color.White, fontSize = 24.sp)// fontSize - шрифт размер

        Spacer(modifier = Modifier.height(8.dp))

        Text(artist, color = Color.Gray , fontSize = 16.sp )

        Spacer(modifier = Modifier.height(40.dp))



        Box( modifier = Modifier
            .fillMaxWidth()
            .height(4.dp)
            .clip(RoundedCornerShape(2.dp))
            .background(Color.DarkGray)
        )
        { Box(
            modifier = Modifier
                // 1. Делаем её круглой (ширина и высота одинаковые, например 10.dp)
                .width(10.dp)
                .height(10.dp)
                .clip(RoundedCornerShape(5.dp)) // Скругление в половину размера делает идеальный круг
                .background(Color.White)

                // 2. Личный приказ для точки внутри Box: встань по центру вертикали!
                .align(Alignment.CenterStart)){} // ?????
        }

        Spacer(modifier = Modifier.height(2.dp))

        Row( modifier = Modifier.fillMaxWidth(),horizontalArrangement = Arrangement.SpaceBetween//SpaceBetween максимальный отступ
        ) {
            Text(text = "0:42", color = Color.Gray, fontSize = 12.sp)

            Text(text = "3:15", color = Color.Gray, fontSize = 12.sp)
        }

        Spacer(modifier = Modifier.height(10.dp))







        Row( modifier = Modifier
            .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(24.dp),
            verticalAlignment = Alignment.CenterVertically)
        {

            playerScreeenButton(onClick = {onPrevClick()}, text = "<", sizeClip = 15, Modifier.weight(1f)) // кнопка назад

            playerScreeenButton(onClick = {play = !play}, text = if (play)"STOP" else "PLAY", sizeClip = 50, Modifier.weight(1f)) // кнопка старт стоп

            playerScreeenButton(onClick = {onNextClick()}, text = ">", sizeClip = 15, Modifier.weight(1f)) // кнопка вперед


        }


    }

}