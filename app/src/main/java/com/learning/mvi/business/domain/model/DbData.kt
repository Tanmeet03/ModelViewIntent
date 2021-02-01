package com.learning.mvi.business.domain.model

data class DbData(
    val id : Long = 0,

    val data : String?,

    val updatedAt : String?,

    val requestUrl : String?,
)