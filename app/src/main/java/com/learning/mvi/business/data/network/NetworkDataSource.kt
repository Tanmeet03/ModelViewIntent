package com.learning.mvi.business.data.network

import com.learning.mvi.business.domain.model.FactModel


interface NetworkDataSource {

    suspend fun getDateFact(month : Int, date : Int) : FactModel
    suspend fun getMathTrivia(number : Long) : FactModel
    suspend  fun getRandomTrivia() : FactModel
    suspend  fun getTriviaFact(number : Long) : FactModel
    suspend fun getYearTrivia(number : Long) : FactModel
}