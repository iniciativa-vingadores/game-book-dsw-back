package com.initvingadores.gamebook.util

import org.springframework.stereotype.Component
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.beans.factory.annotation.Value
import java.util.Date



@Component
class JWTUtils {
    @Value("\${jwt.secret}")
    private val secret: String? = null


    @Value("\${jwt.expiration_time}")
    private val expiration: Long? = null

    fun generateToken(email: String): String {
        return Jwts.builder()
                .setSubject(email)
                .setExpiration(Date(System.currentTimeMillis() + expiration!!))
                .signWith(SignatureAlgorithm.HS512, secret!!.toByteArray())
                .compact()
    }

    fun validToken(token: String): Boolean {
        val claims = getClaims(token)
        claims?.let {
            val username = it.subject
            val expirationDate = it.expiration
            val now = Date(System.currentTimeMillis())

            return username != null && expirationDate != null && now.before(expirationDate)
        }
        return false
    }

    fun getUsername(token: String): String? = getClaims(token)?.subject

    private fun getClaims(token: String): Claims? {
        return try {
            Jwts.parser()
                    .setSigningKey(secret!!.toByteArray())
                    .parseClaimsJws(token)
                    .body
        } catch (e: Exception) {
            null
        }
    }
}