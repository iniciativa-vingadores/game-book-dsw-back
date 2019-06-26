package com.initvingadores.gamebook.dto.auth

data class AuthDTO (
        val id: Long,
        val email: String,
        val token: String
)