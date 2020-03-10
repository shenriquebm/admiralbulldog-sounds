package com.github.mrbean355.admiralbulldog.home

import com.github.mrbean355.admiralbulldog.dota.GameStateMonitor
import com.github.mrbean355.admiralbulldog.resources.PoggiesIcon
import com.github.mrbean355.admiralbulldog.resources.WeirdChampIcon
import com.github.mrbean355.admiralbulldog.resources.getString
import javafx.beans.binding.Bindings
import javafx.beans.binding.ObjectBinding
import javafx.beans.property.SimpleBooleanProperty
import javafx.beans.property.SimpleStringProperty
import javafx.scene.image.Image
import tornadofx.ViewModel
import java.util.concurrent.Callable

class HomeViewModel : ViewModel() {
    val title = SimpleStringProperty(getString("home_text_waiting_for_dota"))
    val description = SimpleStringProperty(getString("home_desc_waiting_for_dota"))
    val isLoading = SimpleBooleanProperty(true)
    val icon: ObjectBinding<Image> = Bindings.createObjectBinding(Callable {
        if (isLoading.get()) WeirdChampIcon() else PoggiesIcon()
    }, isLoading)

    init {
        GameStateMonitor.onFirstUpdate {
            title.set(getString("home_text_connected_to_dota"))
            description.set(getString("home_desc_connected_to_dota"))
            isLoading.set(false)
        }
    }
}
