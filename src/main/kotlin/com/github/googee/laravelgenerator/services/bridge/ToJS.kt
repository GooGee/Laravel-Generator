package com.github.googee.laravelgenerator.services.bridge

import org.cef.browser.CefBrowser

class ToJS(private val browser: CefBrowser) {

    var ready = false

    fun call(action: String, key: String, data: String, message: String) {
        val response = Response.ok(action, key, data, message)
        send(response)
    }

    fun send(response: Response) {
        println("-- to JS (${response.action}, ${response.key})")
        if (!ready) {
            println("not ready")
            return
        }
        val text = response.toJSON()
        browser.executeJavaScript("window.bridge.call($text)", browser.url, 0)
    }

}
