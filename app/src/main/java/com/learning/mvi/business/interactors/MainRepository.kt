package com.learning.mvi.business.interactors

import com.learning.mvi.business.data.cache.CacheDataSource
import com.learning.mvi.business.data.network.NetworkDataSource
import com.learning.mvi.business.domain.model.FactModel
import com.learning.mvi.business.domain.state.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MainRepository
constructor(private val cacheDataSource : CacheDataSource,
            private val networkDataSource : NetworkDataSource) {

	suspend fun getDateFact(month : Int, date : Int) : Flow<DataState<FactModel>> = flow {
		emit(DataState.Loading)
		try {
			//retrieve from network
			val networkFact = networkDataSource.getDateFact(month, date)
			cacheDataSource.insert(networkFact)
			val cachedFact = cacheDataSource.get()
			emit(DataState.Success(networkFact))
		} catch (e : Exception) {
			emit(DataState.Error(e))
		}
	}

	suspend fun getMathFact(number : Long) : Flow<DataState<FactModel>> = flow {
		emit(DataState.Loading)
		try {
			//retrieve from network
			val networkFact = networkDataSource.getMathTrivia(number)
			cacheDataSource.insert(networkFact)
			val cachedFact = cacheDataSource.get()

			emit(DataState.Success(networkFact))
		} catch (e : Exception) {
			emit(DataState.Error(e))
		}
	}

	suspend fun getRandomFact() : Flow<DataState<FactModel>> = flow {
		emit(DataState.Loading)
		try {
			val networkFact = networkDataSource.getRandomTrivia()
			cacheDataSource.insert(networkFact)
			val cachedFact = cacheDataSource.get()

			emit(DataState.Success(networkFact))
		} catch (e : Exception) {
			emit(DataState.Error(e))
		}
	}

	suspend fun getTriviaFact(number : Long) : Flow<DataState<FactModel>> = flow {
		emit(DataState.Loading)
		try {
			val networkFact = networkDataSource.getTriviaFact(number)
			cacheDataSource.insert(networkFact)
			val cachedFact = cacheDataSource.get()

			emit(DataState.Success(networkFact))
		} catch (e : Exception) {
			emit(DataState.Error(e))
		}
	}

	suspend fun getYearFact(number : Long) : Flow<DataState<FactModel>> = flow {
		emit(DataState.Loading)
		try {
			val networkFact = networkDataSource.getYearTrivia(number)
			cacheDataSource.insert(networkFact)
			val cachedFact = cacheDataSource.get()
			emit(DataState.Success(networkFact))
		} catch (e : Exception) {
			emit(DataState.Error(e))
		}
	}

}
