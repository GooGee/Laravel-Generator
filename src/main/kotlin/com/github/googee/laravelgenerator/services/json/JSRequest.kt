package com.github.googee.laravelgenerator.services.json

import kotlinx.serialization.*

@Serializable
class JSRequest(val key: String, val data: String) {}
