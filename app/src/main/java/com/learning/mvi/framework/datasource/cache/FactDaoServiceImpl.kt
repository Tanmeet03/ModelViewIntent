package com.learning.mvi.framework.datasource.cache

import com.learning.mvi.framework.datasource.cache.database.FactDao
import com.learning.mvi.framework.datasource.cache.model.FactCacheEntity
import com.learning.mvi.framework.datasource.model.DbDataCacheEntity

class FactDaoServiceImpl
constructor(private val factDao : FactDao) : FactDaoService {


	override suspend fun <T> insertCacheData(url : String, data : T) : Long {
		return insertOrUpdate(url, data)
	}

	override suspend fun getCacheData(url : String) : DbDataCacheEntity {
		return factDao.getCacheData(url)
	}

	private fun <T> insertOrUpdate(url : String, data : T) : Long {
		val rowId = factDao.getItemById(url)
		val dbDataCacheEntity = data as DbDataCacheEntity
		return if (rowId <= 0) {
			return factDao.insertData(dbDataCacheEntity)
		} else {
			dbDataCacheEntity.id = rowId
			factDao.update(dbDataCacheEntity)
			-1
		}
	}
}