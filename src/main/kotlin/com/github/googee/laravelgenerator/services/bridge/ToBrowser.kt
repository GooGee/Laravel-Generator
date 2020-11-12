package com.github.googee.laravelgenerator.services.bridge

import com.github.googee.laravelgenerator.services.file.FileManager
import org.cef.browser.CefBrowser

class ToBrowser(private val browser: CefBrowser, private val fm: FileManager) {

    fun load() {
        val file = fm.getFile(FileManager.GeneratorFile)
        val text = FileManager.read(file)
        val data = Response.ok("", text).toJSON()
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
