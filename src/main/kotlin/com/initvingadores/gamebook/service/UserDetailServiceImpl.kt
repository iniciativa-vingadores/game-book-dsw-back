package com.initvingadores.gamebook.service

import com.initvingadores.gamebook.model.Customer
import com.initvingadores.gamebook.model.Situation
import com.initvingadores.gamebook.repository.CustomerRepository
import com.initvingadores.gamebook.security.UserSpringSecurity
import com.initvingadores.gamebook.system.exception.AuthorizationException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.authority.AuthorityUtils
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class UserDetailServiceImpl : UserDetailsService {

    @Autowired
    lateinit var customerRepository: CustomerRepository

    override fun loadUserByUsername(email: String?): UserDetails {
        email?.let {
            val customerDB = customerRepository.getByEmail(it)
                    ?: throw UsernameNotFoundException(it)

            //Ativando o usuario no ato do login
            //Tipo facebook, tem seus dados pra sempre hehe
            if (customerDB.situation == Situation.INACTIVE) {
                customerRepository.save(Customer(
                        customerDB.id,
                        Situation.ACTIVE,
                        customerDB.name,
                        customerDB.email,
                        customerDB.password,
                        customerDB.image))
            }

            return UserSpringSecurity(
                    customerDB.id,
                    customerDB.email,
                    customerDB.password,
                    AuthorityUtils.createAuthorityList("ROLE_CUSTOMER"))
        }

        throw AuthorizationException("Email inválido.")
    }
}