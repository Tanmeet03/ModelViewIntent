package com.learning.mvi.business.data.cache

interface CacheDataSource {

	suspend fun <T> insertCacheData(homePageData : String, networkFact : T) : Long

	suspend fun <T> getCacheData(url : String, classOfT : Class<T>) : T?
}