package com.learning.mvi.di

import com.learning.mvi.db.CacheMapper
import com.learning.mvi.db.database.FactDao
import com.learning.mvi.network.FactApi
import com.learning.mvi.network.NetworkMapper
import com.learning.mvi.repository.MainRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object RepositoryModule {

	@Singleton
	@Provides
	fun provideMainRepository(factDao : FactDao, factApi : FactApi, cacheMapper : CacheMapper,
	                          networkMapper : NetworkMapper) : MainRepository {
		return MainRepository(factDao, factApi, cacheMapper, networkMapper)
	}
}