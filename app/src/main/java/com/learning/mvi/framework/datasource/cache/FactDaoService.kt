package com.learning.mvi.framework.datasource.cache

import com.learning.mvi.framework.datasource.model.DbDataCacheEntity


interface FactDaoService {

	suspend fun <T> insertCacheData(url : String, dataCacheEntity : T) : Long

	suspend fun getCacheData(url : String) : DbDataCacheEntity

}