package com.github.mrbean355.admiralbulldog.components

import javafx.beans.property.DoubleProperty
import javafx.event.EventTarget
import javafx.scene.control.Slider
import tornadofx.slider

fun EventTarget.slider(valueProperty: DoubleProperty): Slider {
    return slider(min = 0.0, max = 100.0) {
        this.valueProperty().bindBidirectional(valueProperty)
        isShowTickLabels = true
        isShowTickMarks = true
        majorTickUnit = 10.0
        minorTickCount = 1
        isSnapToTicks = true
    }
}
