package com.example.myapplication

import android.R.attr.label
import android.R.attr.onClick
import android.R.id.home
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.LocalActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.getValue
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.runtime.setValue
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.ui.theme.MyApplicationTheme
import java.nio.file.WatchEvent

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // setContent — это команда, которая говорит: "Отобрази на экране код Compose"
        setContent {
            MaterialTheme {
                Controller() // Наш вызов кастомного экрана
            }
        }
    }
}



@Composable
fun PlayerScreen(paddingValues: PaddingValues) {

    val songs = mutableListOf("Положение", "Группа крови", "Штиль")
    val artist = mutableListOf("Скриптонит", "Кино", "Ария")

    var play by remember { mutableStateOf(false) }
    val activity = LocalActivity.current

    var index by remember { mutableStateOf(0)}

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF121212))
            .padding(paddingValues)
            .statusBarsPadding()
            .padding(horizontal = 5.dp),
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Row(
            modifier = Modifier
                .clip(RoundedCornerShape(12.dp))
                .height(60.dp)
                .background(Color.Black)
                .fillMaxWidth()
                .border(width = 2.dp, color = Color.Black, shape = RoundedCornerShape(8.dp)),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center


        ) {
            Button( onClick ={ activity?.finish()},
                modifier = Modifier
                .weight(1f)
                .clip(RoundedCornerShape(8.dp))
                .background(Color.White)
                .border(width = 2.dp, color = Color.Gray, shape = RoundedCornerShape(8.dp)),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White,     // Цвет самой кнопки (фон)
                    contentColor = Color.Black        // Цвет текста и иконок внутри кнопки
                ))
            {
                Text(text = "Выход" , color = Color.Black)
            }

            Button( onClick ={},
                modifier = Modifier
                .weight(1f)
                .clip(RoundedCornerShape(8.dp))
                .background(Color.White)
                .border(width = 1.dp, color = Color.Gray, shape = RoundedCornerShape(8.dp)),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White,     // Цвет самой кнопки (фон)
                    contentColor = Color.Black        // Цвет текста и иконок внутри кнопки
                ))
            {
                Text(text = "Любимые треки" , color = Color.Black)
            }


        }
        Spacer(modifier = Modifier.height(30.dp))


        Box(modifier = Modifier
            .width(280.dp)
            .height(280.dp)
            .clip(shape = RoundedCornerShape(12.dp))
            .background(Color(0xFF8B5CF6))
        ){}

        Spacer(modifier = Modifier.height(24.dp))

        Text(text = songs[index], color = Color.White, fontSize = 24.sp)// fontSize - шрифт размер

        Spacer(modifier = Modifier.height(8.dp))

        Text(text = artist[index], color = Color.Gray , fontSize = 16.sp )

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
            Button( onClick ={ if (index > 0){
                index--
            }else{index = songs.size-1} },
                modifier = Modifier
                    .weight(1f)
                    .clip(RoundedCornerShape(15.dp))
                    .background(Color.White)
                    .border(width = 1.dp, color = Color.Gray, shape = RoundedCornerShape(15.dp)),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White,     // Цвет самой кнопки (фон)
                    contentColor = Color.Black        // Цвет текста и иконок внутри кнопки
                ))
            {
                Text(text = "<" , color = Color.Black)
            }

            Button( onClick = { play = !play},
                modifier = Modifier
                    .weight(1f)
                    .clip(RoundedCornerShape(50.dp))
                    .background(Color.White)
                    .border(width = 1.dp, color = Color.Gray, shape = RoundedCornerShape(50.dp)),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White,     // Цвет самой кнопки (фон)
                    contentColor = Color.Black        // Цвет текста и иконок внутри кнопки
                ))
            {
                Text(text = if (play)"STOP" else "PLAY", color = Color.Black)
            }

            Button( onClick ={ if (index < songs.size - 1){
                index++
            }else{ index = 0

            } },
                modifier = Modifier
                    .weight(1f)
                    .clip(RoundedCornerShape(15.dp))
                    .background(Color.White)
                    .border(width = 1.dp, color = Color.Gray, shape = RoundedCornerShape(15.dp)),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White,     // Цвет самой кнопки (фон)
                    contentColor = Color.Black        // Цвет текста и иконок внутри кнопки
                ))
            {
                Text(text = ">" , color = Color.Black)
            }
        }


    }

}


@Composable
fun lover(paddingValues: PaddingValues){
    Column(modifier = Modifier
        .fillMaxSize()
        .background(Color.Black)
        .padding(paddingValues)
        .statusBarsPadding()
        .padding(horizontal = 5.dp)

    ) { }


}

@Composable
fun Controller(){

    var current by rememberSaveable{ mutableStateOf("PlayerScreen")}
    Scaffold(
        bottomBar = {
            NavigationBar(containerColor = Color.Transparent) {
                NavigationBarItem(
                    selected = ( current == "PlayerScreen"),
                    onClick = {current = "PlayerScreen" },
                    icon = {Text("\uD83C\uDFE1")},
                    label = {Text("Главная")}
                )

                NavigationBarItem(
                    selected = ( current == "lover"),
                    onClick = {current = "lover" },
                    icon = {Text("❤\uFE0F")},
                    label = {Text("Избраное")}
                )
            }

        }
    ) { innerPadding ->

            if (current == "PlayerScreen"){
                PlayerScreen(paddingValues = innerPadding)
            } else if (current == "lover"){
                lover(paddingValues = innerPadding)
            }

    }
}

