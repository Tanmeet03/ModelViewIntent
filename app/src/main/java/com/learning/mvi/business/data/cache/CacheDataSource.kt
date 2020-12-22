package com.learning.mvi.business.data.cache

import com.learning.mvi.business.domain.model.FactModel

interface CacheDataSource {

    suspend fun insert(fact: FactModel): Long

    suspend fun get(): FactModel
}