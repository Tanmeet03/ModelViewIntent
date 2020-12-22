package com.learning.mvi.di

import android.content.Context
import androidx.room.Room
import com.learning.mvi.db.database.FactDao
import com.learning.mvi.db.database.FactDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object RoomModule {

	@Singleton
	@Provides
	fun provideFactDb(@ApplicationContext
	                  ctx : Context) : FactDatabase {
		return Room.databaseBuilder(ctx, FactDatabase::class.java, FactDatabase.DATABASE_NAME)
			.fallbackToDestructiveMigration().build()
	}

	@Singleton
	@Provides
	fun provideFactDao(factDatabase : FactDatabase) : FactDao {
		return factDatabase.factDao()
	}

}