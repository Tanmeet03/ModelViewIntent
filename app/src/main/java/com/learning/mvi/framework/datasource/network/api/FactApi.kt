package com.learning.mvi.framework.datasource.network.api

import com.learning.mvi.framework.datasource.network.model.FactNetworkEntity
import retrofit2.http.GET
import retrofit2.http.Path

interface FactApi {

	@GET("{month}/{date}/date")
	suspend fun getDateTrivia(@Path("month")
	                              month : Int,
	                          @Path("date")
	                              date : Int) : FactNetworkEntity

	@GET("{number}/math")
	suspend fun getMathTrivia(@Path("number")
	                          number : Long) : FactNetworkEntity

	@GET("random/trivia")
	suspend fun getRandomTrivia() : FactNetworkEntity

	@GET("{number}/trivia")
	suspend fun getTrivia(@Path("number")
	                          number : Long) : FactNetworkEntity

	@GET("{year}/year")
	suspend fun getYearTrivia(@Path("year")
	                              year : Long) : FactNetworkEntity

}