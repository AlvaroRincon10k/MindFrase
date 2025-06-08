package com.example.mindfrase.data.network

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.mindfrase.core.Constants.Companion.FRASE_TABLE
import com.example.mindfrase.domain.model.Frase
import com.example.mindfrase.domain.repository.Frases
import kotlinx.coroutines.flow.Flow

@Dao
interface FraseDao {
    @Query("SELECT * FROM $FRASE_TABLE ORDER BY id ASC")
    fun getFrases(): Flow<Frases>

    @Insert
    fun addFrase(frase: Frase)

    @Delete
    fun deleteFrase(frase: Frase)

    @Update
    fun updateFrase(frase: Frase)

    @Query("SELECT * FROM $FRASE_TABLE WHERE id = :id")
    fun getFrase(id: Int): Frase
}