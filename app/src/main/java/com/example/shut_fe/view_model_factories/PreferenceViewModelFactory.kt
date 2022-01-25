package com.example.shut_fe.view_model_factories

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.shut_fe.models.User
import com.example.shut_fe.view_models.PreferenceViewModel


class PreferenceViewModelFactory(
    private val application: Application,
    private val user: User,
    ) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PreferenceViewModel::class.java)) {
            return PreferenceViewModel(application, user) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}