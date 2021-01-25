package com.github.googee.laravelgenerator.services.view

import com.github.googee.laravelgenerator.services.bridge.Request
import com.github.googee.laravelgenerator.services.bridge.Update
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.editor.Editor
import java.awt.BorderLayout
import javax.swing.JButton
import javax.swing.JPanel

class EditorTab(private var editor: Editor, private var data: Request, update: Update) : JPanel() {
    var closeHandler: () -> Unit = { }

    init {
        this.layout = BorderLayout()

        val saveButton = JButton("Save")
        saveButton.addActionListener { e ->
            update.run(data.key, editor.document.text)
        }

        val close = JButton("Close")
        close.addActionListener { e ->
            closeHandler()
        }

        val panel = JPanel()
        panel.add(saveButton)
        panel.add(close)

        this.add(panel, BorderLayout.PAGE_START)
        this.add(editor.component, BorderLayout.CENTER)
    }

    fun update(data: Request) {
        this.data = data
        updateText(data.data)
    }

    fun updateText(text: String) {
        val task = Runnable() {
            run() {
                editor.document.setText(text)
            }
        }
        val application = ApplicationManager.getApplication()
        if (application.isDispatchThread) {
            application.runWriteAction(task)
        } else {
            application.invokeLater(task)
        }
    }

}
