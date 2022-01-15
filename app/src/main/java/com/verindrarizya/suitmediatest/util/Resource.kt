package com.verindrarizya.suitmediatest.util

sealed class Resource<out T> {

    data class Success<T>(val data: T): Resource<T>()

    data class Error(val errorMessage: String): Resource<Nothing>()
}

