package com.learning.mvi.di

import com.learning.mvi.business.data.cache.CacheDataSource
import com.learning.mvi.business.data.network.NetworkDataSource
import com.learning.mvi.business.interactors.MainRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object InteractorsModule {

	@Singleton
	@Provides
	fun provideGetFact(cacheDataSource : CacheDataSource, networkDataSource : NetworkDataSource) : MainRepository {
		return MainRepository(cacheDataSource, networkDataSource)
	}
}
