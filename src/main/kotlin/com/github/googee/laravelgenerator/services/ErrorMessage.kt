package com.github.googee.laravelgenerator.services

class ErrorMessage {

    companion object {

        const val BadRequest = "Bad Request"
        const val OK = "OK"
        const val Unknown = "Unknown Error"

        fun check(message: String?): String {
            return message ?: Unknown
        }
    }

}
