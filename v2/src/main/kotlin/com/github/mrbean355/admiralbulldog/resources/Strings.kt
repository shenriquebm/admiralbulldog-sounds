package com.github.mrbean355.admiralbulldog.resources

import java.util.ResourceBundle

private val bundle = ResourceBundle.getBundle("strings")

fun getString(key: String): String {
    return if (bundle.containsKey(key)) bundle.getString(key) else key
}

fun getString(key: String, vararg args: Any): String {
    return getString(key).format(*args)
}
