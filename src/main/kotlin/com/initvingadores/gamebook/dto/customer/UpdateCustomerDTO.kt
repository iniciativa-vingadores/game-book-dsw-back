package com.initvingadores.gamebook.dto.customer

import com.initvingadores.gamebook.model.File
import javax.validation.constraints.NotNull

data class UpdateCustomerDTO(
        @NotNull
        val id: Long,
        val name: String?,
        val email: String?,
        val password: String?,
        val image: File?
)