package com.github.googee.laravelgenerator.services

import java.util.*

class Version {
    companion object {
        const val FileName = "/version.properties"

        fun get(): String {
            val properties = Properties()
            properties.load(object {}.javaClass.getResourceAsStream(FileName))
            return properties.get("version") as String
        }

    }
}
