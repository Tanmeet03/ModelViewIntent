package com.learning.mvi.business.domain.model

data class FactModel(var id : Long, var text : String, var year : Int, var number : Long,
                     var found : Boolean, var type : String)
