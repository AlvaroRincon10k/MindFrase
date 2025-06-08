package com.example.mindfrase.data.repository

import com.example.mindfrase.data.network.FraseDao
import com.example.mindfrase.domain.model.Frase
import com.example.mindfrase.domain.repository.FraseRepository
import com.example.mindfrase.domain.repository.Frases
import kotlinx.coroutines.flow.Flow

class FraseRepositoryImpl(
    private val fraseDao: FraseDao
) : FraseRepository {
    override fun getFrasesFromRoom() = fraseDao.getFrases()

    override fun addFrasesToRoom(frase: Frase) = fraseDao.addFrase(frase)

    override fun deleteFraseFromRoom(frase: Frase) = fraseDao.deleteFrase(frase)

    override fun updateFraseInRoom(frase: Frase) = fraseDao.updateFrase(frase)

    override fun getFrasesFromRoom(id: Int) = fraseDao.getFrase(id)
}