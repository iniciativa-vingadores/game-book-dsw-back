package com.initvingadores.gamebook.dto.customer

import com.initvingadores.gamebook.model.File
import com.initvingadores.gamebook.model.Situation

data class DetailCustomerDTO (
        val id: Long,
        val name: String,
        val email: String,
        val situation: Situation,
        val image: File?
)