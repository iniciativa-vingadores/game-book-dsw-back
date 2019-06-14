package com.initvingadores.gamebook.service

import com.initvingadores.gamebook.dto.customer.CreateCustomerDTO
import com.initvingadores.gamebook.dto.customer.DetailCustomerDTO
import com.initvingadores.gamebook.dto.customer.UpdateCustomerDTO
import com.initvingadores.gamebook.repository.CustomerRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class CustomerServiceImpl : CustomerService {

    @Autowired
    lateinit var customerRepository: CustomerRepository

    override fun save(customerDTO: CreateCustomerDTO): DetailCustomerDTO {
        TODO("not implemented")
    }

    override fun detail(idCustomer: Long): DetailCustomerDTO {
        TODO("not implemented")
    }

    override fun update(customerDTO: UpdateCustomerDTO): DetailCustomerDTO {
        TODO("not implemented")
    }

    override fun delete(idCustomer: Long) {
        TODO("not implemented")
    }

}