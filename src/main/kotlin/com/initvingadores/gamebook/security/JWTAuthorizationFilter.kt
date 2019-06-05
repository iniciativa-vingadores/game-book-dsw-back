package com.initvingadores.gamebook.security

import com.initvingadores.gamebook.util.JWTUtils
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter

class JWTAuthorizationFilter(
        authenticationManager: AuthenticationManager?,
        jwtUtils: JWTUtils)
    : BasicAuthenticationFilter(authenticationManager) {
}