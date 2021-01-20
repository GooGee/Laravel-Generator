package com.github.googee.laravelgenerator.services

import com.github.googee.laravelgenerator.services.request.RequestManager
import com.github.googee.laravelgenerator.services.bridge.ToBrowser
import com.github.googee.laravelgenerator.services.file.FileManager
import com.github.googee.laravelgenerator.services.view.BrowserFactory
import com.github.googee.laravelgenerator.services.view.EditorManager
import com.github.googee.laravelgenerator.services.view.WebTab
import com.intellij.openapi.project.Project
import com.intellij.openapi.wm.FocusWatcher
import com.intellij.openapi.wm.ToolWindow
import com.intellij.openapi.wm.ToolWindowFactory
import org.jetbrains.annotations.Nullable
import java.awt.AWTEvent
import java.awt.Component

class WindowFactory : ToolWindowFactory {

    override fun createToolWindowContent(project: Project, toolWindow: ToolWindow) {
        val manager = toolWindow.contentManager
        val browser = BrowserFactory.make()
        val fm = FileManager(project)
        val tb = ToBrowser(browser.cefBrowser, fm)
        val em = EditorManager(project, manager, tb)
        val rm = RequestManager(browser, tb, em, fm)
        val panel = WebTab(browser, tb, rm)
        val tab = manager.factory.createContent(panel, "Generator", false)
        tab.isCloseable = false
        var watcher = object : FocusWatcher() {
            override fun focusedComponentChanged(component: @Nullable Component?, cause: @Nullable AWTEvent?) {
                if (focusedComponent != null) {
                    FileManager.refresh()
                }
            }
        }.install(toolWindow.component)
        manager.addContent(tab)
    }

}
