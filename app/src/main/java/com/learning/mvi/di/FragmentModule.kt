package com.learning.mvi.di

import androidx.fragment.app.FragmentFactory
import com.learning.mvi.ui.fragment.MainFragmentFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Singleton

@ExperimentalCoroutinesApi
@Module
@InstallIn(ApplicationComponent::class)
object FragmentModule {

	@Singleton
	@Provides
	fun provideMainFragmentFactory(someString : String) : FragmentFactory {
		return MainFragmentFactory(someString)
	}
}
