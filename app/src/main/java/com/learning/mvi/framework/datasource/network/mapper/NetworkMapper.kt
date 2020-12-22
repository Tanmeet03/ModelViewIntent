package com.learning.mvi.framework.datasource.network.mapper

import com.learning.mvi.business.domain.model.FactModel
import com.learning.mvi.business.domain.util.EntityMapper
import com.learning.mvi.framework.datasource.network.model.FactNetworkEntity
import javax.inject.Inject

class NetworkMapper
@Inject constructor() : EntityMapper<FactNetworkEntity, FactModel> {
	override fun mapFromEntity(entity : FactNetworkEntity) : FactModel {
		return FactModel(id = 0, text = entity.info, year = entity.year, number = entity.numberInput, found = entity.isDataAvailable, type = entity.type)
	}

	override fun mapToEntity(domainModel : FactModel) : FactNetworkEntity {
		return FactNetworkEntity(id = 0, info = domainModel.text, year = domainModel.year, numberInput = domainModel.number, isDataAvailable = domainModel.found, type = domainModel.type)
	}

}