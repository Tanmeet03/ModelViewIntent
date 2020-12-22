package com.learning.mvi.framework.datasource.network

import com.learning.mvi.framework.datasource.network.model.FactNetworkEntity

interface FactRetrofitService {

	suspend fun getDateFact(month : Int, date : Int) : FactNetworkEntity
	suspend fun getMathFact(number : Long) : FactNetworkEntity
	suspend fun getRandomFact() : FactNetworkEntity
	suspend fun getTriviaFact(number : Long) : FactNetworkEntity
	suspend fun getYearFact(number : Long) : FactNetworkEntity
}