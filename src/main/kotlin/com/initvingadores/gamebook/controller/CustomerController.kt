package com.initvingadores.gamebook.controller

import com.initvingadores.gamebook.dto.customer.CreateCustomerDTO
import com.initvingadores.gamebook.dto.customer.DetailCustomerDTO
import com.initvingadores.gamebook.dto.customer.UpdateCustomerDTO
import com.initvingadores.gamebook.service.CustomerService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/customers")
class CustomerController {

    @Autowired
    lateinit var customerService: CustomerService

    @PostMapping
    fun saveCustomer (
        @Valid
        @RequestBody
        customerDTO: CreateCustomerDTO) : ResponseEntity<DetailCustomerDTO> {

        return ResponseEntity(customerService.save(customerDTO), HttpStatus.CREATED)
    }

    @PreAuthorize("hasAnyRole('ROLE_CUSTOMER')")
    @GetMapping("/{idCustomer}")
    fun detailCustomer (@PathVariable idCustomer: Long) : ResponseEntity<DetailCustomerDTO> {

        return ResponseEntity(customerService.detail(idCustomer), HttpStatus.OK)
    }

    @PreAuthorize("hasAnyRole('ROLE_CUSTOMER')")
    @PutMapping
    fun updateCustomer (
            @Valid
            @RequestBody
            customerDTO: UpdateCustomerDTO) : ResponseEntity<DetailCustomerDTO> {

        return ResponseEntity(customerService.update(customerDTO), HttpStatus.OK)
    }

    @PreAuthorize("hasAnyRole('ROLE_CUSTOMER')")
    @DeleteMapping("/{idCustomer}")
    fun deleteCustomer (@PathVariable idCustomer: Long) : ResponseEntity<String> {

        customerService.delete(idCustomer)

        return ResponseEntity(HttpStatus.NO_CONTENT)
    }
}