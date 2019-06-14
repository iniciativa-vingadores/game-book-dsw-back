package com.initvingadores.gamebook.service

import com.initvingadores.gamebook.dto.customer.CreateCustomerDTO
import com.initvingadores.gamebook.dto.customer.DetailCustomerDTO
import com.initvingadores.gamebook.dto.customer.UpdateCustomerDTO

interface CustomerService {

    fun save(customerDTO: CreateCustomerDTO): DetailCustomerDTO

    fun detail(idCustomer: Long): DetailCustomerDTO

    fun update(customerDTO: UpdateCustomerDTO): DetailCustomerDTO

    fun delete(idCustomer: Long)
}