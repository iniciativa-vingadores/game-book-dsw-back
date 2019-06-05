package com.initvingadores.gamebook.security

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class UserSpringSecurity(
        private val id: Long,
        private val email: String,
        private val userPassword: String,
        private val authority: MutableCollection<out GrantedAuthority>
): UserDetails {

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> = authority

    fun getUserId() = id

    override fun isEnabled(): Boolean = true

    override fun getUsername(): String = email

    override fun isCredentialsNonExpired(): Boolean = true

    override fun getPassword(): String = userPassword

    override fun isAccountNonExpired(): Boolean = true

    override fun isAccountNonLocked(): Boolean = true
}