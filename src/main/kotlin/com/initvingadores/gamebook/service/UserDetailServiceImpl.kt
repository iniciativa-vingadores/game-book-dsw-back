package com.initvingadores.gamebook.service

import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class UserDetailServiceImpl : UserDetailsService {

    override fun loadUserByUsername(p0: String?): UserDetails {
        TODO() // Recupera usuario do bd pra saber se esta logado ou n
    }
}