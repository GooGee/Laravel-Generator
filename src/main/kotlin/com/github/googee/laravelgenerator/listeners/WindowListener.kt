package com.github.googee.laravelgenerator.listeners

import com.github.googee.laravelgenerator.services.file.FileManager
import com.intellij.openapi.wm.ToolWindowManager
import com.intellij.openapi.wm.ex.ToolWindowManagerListener

class WindowListener : ToolWindowManagerListener {
    private var active = false

    override fun stateChanged(toolWindowManager: ToolWindowManager) {
        super.stateChanged(toolWindowManager)

        if (toolWindowManager.activeToolWindowId == "Code Generator") {
            active = true
        } else {
            if (active) {
                FileManager.refresh()
            }
            active = false
        }
    }

}
