package com.learning.mvi.framework.datasource.cache

import androidx.room.*

@Dao
interface BaseDao<T> {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertData(obj : T) : Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(vararg obj : T)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    abstract fun update(obj : T)

    @Delete
    abstract fun delete(obj : T)
}