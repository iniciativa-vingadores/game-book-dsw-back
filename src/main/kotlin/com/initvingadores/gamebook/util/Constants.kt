package com.initvingadores.gamebook.util

class Constants {
    companion object {
        const val PASSWORD_PATTERN = "^(?=.*\\d)(?=.*[a-z])(?!.*\\s).*$"
        const val EMAIL_PATTERN = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$"
    }
}