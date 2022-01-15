package com.verindrarizya.suitmediatest.ui.thirdscreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.verindrarizya.suitmediatest.data.UserRepository
import com.verindrarizya.suitmediatest.data.entity.User
import com.verindrarizya.suitmediatest.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ThirdViewModel @Inject constructor(private val userRepository: UserRepository): ViewModel() {

    private val _users = MutableLiveData<List<User>>()
    val users: LiveData<List<User>>
        get() = _users

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    private val _isError = MutableLiveData<Boolean>()
    val isError: LiveData<Boolean>
        get() = _isError

    init {
        getUsers()
    }

    fun getUsers(page: Int = 1, perPage: Int = 10) {
        viewModelScope.launch {
            _isError.value = false
            _isLoading.value = true

            val users = userRepository.getUsers(page, perPage)
            when (users) {
                is Resource.Error -> {
                    _isLoading.value = false
                    _isError.value = true
                }
                is Resource.Success -> {
                    _isLoading.value = false
                    _users.value = users.data
                }
            }
        }
    }

}