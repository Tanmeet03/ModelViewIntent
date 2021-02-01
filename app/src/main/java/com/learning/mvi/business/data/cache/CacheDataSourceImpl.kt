package com.learning.mvi.business.data.cache

import com.google.gson.Gson
import com.learning.mvi.framework.datasource.cache.FactDaoService
import com.learning.mvi.framework.datasource.cache.mapper.CacheMapper
import com.learning.mvi.framework.datasource.model.DbDataCacheEntity

class CacheDataSourceImpl
constructor(private val factDaoService : FactDaoService, private val cacheMapper : CacheMapper) :
		CacheDataSource {

	override suspend fun <T> insertCacheData(url : String, networkFact : T) : Long {
		val gson = Gson()
		val jsonString = gson.toJson(networkFact)

		val dbData = DbDataCacheEntity(requestUrl = url, data = jsonString, updatedAt = "0")
		return factDaoService.insertCacheData(url, dbData)
	}

	override suspend fun <T> getCacheData(url : String, classOfT : Class<T>) : T? {
		val dbData : DbDataCacheEntity = factDaoService.getCacheData(url)
		return try {
			val data = Gson().fromJson(dbData.data, classOfT)
			data as T
		} catch (e : Exception) {
			null
		}
	}

}
