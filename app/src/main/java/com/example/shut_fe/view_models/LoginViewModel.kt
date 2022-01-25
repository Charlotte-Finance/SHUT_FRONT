package com.example.shut_fe.view_models

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.shut_fe.models.User
import com.example.shut_fe.models.user.PostLogin
import com.example.shut_fe.services.ApiClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class LoginViewModel(
    application: Application
) : AndroidViewModel(application) {
    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)
    private val _navigateToPreferenceFragment = MutableLiveData<User>()
    private val _user = MutableLiveData<User>()
    private var _message: String

    val user: LiveData<User>
        get() = _user

    val navigateToPreferenceFragment: LiveData<User>
        get() = _navigateToPreferenceFragment

    val message: String
        get() = _message

    init {
        _user.value = User()
        _message = "Please fill in the fields below"
    }

    fun onValidateLogin() {
        uiScope.launch {
            val userForm = user.value ?: return@launch
            if (userForm.email.isNullOrEmpty() || userForm.password.isNullOrEmpty()) {
                return@launch
            }
            coroutineScope.launch {
                val response =
                    ApiClient.apiService.login(PostLogin(userForm.email!!, userForm.password!!))
                if (response.code() == 200) {
                    _message = "Login successful"
                    val body = response.body()
                    val newUser = User(
                        body?.id,
                        body?.email!!,
                        body.password!!,
                        null,
                        body.forename!!,
                        body.lastName!!,
                        (body.age).toString(),
                        body.address!!,
                        body.city!!,
                        body.country!!,
                        body.gender!!
                    )
                    _user.value = newUser
                    _navigateToPreferenceFragment.value = newUser
                }
                _message = "Wrong email or password, please try again or create an account"
            }
        }
    }


    fun doneNavigating() {
        _navigateToPreferenceFragment.value = null
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}