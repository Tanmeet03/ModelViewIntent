package com.learning.mvi.business.data.cache

import com.learning.mvi.business.domain.model.FactModel
import com.learning.mvi.framework.datasource.cache.FactDaoService
import com.learning.mvi.framework.datasource.cache.mapper.CacheMapper

class CacheDataSourceImpl
constructor(private val factDaoService : FactDaoService, private val cacheMapper : CacheMapper) :
		CacheDataSource {

	override suspend fun insert(fact : FactModel) : Long {
		return factDaoService.insert(cacheMapper.mapToEntity(fact))
	}

	override suspend fun get() : FactModel {
		return cacheMapper.mapFromEntity(factDaoService.get())
	}

}
