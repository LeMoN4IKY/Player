package com.example.myapplication

import android.net.Uri
import androidx.room3.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.myapplication.domain.AppDatabase
import com.example.myapplication.domain.TrackConverters
import com.example.myapplication.domain.TrackEntity
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeout
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class TrackDatabaseTest {
    @Test
    fun nullableUriConvertersPreserveNull() {
        val converters = TrackConverters()
        assertNull(converters.fromUri(null))
        assertNull(converters.toUri(null))
    }

    @Test
    fun trackUriRoundTripsAndFavoriteUpdatesWithoutDuplicate() = runBlocking {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        val database = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).build()
        try {
            withTimeout(10_000) {
                val dao = database.trackdao()
                val uri = Uri.parse("content://media/external/audio/media/42?name=track%20one")
                dao.insertTrack(TrackEntity(title = "Test song", artist = "Artist", filelUri = uri))
                val saved = dao.getAllTracks().first().single()
                assertEquals(uri, saved.filelUri)
                assertTrue(saved.Id > 0)
                assertEquals(saved, dao.searchTitleFun("%Test%").first().single())

                val favorite = saved.copy(isFavorite = true)
                dao.updateTrack(favorite)
                assertEquals(listOf(favorite), dao.getAllTracks().first())
                assertEquals(listOf(favorite), dao.getFavTracks().first())

                dao.updateTrack(saved)
                assertTrue(dao.getFavTracks().first().isEmpty())
                dao.del(saved)
                assertTrue(dao.getAllTracks().first().isEmpty())
            }
        } finally {
            database.close()
        }
    }
}
