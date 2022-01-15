package com.verindrarizya.suitmediatest.data

import com.verindrarizya.suitmediatest.data.entity.User
import com.verindrarizya.suitmediatest.data.remote.UserService
import com.verindrarizya.suitmediatest.util.Resource
import java.lang.Exception
import javax.inject.Inject

class UserRepository @Inject constructor(private val userService: UserService) {

    suspend fun getUsers(page:Int, perPage: Int): Resource<List<User>> {
        try {
            val users = mutableListOf<User>()
            val userResponses = userService.getUsers(page, perPage).data

            userResponses.forEach {
                users.add(User(
                    lastName = it.lastName,
                    id = it.id,
                    avatar = it.avatar,
                    firstName = it.firstName,
                    email = it.email
                ))
            }

            return Resource.Success(users)
        } catch (e: Exception) {
            return Resource.Error("")
        }
    }

}