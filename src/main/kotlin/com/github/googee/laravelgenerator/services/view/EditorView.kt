package com.github.googee.laravelgenerator.services.view

import java.awt.event.ActionListener
import javax.swing.JButton
import javax.swing.JLabel
import javax.swing.JPanel

class EditorView(listener: ActionListener) : JPanel() {

    init {
        val saveButton = JButton("Save")
        saveButton.addActionListener(listener)

        val label = JLabel("click Save button after changing text")

        this.add(saveButton)
        this.add(label)
    }
}
