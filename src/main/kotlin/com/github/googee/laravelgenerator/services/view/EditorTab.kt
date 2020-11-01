package com.github.googee.laravelgenerator.services.view

import com.github.googee.laravelgenerator.services.json.JSEditRequest
import com.intellij.ui.EditorTextField
import java.awt.BorderLayout
import javax.swing.JButton
import javax.swing.JPanel

class EditorTab(data: JSEditRequest, save: (key: String, text: String) -> Unit) : JPanel() {

    init {
        this.layout = BorderLayout()

        val box = EditorTextField(data.data)
        box.setOneLineMode(false)

        val saveButton = JButton("Save")
        saveButton.addActionListener { e ->
            println("Save")
            save(data.key, box.text)
        }

        val reset = JButton("Reset")
        reset.addActionListener { e ->
            println("Reset")
            box.text = data.data
        }

        val panel = JPanel()
        panel.add(saveButton)
        panel.add(reset)

        this.add(panel, BorderLayout.PAGE_START)
        this.add(box, BorderLayout.CENTER)
    }

}
