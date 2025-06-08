package com.example.mindfrase.di

import android.content.Context
import androidx.room.Room
import com.example.mindfrase.core.Constants.Companion.FRASE_TABLE
import com.example.mindfrase.data.network.FraseDB
import com.example.mindfrase.data.network.FraseDao
import com.example.mindfrase.data.repository.FraseRepositoryImpl
import com.example.mindfrase.domain.repository.FraseRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent:: class)
class AppModule {
    @Provides
    fun provideFraseDB(
        @ApplicationContext
        context: Context
    ) = Room.databaseBuilder(
        context,
        FraseDB::class.java,
        FRASE_TABLE
    )
        .build()

    @Provides
    fun provideFraseDao(fraseDB: FraseDB) = fraseDB.fraseDao()

    @Provides
    fun provideFraseRepository(fraseDao: FraseDao): FraseRepository = FraseRepositoryImpl(fraseDao)
}