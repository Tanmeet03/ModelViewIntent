package com.learning.mvi.framework.datasource.cache

import com.learning.mvi.framework.datasource.cache.database.FactDao
import com.learning.mvi.framework.datasource.cache.model.FactCacheEntity

class FactDaoServiceImpl
constructor(private val factDao : FactDao) : FactDaoService {

	override suspend fun insert(factCacheEntity : FactCacheEntity) : Long {
		return factDao.insert(factCacheEntity)
	}

	override suspend fun get() : FactCacheEntity {
		return factDao.getFactData()
	}
}