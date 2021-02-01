package com.learning.mvi.framework.datasource.cache.database

import androidx.room.Dao
import androidx.room.Query
import com.learning.mvi.framework.datasource.cache.BaseDao
import com.learning.mvi.framework.datasource.model.DbDataCacheEntity

@Dao
interface FactDao : BaseDao<DbDataCacheEntity> {
	//returns row it would get inserted to

	@Query("SELECT * FROM home_page WHERE request_url==:url")
	fun getItemById(url : String) : Long

	@Query("SELECT * FROM home_page WHERE request_url= :url")
	suspend fun getCacheData(url : String) : DbDataCacheEntity

	@Query("SELECT * FROM home_page WHERE request_url=:url")
	suspend fun getAllData(url : String) : List<DbDataCacheEntity>
}