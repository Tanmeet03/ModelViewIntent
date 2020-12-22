package com.learning.mvi.business.data.network

import com.learning.mvi.business.domain.model.FactModel
import com.learning.mvi.framework.datasource.network.FactRetrofitService
import com.learning.mvi.framework.datasource.network.mapper.NetworkMapper

class NetworkDataSourceImpl
constructor(private val factRetrofitService : FactRetrofitService,
            private val networkMapper : NetworkMapper) : NetworkDataSource {

	override suspend fun getDateFact(month : Int, date : Int) : FactModel {
		return networkMapper.mapFromEntity(factRetrofitService.getDateFact(month, date))
	}

	override suspend fun getMathTrivia(number : Long) : FactModel {
		return networkMapper.mapFromEntity(factRetrofitService.getMathFact(number))
	}

	override suspend fun getRandomTrivia() : FactModel {
		return networkMapper.mapFromEntity(factRetrofitService.getRandomFact())
	}

	override suspend fun getTriviaFact(number : Long) : FactModel {
		return networkMapper.mapFromEntity(factRetrofitService.getTriviaFact(number))
	}

	override suspend fun getYearTrivia(number : Long) : FactModel {
		return networkMapper.mapFromEntity(factRetrofitService.getYearFact(number))
	}
}