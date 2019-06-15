package com.initvingadores.gamebook.security

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.initvingadores.gamebook.model.Customer
import com.initvingadores.gamebook.system.getEmailUserLogged
import com.initvingadores.gamebook.util.JWTUtils
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import java.io.IOException
import java.lang.RuntimeException
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class JWTAuthenticationFilter(
        private val myAuthenticationManager: AuthenticationManager?,
        private val jwtUtils: JWTUtils)
    : UsernamePasswordAuthenticationFilter() {

    override fun attemptAuthentication(request: HttpServletRequest?,
                                       response: HttpServletResponse?): Authentication {
        try {
            val customer = ObjectMapper().readValue<Customer>(request!!.inputStream)
            request.setAttribute("email", customer.email)
            val authenticationToken =
                    UsernamePasswordAuthenticationToken(customer.email, customer.password)

            return myAuthenticationManager!!.authenticate(authenticationToken)

        } catch (exception: IOException){ throw RuntimeException(exception) }
    }

    override fun successfulAuthentication(request: HttpServletRequest?,
                                          response: HttpServletResponse?,
                                          chain: FilterChain?,
                                          authResult: Authentication?) {

        val email = (authResult?.principal as UserSpringSecurity).username
        val token = jwtUtils.generateToken(email)

        response?.addHeader("Authorization", token)
        response?.addHeader("access-control-expose-headers", "Authorization")
    }
}