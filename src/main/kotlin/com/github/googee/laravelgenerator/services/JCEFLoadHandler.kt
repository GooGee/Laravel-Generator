package com.github.googee.laravelgenerator.services

import com.intellij.ui.jcef.JBCefBrowser
import org.cef.browser.CefBrowser
import org.cef.browser.CefFrame
import org.cef.handler.CefLoadHandler
import org.cef.network.CefRequest

class JCEFLoadHandler(val browser: JBCefBrowser, val uri: String, val code: String) : CefLoadHandler {
    override fun onLoadStart(p0: CefBrowser?, p1: CefFrame?, p2: CefRequest.TransitionType?) {
    }

    override fun onLoadingStateChange(p0: CefBrowser?, p1: Boolean, p2: Boolean, p3: Boolean) {
    }

    override fun onLoadError(p0: CefBrowser?, p1: CefFrame?, p2: CefLoadHandler.ErrorCode?, p3: String?, p4: String?) {
    }

    override fun onLoadEnd(p0: CefBrowser?, p1: CefFrame?, p2: Int) {
        browser.cefBrowser.executeJavaScript(code, uri, 0)
        browser.cefBrowser.executeJavaScript("window.bridge.load()", uri, 0)
    }
}