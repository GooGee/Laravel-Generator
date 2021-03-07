package com.github.googee.laravelgenerator.services

class Constant {

    companion object {

        const val Title = "Code Generator"

        const val Unknown = "Unknown Error"

        fun check(message: String?): String {
            return message ?: Unknown
        }
    }
}
