package com.verindrarizya.suitmediatest.domain.usecase

class PalindromeUseCase {

    fun checkPalindrome(text: String): Boolean {
        var isPalindrome = true
        var textLong = text.length - 1

        for(i in 0..textLong) {
            if(text[i] != text[textLong - i]) {
                return false
            }
        }

        return isPalindrome
    }

}