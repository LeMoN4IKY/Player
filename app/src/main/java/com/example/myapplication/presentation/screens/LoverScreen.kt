package com.example.myapplication.presentation.screens

import android.R.attr.onClick
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.myapplication.domain.TrackDao
import com.example.myapplication.domain.TrackEntity
import com.example.myapplication.presentation.screens.components.playerScreeenButton
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch


// второстепенный экран с любимыми и сохраненными треками
// 20.10.2026
// Завалюев Артем Александрович
@Composable
fun Lover(songs: String, myDao: TrackDao, nameTrack: String, valueFun: (String) -> Unit) {
    val scope = rememberCoroutineScope()


    var chooseFilter by rememberSaveable { mutableStateOf("None") }
    val aidioPicker = rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent()) { uri: Uri? ->
        if (uri != null) {
            // Запускаем асинхронный фоновый поток для работы с базой данных
            scope.launch {
                // Собираем объект трека, автоматически подставляя заглушки под текст
                val newTrack = TrackEntity(
                    title = "Скачанный трек #${System.currentTimeMillis().toString().takeLast(4)}", // Даем уникальный номер, чтобы треки отличались
                    artist = "Локальный файл",
                    filelUri = uri, // Передаем тот самыйUri-адрес для ExoPlayer!
                    isFavorite = false
                )
                // Отправляем объект в базу Room через твою функцию del/insert
                myDao.insertTrack(newTrack)
                println("БАЗА ДАННЫХ: Трек успешно добавлен на жесткий диск!")
            }
        }
    }



    val Flow: Flow<List<TrackEntity>> = if (nameTrack.isNotEmpty()){
        myDao.searchTitleFun("%$nameTrack%")
    }else when(chooseFilter) {
        "like" -> myDao.getFavTracks()
       else -> myDao.getAllTracks()
    }

    val allTracks by Flow.collectAsState(initial = emptyList())




    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 5.dp)

    ) {

        OutlinedTextField(
            value = nameTrack,
            onValueChange = {valueFun(it)},
            label = {Text("Введите название песни: ")}
        )


        playerScreeenButton(
            onClick = { aidioPicker.launch("audio/*") },
            text = "Добавить песню",
            15
        )

        LazyColumn(
            modifier = Modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(5.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            items(allTracks) { odinTrack ->

                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {

                    Column(
                        verticalArrangement = Arrangement.spacedBy(2.dp),
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(odinTrack.title)
                        Text(odinTrack.artist)

                    }

                    Button(onClick = {
                        scope.launch {

                            val new = odinTrack.copy(isFavorite = !odinTrack.isFavorite)
                            myDao.updateTrack(new)
                            println("БАЗА ДАННЫХ: Статус избранного изменен на ${new.isFavorite}")


                        }
                    }, modifier = Modifier
                        .height(45.dp)
                        .width(60.dp)
                        .border(2.dp, Color.Red, RoundedCornerShape(15.dp))
                        .padding(3.dp),
                        shape = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.buttonColors(
                            contentColor = Color.White,
                            containerColor = Color.Red
                        )

                    )  { Text(if(odinTrack.isFavorite) "❤️" else "🖤")}

                    Button(
                        onClick = {
                            scope.launch {

                                myDao.del(track = odinTrack)


                            }
                        }, modifier = Modifier
                            .height(45.dp)
                            .width(60.dp)
                            .border(2.dp, Color.Red, RoundedCornerShape(15.dp))
                            .padding(3.dp),
                        shape = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.buttonColors(
                            contentColor = Color.White,
                            containerColor = Color.Red
                        )

                    ) {
                        Text("❌")
                    }
                }


            }
        }

    }
}
