package com.learning.mvi.framework.datasource.cache

import com.learning.mvi.framework.datasource.cache.model.FactCacheEntity


interface FactDaoService {

    suspend fun insert(factEntity: FactCacheEntity): Long

    suspend fun get(): FactCacheEntity

}