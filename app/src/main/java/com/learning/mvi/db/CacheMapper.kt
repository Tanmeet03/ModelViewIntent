package com.learning.mvi.db

import com.learning.mvi.model.FactModel
import com.learning.util.EntityMapper
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