package com.github.googee.laravelgenerator.services.bridge

import com.github.googee.laravelgenerator.services.ServiceManager
import com.intellij.ui.jcef.JBCefBrowser
import com.intellij.ui.jcef.JBCefJSQuery

class FromJS(val browser: JBCefBrowser) {

    private var code = ""

    private fun makeFunction(query: JBCefJSQuery): String {
        val onSuccessCallback = "function(response) {window.bridge.handle(response)}"
        val onFailureCallback = "function(code, message) {window.bridge.error(code, message)}"
        val code = query.inject("text", onSuccessCallback, onFailureCallback)
        return "function(text) {$code}"
    }

    fun makeCode() {
        val query = JBCefJSQuery.create(browser)
        query.addHandler { text ->
            val request = Request.parse(text)
            println("call Java (${request.action}, ${request.key})")
            ServiceManager.run()
            null
        }
        val function = makeFunction(query)
        code = "window.JavaBridge = {call : $function};"
    }

    fun inject() {
        println(code)
        browser.cefBrowser.executeJavaScript(code, browser.cefBrowser.url, 0)
    }

}
