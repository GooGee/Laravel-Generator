package com.github.googee.laravelgenerator.services.bridge

import com.github.googee.laravelgenerator.services.loader.Loader
import org.cef.browser.CefBrowser

class ToBrowser(private val browser: CefBrowser, private val loader: Loader) {

    fun load() {
        val data = loader.load().toJSON()
        run("load", data)
    }

    fun run(function: String, data: String) {
        println(function)
        browser.executeJavaScript("window.bridge.$function($data)", browser.url, 0)
    }

    fun update(key: String, text: String) {
        val data = Response.ok(key, text).toJSON()
        run("editUpdate", data)
    }

}
