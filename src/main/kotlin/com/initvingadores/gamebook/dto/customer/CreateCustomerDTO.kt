package com.initvingadores.gamebook.dto.customer

import com.initvingadores.gamebook.model.Customer
import com.initvingadores.gamebook.model.File
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

data class CreateCustomerDTO (
        val id: Long = 0L,
        @NotNull(message = "Nome não pode ser nulo")
        @NotBlank(message = "Campo obrigatório")
        val name: String,
        @NotNull(message = "Email não pode ser nulo")
        @NotBlank(message = "Campo obrigatório")
        @Email(message = "Email inválido")
        val email: String,
        @NotNull(message = "Senha não pode ser nula")
        @NotBlank(message = "Campo obrigatório")
        @Size(min = 6, max = 15, message = "Senha deve ter tamanho entre 6 e 15 caracteres.")
        val password: String,
        val image: File?
)

fun CreateCustomerDTO.toCustomer(password: String): Customer =
        Customer(
                id = id,
                name = name,
                email = email,
                password = password,
                image = image)