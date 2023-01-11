package com.mungaicodes.tomesanctuary.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.mungaicodes.tomesanctuary.data.mapper.DataConverter

@Database(entities = [BookEntity::class], version = 1)
@TypeConverters(DataConverter::class)
abstract class TomeSanctuaryDatabase : RoomDatabase() {
    abstract val dao: TomeSanctuaryDao
}