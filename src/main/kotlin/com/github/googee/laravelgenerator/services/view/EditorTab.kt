package com.github.googee.laravelgenerator.services.view

import com.github.googee.laravelgenerator.services.file.FileManager
import com.github.googee.laravelgenerator.services.json.JSRequest
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.project.Project
import java.awt.BorderLayout
import javax.swing.JButton
import javax.swing.JPanel

class EditorTab(project: Project, private var data: JSRequest, private val fm: FileManager, save: (key: String, text: String) -> Unit) : JPanel() {
    private val editor: Editor

    init {
        this.layout = BorderLayout()

        val file = fm.getFile(data.key)
        editor = EditorFactory.make(project, file)

        val saveButton = JButton("Save")
        saveButton.addActionListener { e ->
            println("Save")
            save(data.key, editor.document.text)
        }

        val reset = JButton("Reset")
        reset.addActionListener { e ->
            println("Reset")
            updateText(data.data)
        }

        val panel = JPanel()
        panel.add(saveButton)
        panel.add(reset)

        this.add(panel, BorderLayout.PAGE_START)
        this.add(editor.component, BorderLayout.CENTER)
    }

    fun update(data: JSRequest) {
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
