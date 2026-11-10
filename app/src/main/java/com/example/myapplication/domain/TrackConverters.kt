package com.example.myapplication.domain

import android.net.Uri
import androidx.room3.ColumnTypeConverter

class TrackConverters {
    @ColumnTypeConverter
    fun fromUri(uri: Uri?): String? = uri?.toString()

    @ColumnTypeConverter
    fun toUri(uriString: String?): Uri? = uriString?.let(Uri::parse)
}
