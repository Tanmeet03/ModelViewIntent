package com.learning.mvi.framework.datasource.cache.mapper

import com.learning.mvi.business.domain.model.DbData
import com.learning.mvi.business.domain.model.FactModel
import com.learning.mvi.business.domain.util.EntityMapper
import com.learning.mvi.framework.datasource.cache.model.FactCacheEntity
import com.learning.mvi.framework.datasource.model.DbDataCacheEntity
import javax.inject.Inject

class CacheMapper
@Inject constructor() : EntityMapper<DbDataCacheEntity, DbData> {
	override fun mapFromEntity(entity : DbDataCacheEntity) : DbData {
		return DbData(data = entity.data,
		              updatedAt = entity.updatedAt,
		              requestUrl = entity.requestUrl)
	}

	override fun mapToEntity(domainModel : DbData) : DbDataCacheEntity {
		return DbDataCacheEntity(data = domainModel.data,
		                         updatedAt = domainModel.updatedAt,
		                         requestUrl = domainModel.requestUrl)
	}
}