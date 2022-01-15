package com.verindrarizya.suitmediatest.ui.firstscreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.verindrarizya.suitmediatest.util.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FirstViewModel @Inject constructor(): ViewModel() {

    private val _isPalindrome = MutableLiveData<Event<Boolean>>()
    val isPalindrome: LiveData<Event<Boolean>>
        get() = _isPalindrome

    fun checkPalindrome(text: String) {
        val result = palindrome(text)
        if (result == true) {
            _isPalindrome.value = Event(true)
        } else {
            _isPalindrome.value = Event(false)
        }
    }

    private fun palindrome(text: String): Boolean {
        val textLong = text.length - 1

        for(i in 0..textLong) {
            if(text[i] != text[textLong - i]) {
                return false
            }
        }

        return true
    }

}