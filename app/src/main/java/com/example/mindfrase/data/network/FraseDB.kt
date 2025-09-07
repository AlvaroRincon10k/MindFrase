package com.example.mindfrase.data.network

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.mindfrase.domain.model.Frase

@Database(entities = [Frase::class], version = 2)
abstract class FraseDB : RoomDatabase() {
    abstract fun fraseDao(): FraseDao
}