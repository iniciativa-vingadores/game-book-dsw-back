package com.initvingadores.gamebook.service

import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class UserDetailServiceImpl : UserDetailsService {

    override fun loadUserByUsername(p0: String?): UserDetails {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}