package com.github.googee.laravelgenerator.services.view

import com.github.googee.laravelgenerator.services.json.JSEditRequest
import java.awt.BorderLayout
import javax.swing.JButton
import javax.swing.JPanel

class EditorTab(private var data: JSEditRequest, save: (key: String, text: String) -> Unit) : JPanel() {
    private val editor: CodeEditor

    init {
        this.layout = BorderLayout()

        editor = CodeEditor(data.data)

        val saveButton = JButton("Save")
        saveButton.addActionListener { e ->
            println("Save")
            save(data.key, editor.text)
        }

        val reset = JButton("Reset")
        reset.addActionListener { e ->
            println("Reset")
            editor.text = data.data
        }

        val panel = JPanel()
        panel.add(saveButton)
        panel.add(reset)

        this.add(panel, BorderLayout.PAGE_START)
        this.add(editor, BorderLayout.CENTER)
    }

    fun update(data: JSEditRequest) {
        this.data = data
        editor.text = data.data
    }

}
