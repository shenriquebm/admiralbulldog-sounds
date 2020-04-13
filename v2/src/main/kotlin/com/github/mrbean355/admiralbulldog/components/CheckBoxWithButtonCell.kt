package com.github.mrbean355.admiralbulldog.components

import javafx.beans.property.BooleanProperty
import javafx.scene.control.Button
import javafx.scene.control.CheckBox
import javafx.scene.control.ListCell
import javafx.scene.control.ListView
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import javafx.scene.layout.ColumnConstraints
import javafx.scene.layout.GridPane
import javafx.scene.layout.Priority

class CheckBoxWithButtonCell<T>(
        buttonImage: Image,
        private val stringConverter: (T) -> String,
        private val getSelectedProperty: (T) -> BooleanProperty,
        private val onButtonClick: (T) -> Unit
) : ListCell<T>() {

    private val container = GridPane()
    private val checkBox = CheckBox()
    private val button = Button("", ImageView(buttonImage))
    private var booleanProperty: BooleanProperty? = null

    init {
        container.columnConstraints.addAll(ColumnConstraints().apply {
            hgrow = Priority.ALWAYS
        })
        container.add(checkBox, 0, 0)
        container.add(button, 1, 0)
    }

    override fun updateItem(item: T?, empty: Boolean) {
        super.updateItem(item, empty)
        if (item == null || empty) {
            graphic = null
            return
        }
        graphic = container
        checkBox.text = stringConverter(item)
        button.setOnAction { onButtonClick(item) }

        booleanProperty?.let {
            checkBox.selectedProperty().unbindBidirectional(booleanProperty)
        }
        booleanProperty = getSelectedProperty(item)
        booleanProperty?.let {
            checkBox.selectedProperty().bindBidirectional(booleanProperty)
        }
    }
}

fun <T> ListView<T>.useCheckBoxWithButton(
        buttonImage: Image,
        stringConverter: (T) -> String,
        getSelectedProperty: (T) -> BooleanProperty,
        onButtonClick: (T) -> Unit
) {
    setCellFactory { CheckBoxWithButtonCell(buttonImage, stringConverter, getSelectedProperty, onButtonClick) }
}
