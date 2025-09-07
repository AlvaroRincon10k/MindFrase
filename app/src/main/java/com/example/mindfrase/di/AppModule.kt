package com.example.mindfrase.di

import android.content.Context
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
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

@Module
@InstallIn(SingletonComponent::class)
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
        .addMigrations(MIGRATION_1_2)
        .build()

    private val MIGRATION_1_2 = object : Migration(1, 2) {
        override fun migrate(database: SupportSQLiteDatabase) {
            database.execSQL(
                "ALTER TABLE $FRASE_TABLE " +
                        "ADD COLUMN categoria TEXT NOT NULL DEFAULT 'General'"
            )
        }
    }

    @Provides
    fun provideFraseDao(fraseDB: FraseDB) = fraseDB.fraseDao()

    @Provides
    fun provideFraseRepository(fraseDao: FraseDao): FraseRepository = FraseRepositoryImpl(fraseDao)
}
