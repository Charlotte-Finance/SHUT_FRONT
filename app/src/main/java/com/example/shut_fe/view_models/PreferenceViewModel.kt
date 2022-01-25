package com.example.shut_fe.view_models

import android.annotation.SuppressLint
import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.shut_fe.services.ApiClient
import com.example.shut_fe.models.preference.Preference
import com.example.shut_fe.models.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

@SuppressLint("LongLogTag")
class PreferenceViewModel(
    application: Application,
    private val user: User,
) : AndroidViewModel(application) {
    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)
    private val _navigateToLoginFragment = MutableLiveData<Preference>()
    private val _preference = MutableLiveData<Preference>()

    val preference: LiveData<Preference>
        get() = _preference

    val navigateToLoginFragment: LiveData<Preference>
        get() = _navigateToLoginFragment


    init {
        try {
            coroutineScope.launch {
                Log.d("AAAAAAAAAAAAAAAAAAAA", user.toString())
                Log.d("BBBBBBBBBBBB", user.id.toString())
                // ToDo : change user Id and fetch it from user
                val response =
                    user.id?.let { ApiClient.apiService.getPreference(it) }
                if (response != null) {
                    _navigateToLoginFragment.value = response.body()
                }
            }
        } catch (e: Exception) {
        }
    }

    fun doneNavigating() {
        _navigateToLoginFragment.value = null
    }

}