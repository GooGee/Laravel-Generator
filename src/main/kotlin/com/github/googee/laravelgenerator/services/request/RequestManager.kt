package com.github.googee.laravelgenerator.services.request

import com.github.googee.laravelgenerator.services.bridge.ToBrowser
import com.github.googee.laravelgenerator.services.view.EditorManager
import com.github.googee.laravelgenerator.services.file.FileManager
import com.intellij.ui.jcef.JBCefBrowser

class RequestManager(val browser: JBCefBrowser, val tb: ToBrowser, val em: EditorManager, val fm: FileManager) {
    private val list = ArrayList<String>()

    fun register(): String {
        list.add(EditRequest().register(browser, tb, em, fm))
        list.add(HTTPRequest().register(browser, tb))
        list.add(HTTPRequest().register(browser, tb, true))
        list.add(RefreshRequest().register(browser, tb, fm))
        list.add(ReadRequest().register(browser, tb, fm))
        list.add(WriteRequest().register(browser, tb, fm))
        val text = list.joinToString()
        return "window.JavaBridge = {$text};"
    }

}
