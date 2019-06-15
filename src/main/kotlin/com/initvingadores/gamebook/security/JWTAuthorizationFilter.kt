package com.initvingadores.gamebook.security

import com.initvingadores.gamebook.service.UserDetailServiceImpl
import com.initvingadores.gamebook.util.JWTUtils
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class JWTAuthorizationFilter(
        authenticationManager: AuthenticationManager?,
        private val userDetailServiceImpl: UserDetailServiceImpl,
        private val jwtUtils: JWTUtils)
    : BasicAuthenticationFilter(authenticationManager) {

    override fun doFilterInternal(request: HttpServletRequest,
                                  response: HttpServletResponse,
                                  chain: FilterChain) {

        val header = request.getHeader("Authorization")

        if (!header.isNullOrEmpty()) {
            val auth = getAuthenticationToken(header)

            auth?.let {
                SecurityContextHolder.getContext().authentication = it
            }
        }

        chain.doFilter(request, response)
    }

    private fun getAuthenticationToken(token: String): UsernamePasswordAuthenticationToken? {
        if (jwtUtils.validToken(token)) {
            val email = jwtUtils.getUsername(token)
            val userDetails = userDetailServiceImpl.loadUserByUsername(email)

            return UsernamePasswordAuthenticationToken(userDetails, null, userDetails.authorities)
        }

        return null
    }
}