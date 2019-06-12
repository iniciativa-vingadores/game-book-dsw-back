package com.initvingadores.gamebook.controller

import com.initvingadores.gamebook.dto.customer.CreateCustomerDTO
import com.initvingadores.gamebook.service.CustomerService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/customers")
class CustomerController {

    @Autowired
    lateinit var customerService: CustomerService

    //TODO(): rotas do cliente

    @PostMapping
    fun saveCustomer (
        @Valid
        @RequestBody
        customerDTO: CreateCustomerDTO) : ResponseEntity<CreateCustomerDTO> {

        TODO() //Mapping que converte DTO to model
    }
}