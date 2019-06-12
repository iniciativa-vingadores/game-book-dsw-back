package com.initvingadores.gamebook.dto.customer

import com.initvingadores.gamebook.model.File
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size
import kotlin.math.min

data class CreateCustomerDTO (
        val id: Long = 0L,
        @NotNull
        @NotBlank(message = "Campo obrigatório")
        val name: String,
        @NotNull
        @NotBlank(message = "Campo obrigatório")
        val email: String,
        @NotNull
        @NotBlank(message = "Campo obrigatório")
        @Size(min = 6, max = 15, message = "Senha deve ter tamanho entre 6 e 15 e conter caracteres e números")
        val password: String,
        val image: File?
)