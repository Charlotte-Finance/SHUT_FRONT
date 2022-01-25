package com.example.shut_fe.view_models

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.shut_fe.services.ApiClient
import com.example.shut_fe.models.user.PostUser
import com.example.shut_fe.models.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class RegisterViewModel(
    application: Application
) : AndroidViewModel(application) {
    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)
    private val _navigateToLoginFragment = MutableLiveData<User>()
    private val _user = MutableLiveData<User>()
    private var _message: String

    val user: LiveData<User>
        get() = _user

    val navigateToLoginFragment: LiveData<User>
        get() = _navigateToLoginFragment

    val message: String
        get() = _message

    init {
        _user.value = User()
        _message = "Please fill in the fields below"
    }

    fun onValidateRegister() {
        uiScope.launch {
            val userForm = user.value ?: return@launch
            if (userForm.email.isNullOrEmpty() ||
                userForm.password.isNullOrEmpty() ||
                userForm.repeatPassword.isNullOrEmpty() ||
                userForm.forename.isNullOrEmpty() ||
                userForm.lastName.isNullOrEmpty() ||
                userForm.age.isNullOrEmpty() ||
                userForm.address.isNullOrEmpty() ||
                userForm.city.isNullOrEmpty() ||
                userForm.country.isNullOrEmpty() ||
                userForm.gender.isNullOrEmpty()
            ) {
                return@launch
            }
            try{
                coroutineScope.launch {
                    val postUser = PostUser(
                        null,
                        userForm.email!!,
                        userForm.password!!,
                        userForm.forename!!,
                        userForm.lastName!!,
                        Integer.parseInt(userForm.age!!),
                        userForm.address!!,
                        userForm.city!!,
                        userForm.country!!,
                        userForm.gender!!
                    )
                    val response =
                        ApiClient.apiService.postUser(postUser)
                    if (response.code() == 200) {
                        _message = "Register successful"
                    }
                    _navigateToLoginFragment.value = response.body()
                }
            }catch (e:Exception){
                return@launch
            }
        }
    }

    fun onGender(gender: String) {
        _user.value?.gender = gender
    }

    fun doneNavigating() {
        _navigateToLoginFragment.value = null
    }

}