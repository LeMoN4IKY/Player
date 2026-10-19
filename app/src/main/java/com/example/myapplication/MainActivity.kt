package com.example.myapplication

import android.R.attr.label
import android.R.attr.onClick
import android.R.id.home
import android.os.Bundle
import android.util.Log.i
import androidx.activity.ComponentActivity
import androidx.activity.compose.LocalActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key.Companion.Home
import androidx.compose.ui.text.font.FontVariation.weight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.ui.theme.MyApplicationTheme
import java.nio.file.Files.size
import java.nio.file.WatchEvent

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // setContent — это команда, которая говорит: "Отобрази на экране код Compose"
        enableEdgeToEdge()
        setContent {
            MaterialTheme {
                Controller() // Наш вызов кастомного экрана
            }
        }
    }
}


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

@Composable
fun Profile(){
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(horizontal = 5.dp)

    ) {
        Text("Ваш профиль", color = Color.White)
    }


}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Controller(){

    val songs = mutableListOf("Положение", "Группа крови", "Штиль")
    val artist = mutableListOf("Скриптонит", "Кино", "Ария")
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
                    onClick = {controller.navigate("Lover/$songs") },
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
                PlayerScreen(songs = songs[index], artist = artist[index], onNextClick =
                    { if (index < songs.size - 1){
                    index++ }
                    else {index = 0}
                    }, onPrevClick = {if (index > 0) index-- else index = songs.size - 1 })
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

