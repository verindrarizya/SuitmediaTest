package com.verindrarizya.suitmediatest.util

open class Event<out T>(private val content: T) {

    var hasBeenHandled = false
        private set

    /**
     * return sekali aja, mencegah observer dari sebuah livedata untuk trigger terus menerus
     */
    fun getContentIfNotHandled(): T? {
        return if (hasBeenHandled) {
            null
        } else {
            hasBeenHandled = true
            content
        }
    }
}