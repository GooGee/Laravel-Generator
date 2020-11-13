package com.github.googee.laravelgenerator.services.request

import com.intellij.ui.jcef.JBCefJSQuery

open class Request {

    protected fun getHandlerName(name: String): String {
        return "${name}Handler"
    }

    protected fun makeFunction(name: String, query: JBCefJSQuery): String {
        val onSuccessCallback = "function(response) {window.bridge.handle(response)}"
        val onFailureCallback = "function(error_code, error_message) {window.bridge.handleError(error_code, error_message)}"
        val text = query.inject("text", onSuccessCallback, onFailureCallback)
        return "$name : function(text) {$text}"
    }

}
