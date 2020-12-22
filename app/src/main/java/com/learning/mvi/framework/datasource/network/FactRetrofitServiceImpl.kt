package com.learning.mvi.framework.datasource.network

import com.learning.mvi.framework.datasource.network.api.FactApi
import com.learning.mvi.framework.datasource.network.model.FactNetworkEntity

class FactRetrofitServiceImpl
constructor(private val factApi : FactApi) : FactRetrofitService {

	override suspend fun getDateFact(month : Int, date : Int) : FactNetworkEntity {
		return factApi.getDateTrivia(month, date)
	}

	override suspend fun getMathFact(number : Long) : FactNetworkEntity {
		return factApi.getMathTrivia(number)
	}

	override suspend fun getRandomFact() : FactNetworkEntity {
		return factApi.getRandomTrivia()
	}

	override suspend fun getTriviaFact(number : Long) : FactNetworkEntity {
		return factApi.getTrivia(number)
	}

	override suspend fun getYearFact(number : Long) : FactNetworkEntity {
		return factApi.getYearTrivia(number)
	}
}