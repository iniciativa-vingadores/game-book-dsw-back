package com.initvingadores.gamebook.system

import com.initvingadores.gamebook.security.UserSpringSecurity
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.authority.SimpleGrantedAuthority


fun getIdUserLogged(): Long? {
    val authentication = SecurityContextHolder.getContext().authentication

    return if (authentication == null || authentication.principal == "anonymousUser") {
        null
    } else (authentication.principal as UserSpringSecurity).getUserId()
}

fun getEmailUserLogged(): String? {
    val authentication = SecurityContextHolder.getContext().authentication

    return if (authentication == null || authentication.principal == "anonymousUser") {
        null
    } else (authentication.principal as UserSpringSecurity).username
}

