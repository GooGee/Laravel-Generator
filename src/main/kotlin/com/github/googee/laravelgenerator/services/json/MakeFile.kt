package com.github.googee.laravelgenerator.services.json

import kotlinx.serialization.*

@Serializable
class MakeFile(val file: String, val content: String) {}
