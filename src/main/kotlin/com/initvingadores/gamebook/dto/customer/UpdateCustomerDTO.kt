package com.initvingadores.gamebook.dto.customer

import com.initvingadores.gamebook.model.File
import javax.validation.constraints.NotNull

data class UpdateCustomerDTO(
        @NotNull(message = "Id não pode ser nulo")
        val id: Long,
        val name: String?,
        val email: String?,
        val password: String?,
        val image: File?
)