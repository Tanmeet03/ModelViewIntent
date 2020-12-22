package com.learning.mvi.framework.datasource.cache.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.learning.mvi.framework.datasource.cache.model.FactCacheEntity

@Dao
interface FactDao {
	//returns row it would get inserted to
	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun insert(factCacheEntity : FactCacheEntity) : Long

	@Query("SELECT * FROM fact")
	suspend fun getFactData(): FactCacheEntity
}