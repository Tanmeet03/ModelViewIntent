package com.learning.mvi.framework.datasource.cache.mapper

import com.learning.mvi.business.domain.model.FactModel
import com.learning.mvi.business.domain.util.EntityMapper
import com.learning.mvi.framework.datasource.cache.model.FactCacheEntity
import javax.inject.Inject

class CacheMapper
@Inject constructor() : EntityMapper<FactCacheEntity, FactModel> {
	override fun mapFromEntity(entity : FactCacheEntity) : FactModel {
		return FactModel(id = 0, text = entity.info, year = entity.year, number = entity.numberInput, found = entity.isDataAvailable, type = entity.type)
	}

	override fun mapToEntity(domainModel : FactModel) : FactCacheEntity {
		return FactCacheEntity(id =0, info = domainModel.text, year = domainModel.year, numberInput = domainModel.number, isDataAvailable = domainModel.found, type = domainModel.type)
	}
}