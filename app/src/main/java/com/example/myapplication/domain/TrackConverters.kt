package com.example.myapplication.domain

import android.R.string.no
import android.net.Uri
import androidx.core.animation.TypeConverter
import androidx.core.net.toUri

class TrackConverters {
    @TypeConverter
    fun fromUri(uri: Uri?): String?{
        return uri?.toString()
    }

    fun toUri(uriString: String?): Uri?{
        return if(uriString!= null) Uri.parse(uriString) else null
    }

}