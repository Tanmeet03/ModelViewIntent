package com.learning.mvi.di

import android.content.Context
import androidx.room.Room
import com.learning.mvi.business.data.cache.CacheDataSource
import com.learning.mvi.business.data.cache.CacheDataSourceImpl
import com.learning.mvi.business.domain.model.FactModel
import com.learning.mvi.business.domain.util.EntityMapper
import com.learning.mvi.framework.datasource.cache.FactDaoService
import com.learning.mvi.framework.datasource.cache.FactDaoServiceImpl
import com.learning.mvi.framework.datasource.cache.database.FactDao
import com.learning.mvi.framework.datasource.cache.database.FactDatabase
import com.learning.mvi.framework.datasource.cache.mapper.CacheMapper
import com.learning.mvi.framework.datasource.cache.model.FactCacheEntity
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object CacheModule {

	@Singleton
	@Provides
	fun provideCacheMapper() : EntityMapper<FactCacheEntity, FactModel> {
		return CacheMapper()
	}

	@Singleton
	@Provides
	fun provideFactDb(@ApplicationContext
                      context : Context) : FactDatabase {
		return Room.databaseBuilder(context, FactDatabase::class.java, FactDatabase.DATABASE_NAME)
			.fallbackToDestructiveMigration().build()
	}

	@Singleton
	@Provides
	fun provideFactDAO(factDatabase : FactDatabase) : FactDao {
		return factDatabase.factDao()
	}

	@Singleton
	@Provides
	fun provideFactDaoService(factDao : FactDao) : FactDaoService {
		return FactDaoServiceImpl(factDao)
	}

	@Singleton
	@Provides
	fun provideCacheDataSource(factDaoService : FactDaoService, cacheMapper : CacheMapper) : CacheDataSource {
		return CacheDataSourceImpl(factDaoService, cacheMapper)
	}
}

























