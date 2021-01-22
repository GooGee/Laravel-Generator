package com.github.googee.laravelgenerator.services.bridge

import org.cef.browser.CefBrowser

class ToJS(private val browser: CefBrowser) {

    fun call(action: String, key: String, data: String, message: String) {
        val response = Response.ok(action, key, data, message)
        send(response)
    }

    fun send(response: Response) {
        println("call JS (${response.action}, ${response.key})")
        val text = response.toJSON()
        browser.executeJavaScript("window.bridge.call($text)", browser.url, 0)
    }

}
