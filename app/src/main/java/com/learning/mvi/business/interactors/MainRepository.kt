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
			cacheDataSource.insertCacheData("unique_to_store_in_db_probably_url",networkFact)
			val cachedFact = cacheDataSource.getCacheData("unique_to_store_in_db_probably_url",FactModel::class.java)
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

			emit(DataState.Success(networkFact))
		} catch (e : Exception) {
			emit(DataState.Error(e))
		}
	}

	suspend fun getRandomFact() : Flow<DataState<FactModel>> = flow {
		emit(DataState.Loading)
		try {
			val networkFact = networkDataSource.getRandomTrivia()

			emit(DataState.Success(networkFact))
		} catch (e : Exception) {
			emit(DataState.Error(e))
		}
	}

	suspend fun getTriviaFact(number : Long) : Flow<DataState<FactModel>> = flow {
		emit(DataState.Loading)
		try {
			val networkFact = networkDataSource.getTriviaFact(number)

			emit(DataState.Success(networkFact))
		} catch (e : Exception) {
			emit(DataState.Error(e))
		}
	}

	suspend fun getYearFact(number : Long) : Flow<DataState<FactModel>> = flow {
		emit(DataState.Loading)
		try {
			val networkFact = networkDataSource.getYearTrivia(number)
			emit(DataState.Success(networkFact))
		} catch (e : Exception) {
			emit(DataState.Error(e))
		}
	}
}
