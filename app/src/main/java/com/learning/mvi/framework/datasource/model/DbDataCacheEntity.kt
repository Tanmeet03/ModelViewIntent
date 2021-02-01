package com.learning.mvi.framework.datasource.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "home_page")
data class DbDataCacheEntity(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id : Long = 0,

    //this is json data
    @ColumnInfo(name = "data")
    val data : String?,

    //timestamp
    @ColumnInfo(name = "updated_at")
    val updatedAt : String?,

    //request url from which we will fetch data
    @ColumnInfo(name = "request_url")
    val requestUrl : String?,
)