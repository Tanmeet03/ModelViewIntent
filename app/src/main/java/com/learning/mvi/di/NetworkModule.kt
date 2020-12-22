package com.learning.mvi.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.learning.mvi.business.data.network.NetworkDataSource
import com.learning.mvi.business.data.network.NetworkDataSourceImpl
import com.learning.mvi.business.domain.model.FactModel
import com.learning.mvi.business.domain.util.EntityMapper
import com.learning.mvi.framework.datasource.network.FactRetrofitService
import com.learning.mvi.framework.datasource.network.FactRetrofitServiceImpl
import com.learning.mvi.framework.datasource.network.api.FactApi
import com.learning.mvi.framework.datasource.network.mapper.NetworkMapper
import com.learning.mvi.framework.datasource.network.model.FactNetworkEntity
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object NetworkModule {

	@Singleton
	@Provides
	fun provideGsonBuilder() : Gson {
		return GsonBuilder().excludeFieldsWithoutExposeAnnotation().create()
	}

	@Singleton
	@Provides
	fun provideRetrofit(gson : Gson) : Retrofit.Builder {
		return Retrofit.Builder().client(client).baseUrl("https://numbersapi.p.rapidapi.com/")
			.addConverterFactory(GsonConverterFactory.create(gson))
	}

	var client : OkHttpClient = OkHttpClient.Builder().apply {

	}.addInterceptor { chain ->
		val request = chain.request()
		val newRequest = request.newBuilder().header("Content-Type", "application/json")
			.addHeader("x-rapidapi-key", "c218464e98msheaee282a723c717p16f34ejsnd30e1f3b14e2")
			.addHeader("x-rapidapi-host", "numbersapi.p.rapidapi.com")
			.method(request.method(), request.body()).build()
		val response = chain.proceed(newRequest)

		response
	}.connectTimeout(60, TimeUnit.SECONDS).readTimeout(60, TimeUnit.SECONDS)
		.writeTimeout(60, TimeUnit.SECONDS).build()

	@Singleton
	@Provides
	fun provideFactService(retrofit : Retrofit.Builder) : FactApi {
		return retrofit.build().create(FactApi::class.java)
	}

	@Singleton
	@Provides
	fun provideNetworkMapper(): EntityMapper<FactNetworkEntity, FactModel> {
		return NetworkMapper()
	}

	@Singleton
	@Provides
	fun provideNetworkDataSource(
		factRetrofitService: FactRetrofitService,
		networkMapper: NetworkMapper
	): NetworkDataSource {
		return NetworkDataSourceImpl(factRetrofitService, networkMapper)
	}

	@Singleton
	@Provides
	fun provideRetrofitService(
		factRetrofit: FactApi
	): FactRetrofitService{
		return FactRetrofitServiceImpl(factRetrofit)
	}

}