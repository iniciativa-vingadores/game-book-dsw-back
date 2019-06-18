package com.initvingadores.gamebook.service

import com.initvingadores.gamebook.dto.customer.CreateCustomerDTO
import com.initvingadores.gamebook.dto.customer.DetailCustomerDTO
import com.initvingadores.gamebook.dto.customer.UpdateCustomerDTO
import com.initvingadores.gamebook.dto.customer.toCustomer
import com.initvingadores.gamebook.model.Customer
import com.initvingadores.gamebook.model.Situation
import com.initvingadores.gamebook.model.toDetailCustomerDTO
import com.initvingadores.gamebook.repository.CustomerRepository
import com.initvingadores.gamebook.system.exception.*
import com.initvingadores.gamebook.system.getIdUserLogged
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

@Service
class CustomerServiceImpl : CustomerService {

    @Autowired
    lateinit var customerRepository: CustomerRepository
    @Autowired
    lateinit var bCryptPasswordEncoder: BCryptPasswordEncoder

    override fun save(customerDTO: CreateCustomerDTO): DetailCustomerDTO {
        customerRepository.getByEmail(customerDTO.email)
                ?: return customerRepository.save(
                        customerDTO.toCustomer(
                                bCryptPasswordEncoder.encode(customerDTO.password))
                ).toDetailCustomerDTO()

        throw DataException("Email já cadastrado.")
    }

    override fun detail(idCustomer: Long): DetailCustomerDTO =
            getCustomerById(idCustomer).toDetailCustomerDTO()

    override fun update(customerDTO: UpdateCustomerDTO): DetailCustomerDTO {
        val customerDB = getCustomerById(customerDTO.id)

        val customer = customerDTO.toCustomer(
                customerDTO.name ?: customerDB.name,
                customerDTO.email ?: customerDB.email,
                customerDTO.password?.let {
                    bCryptPasswordEncoder.encode(it)
                } ?: customerDB.password)

        return customerRepository.save(customer).toDetailCustomerDTO()
    }

    override fun delete(idCustomer: Long) {
        val customerDB = getCustomerById(idCustomer)

        val deletedCustomer = Customer(
                customerDB.id,
                Situation.INACTIVE,
                customerDB.name,
                customerDB.email,
                customerDB.password,
                customerDB.image)

        customerRepository.save(deletedCustomer)
    }


    override fun getCustomerById(idCustomer: Long): Customer {
        val idCustomerLogged = getIdUserLogged()
                ?: throw AuthenticationException("Usuário não está autenticado.")

        if (idCustomer != idCustomerLogged) {
            throw AuthorizationException("Acesso negado.")
        } else {
            return customerRepository.findById(idCustomer)
                    .orElseThrow { throw NotFoundException("Perfil de usuário não encontrado.") }
        }
    }

}