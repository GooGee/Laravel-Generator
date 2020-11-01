package com.github.googee.laravelgenerator.services

import org.cef.browser.CefBrowser

class ToBrowser(private val browser: CefBrowser) {

    fun load(fm: FileManager) {
        val data = fm.load().toJSON()
        run("load", data)
    }

    private fun run(function: String, data: String) {
        println(function)
        browser.executeJavaScript("window.bridge.$function($data)", browser.url, 0)
    }

    fun update(key: String, text: String) {
        val data = Response.ok(key, text).toJSON()
        run("editUpdate", data)
    }

}
