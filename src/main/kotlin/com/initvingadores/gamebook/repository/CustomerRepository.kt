package com.initvingadores.gamebook.repository

import com.initvingadores.gamebook.model.Customer
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CustomerRepository : JpaRepository<Customer, Long> {

    fun getByEmail(email: String): Customer?
}