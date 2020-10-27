package com.github.googee.laravelgenerator.services

import com.intellij.openapi.project.Project
import com.intellij.openapi.wm.ToolWindow
import com.intellij.openapi.wm.ToolWindowFactory
import com.intellij.ui.jcef.JBCefApp

class WindowFactory : ToolWindowFactory {

    override fun createToolWindowContent(project: Project, toolWindow: ToolWindow) {
        val view = View()
        toolWindow.component.add(view)
        if (!JBCefApp.isSupported()) {
            view.showError("Error: JCEF is required")
            return
        }

        view.addBrowser(project)
    }

}