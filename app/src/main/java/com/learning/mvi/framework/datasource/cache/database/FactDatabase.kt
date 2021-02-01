package com.learning.mvi.framework.datasource.cache.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.learning.mvi.framework.datasource.cache.model.FactCacheEntity
import com.learning.mvi.framework.datasource.model.DbDataCacheEntity


@Database(entities = [DbDataCacheEntity::class], version = 3)
abstract class FactDatabase : RoomDatabase() {
	abstract fun factDao() : FactDao

	companion object {
		val DATABASE_NAME = "fact_db"
	}
}