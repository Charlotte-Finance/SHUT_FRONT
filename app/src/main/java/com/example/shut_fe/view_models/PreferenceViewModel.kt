package com.example.shut_fe.view_models

import android.annotation.SuppressLint
import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.shut_fe.models.User
import com.example.shut_fe.models.preference.PostPreference
import com.example.shut_fe.models.preference.Preference
import com.example.shut_fe.services.ApiClient
import kotlinx.coroutines.*

@SuppressLint("LongLogTag")
class PreferenceViewModel(
    application: Application,
    private val user: User,
) : AndroidViewModel(application) {
    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)
    private val _preference = MutableLiveData<Preference>()
    private val _volume = MutableLiveData<Int>()


    val preference: LiveData<Preference>
        get() = _preference

    val volume: LiveData<Int>
        get() = _volume




    init {
        coroutineScope.launch {
            val userId = user.id!!
            val response = ApiClient.apiService.getPreference(userId)
            if (response.code() == 200) {
                val body = response.body()
                val newPreference = Preference(
                    body?.id,
                    body?.userId!!,
                    body.maxSound!!,
                    body.maxVibration!!,
                    body.soundControl!!,
                    body.colorAlert,
                    body.soundAlert!!,
                    body.music!!,
                )
                _preference.value = newPreference
            }
        }
    }

    fun onMaxNoiseChange(maxSound: String) {
        _preference.value?.maxSound = maxSound
        updatePreference(_preference)
    }

    fun onMaxVibrationChange(maxVibration: String) {
        _preference.value?.maxVibration = maxVibration
        updatePreference(_preference)
    }

    fun onSoundControlChange(soundControl: Boolean) {
        _preference.value?.soundControl = soundControl
        updatePreference(_preference)
    }

    fun onColorAlertChange(colorAlert: Boolean) {
        _preference.value?.colorAlert = colorAlert
        updatePreference(_preference)
    }

    fun onSoundAlertChange(soundAlert: Boolean) {
        _preference.value?.soundAlert = soundAlert
        updatePreference(_preference)
    }

    fun onMusicAlertChange(musicId: Int) {
        _preference.value?.music = musicId
        updatePreference(_preference)
    }

    private fun updatePreference(_preference: MutableLiveData<Preference>) {
        coroutineScope.launch {
            val postPreference = PostPreference(
                _preference.value?.id,
                _preference.value?.userId!!,
                _preference.value?.maxSound!!,
                _preference.value?.maxVibration!!,
                _preference.value?.soundControl!!,
                _preference.value?.colorAlert,
                _preference.value?.soundAlert!!,
                _preference.value?.music!!
            )
            ApiClient.apiService.putPreference(postPreference.id!!, postPreference)
        }
    }

    fun repeatFun(): Job {
        return coroutineScope.launch {
            while(isActive) {
                _volume.value = ApiClient.apiService.getVolume().body()
                delay(100)
            }
        }
    }

}