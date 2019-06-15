package com.initvingadores.gamebook.system.exception

import java.util.*

data class CustomError (
        val status: Int,
        val time: Date,
        val message: String?
)