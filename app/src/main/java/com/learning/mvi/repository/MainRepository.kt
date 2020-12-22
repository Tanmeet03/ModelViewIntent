package com.learning.mvi.repository

import com.learning.mvi.db.CacheMapper
import com.learning.mvi.db.database.FactDao
import com.learning.mvi.model.FactModel
import com.learning.mvi.network.FactApi
import com.learning.mvi.network.NetworkMapper
import com.learning.mvi.util.DataState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MainRepository
constructor(private val factDao : FactDao, private val factApi : FactApi,
            private val cacheMapper : CacheMapper, private val networkMapper : NetworkMapper) {

	suspend fun getDateFact(month : Int, date : Int) : Flow<DataState<FactModel>> = flow {
		emit(DataState.Loading)
		try {
			//retrieve from network
			val networkFact = factApi.getDateTrivia(month, date)
			val dateFact = networkMapper.mapFromEntity(networkFact)
			//sent to the cache
			factDao.insert(cacheMapper.mapToEntity(dateFact))
			//retrieve from the cache
			val cacheFact = factDao.getFactData()
			emit(DataState.Success(dateFact))
		} catch (e : Exception) {
			emit(DataState.Error(e))
		}
	}

	suspend fun getMathFact(number : Long) : Flow<DataState<FactModel>> = flow {
		emit(DataState.Loading)
		try {
			//retrieve from network
			val networkFact = factApi.getMathTrivia(number)
			val dateFact = networkMapper.mapFromEntity(networkFact)
			//sent to the cache
			factDao.insert(cacheMapper.mapToEntity(dateFact))
			//retrieve from the cache
			val cacheFact = factDao.getFactData()
			emit(DataState.Success(dateFact))
		} catch (e : Exception) {
			emit(DataState.Error(e))
		}
	}

	suspend fun getRandomFact() : Flow<DataState<FactModel>> = flow {
		emit(DataState.Loading)
		try {
			//retrieve from network
			val networkFact = factApi.getRandomTrivia()
			val dateFact = networkMapper.mapFromEntity(networkFact)
			//sent to the cache
			factDao.insert(cacheMapper.mapToEntity(dateFact))
			//retrieve from the cache
			val cacheFact = factDao.getFactData()
			emit(DataState.Success(dateFact))
		} catch (e : Exception) {
			emit(DataState.Error(e))
		}
	}

	suspend fun getTriviaFact(number : Long) : Flow<DataState<FactModel>> = flow {
		emit(DataState.Loading)
		try {
			//retrieve from network
			val networkFact = factApi.getTrivia(number)
			val dateFact = networkMapper.mapFromEntity(networkFact)
			//sent to the cache
			factDao.insert(cacheMapper.mapToEntity(dateFact))
			//retrieve from the cache
			val cacheFact = factDao.getFactData()
			emit(DataState.Success(dateFact))
		} catch (e : Exception) {
			emit(DataState.Error(e))
		}
	}

	suspend fun getYearFact(number : Long) : Flow<DataState<FactModel>> = flow {
		emit(DataState.Loading)
		try {
			//retrieve from network
			val networkFact = factApi.getYearTrivia(number)
			val dateFact = networkMapper.mapFromEntity(networkFact)
			//sent to the cache
			factDao.insert(cacheMapper.mapToEntity(dateFact))
			//retrieve from the cache
			val cacheFact = factDao.getFactData()
			emit(DataState.Success(dateFact))
		} catch (e : Exception) {
			emit(DataState.Error(e))
		}
	}

}
