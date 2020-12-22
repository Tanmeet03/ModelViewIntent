package com.learning.mvi.framework.datasource.network.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class FactNetworkEntity(
	@SerializedName("id")
	@Expose
	val id : Long,

	@SerializedName("text")
	@Expose
	val info:String,

	@SerializedName("year")
	@Expose
	val year:Int,

	@SerializedName("number")
	@Expose
	val numberInput:Long,

	@SerializedName("found")
	@Expose
	val isDataAvailable:Boolean,

	@SerializedName("type")
	@Expose
	val type:String,


){

}